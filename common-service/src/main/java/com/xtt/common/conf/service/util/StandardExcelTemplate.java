package com.xtt.common.conf.service.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.xtt.common.constants.CmDictConstants;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.CmPatient;
import com.xtt.common.dao.po.SysUserPO;
import com.xtt.common.util.DictUtil;

class StandardExcelTemplate {

    final Workbook workbook;

    private final Sheet sheet;

    private final CellStyle errorRed;

    private final StandardSheetType sheetType;

    private final static short firstDataRow = 1;

    private final static short headRow = 0;

    private final static String[] doctorPositionArr = { "医士", "助理医师", "医师", "主治医师", "副主任医师", "主任医师" };
    private final static String[] nursePositionArr = { "初级护士", "初级护师", "主管护师", "副主任护师", "主任护师" };

    private StandardColumnHeaders[] columnHeaders;

    private Map<Short, Map<CellStyle, CellStyle>> cellStyles;

    public StandardExcelTemplate(Workbook excel, StandardSheetType type) {
        workbook = excel;
        sheetType = type;
        sheet = ExcelTools.getRequiredSheet(workbook, sheetType.getValue());
        errorRed = workbook.createCellStyle();
        errorRed.setFillForegroundColor(IndexedColors.RED.getIndex());
        errorRed.setFillPattern(CellStyle.SOLID_FOREGROUND);
        errorRed.setDataFormat(sheet.getRow(0).getCell(0).getCellStyle().getDataFormat());
        cellStyles = new HashMap<Short, Map<CellStyle, CellStyle>>();
        init();
    }

    private void init() {
        StandardColumnHeaders[] matchColumnHeaders = {};
        switch (sheetType) {
        case patient:
            matchColumnHeaders = StandardColumnHeaders.patientColumnHeaders;
            break;
        case doctor:
            matchColumnHeaders = StandardColumnHeaders.doctorColumnHeaders;
            break;
        case nurse:
            matchColumnHeaders = StandardColumnHeaders.nurseColumnHeaders;
            break;
        }
        // 列名匹配
        Row head = sheet.getRow(headRow);
        List<StandardColumnHeaders> headList = new ArrayList<StandardColumnHeaders>();
        for (int i = 0; i < head.getLastCellNum(); i++) {
            String headCellStr = ExcelTools.toString(head.getCell(i));
            if (StringUtils.isBlank(headCellStr)) {
                throw new BadInputException(BadInputException.KEY_UNRECOGNIZED_HEADER, headCellStr);
            }
            boolean hasMatched = false;
            for (StandardColumnHeaders h : matchColumnHeaders) {
                if (h.getValue().equals(headCellStr)) {
                    hasMatched = true;
                    headList.add(h);
                    break;
                }
            }
            if (!hasMatched)
                throw new BadInputException(BadInputException.KEY_UNRECOGNIZED_HEADER, headCellStr);
        }
        columnHeaders = StandardColumnHeaders.convertToArray(headList);
    }

    CmPatient getPatient(int rownum) throws DatatypeConfigurationException {
        if (sheetType == StandardSheetType.patient && firstDataRow <= rownum) {
            Row row = sheet.getRow(rownum);
            if (ExcelTools.isEmpty(row))
                return null;
            CmPatient p = new CmPatient();
            p.setName(checkLength(ExcelTools.toString(getCell(row, StandardColumnHeaders.patientName)), StandardColumnHeaders.patientName.getValue(),
                            18, true));
            p.setSex(getSexValue(ExcelTools.toString(getCell(row, StandardColumnHeaders.patientSex))));
            p.setIdType(checkLength(getIdType(ExcelTools.toString(getCell(row, StandardColumnHeaders.patientIdType))),
                            StandardColumnHeaders.patientIdType.getValue(), 1, true));
            if ("1".equals(p.getIdType())) {
                p.setIdNumber(checkIdNumber(ExcelTools.toString(getCell(row, StandardColumnHeaders.patientIdNumber))));
            } else {
                p.setIdNumber(checkLength(ExcelTools.toString(getCell(row, StandardColumnHeaders.patientIdNumber)),
                                StandardColumnHeaders.patientIdNumber.getValue(), 18, true));
            }
            p.setBirthday(getDate(ExcelTools.toDay(getCell(row, StandardColumnHeaders.patientBirthday)), p.getIdNumber()));
            p.setWorkUnit(checkLength(ExcelTools.toString(getCell(row, StandardColumnHeaders.patientWorkUnit)),
                            StandardColumnHeaders.patientWorkUnit.getValue(), 64, false));
            p.setMobile(checkMobile(ExcelTools.toString(getCell(row, StandardColumnHeaders.patientMobile)), true));
            p.setEmergencyContacts(checkLength(ExcelTools.toString(getCell(row, StandardColumnHeaders.patientEmergencyContacts)),
                            StandardColumnHeaders.patientEmergencyContacts.getValue(), 36, false));
            p.setEmergencyMobile(checkLength(ExcelTools.toString(getCell(row, StandardColumnHeaders.patientEmergencyMobile)),
                            StandardColumnHeaders.patientEmergencyMobile.getValue(), 15, false));
            p.setEmergencyMobile2(checkLength(ExcelTools.toString(getCell(row, StandardColumnHeaders.patientEmergencyMobile2)),
                            StandardColumnHeaders.patientEmergencyMobile2.getValue(), 15, false));
            p.setEmergencyMobile3(checkLength(ExcelTools.toString(getCell(row, StandardColumnHeaders.patientEmergencyMobile3)),
                            StandardColumnHeaders.patientEmergencyMobile3.getValue(), 15, false));
            p.setAddress(checkLength(ExcelTools.toString(getCell(row, StandardColumnHeaders.patientAddress)),
                            StandardColumnHeaders.patientAddress.getValue(), 64, false));
            p.setDryWeight(checkDecimal(ExcelTools.toBigDecimal(getCell(row, StandardColumnHeaders.patientDryWeight)),
                            StandardColumnHeaders.patientDryWeight.name(), new BigDecimal(0.01), new BigDecimal(600), false));
            p.setAdmissionNumber(checkLength(
                            checkIsNumberOrLetter(ExcelTools.toString(getCell(row, StandardColumnHeaders.patientAdmissionNumber)),
                                            StandardColumnHeaders.patientAdmissionNumber.getValue()),
                            StandardColumnHeaders.patientAdmissionNumber.getValue(), 64, false));
            p.setOutpatientNumber(checkLength(
                            checkIsNumberOrLetter(ExcelTools.toString(getCell(row, StandardColumnHeaders.patientOutpatientNumber)),
                                            StandardColumnHeaders.patientOutpatientNumber.getValue()),
                            StandardColumnHeaders.patientOutpatientNumber.getValue(), 64, false));
            p.setDialysisTimes(checkInteger(
                            checkLength(ExcelTools.toString(getCell(row, StandardColumnHeaders.patientDialysisTimes)),
                                            StandardColumnHeaders.patientDialysisTimes.getValue(), 4, false),
                            StandardColumnHeaders.patientDialysisTimes.getValue(), false));
            p.setSerialNum(checkLength(ExcelTools.toString(getCell(row, StandardColumnHeaders.patientSerialNum)),
                            StandardColumnHeaders.patientSerialNum.getValue(), 10, false));

            return p;
        }
        return null;
    }

    SysUserPO getUser(int rownum) throws DatatypeConfigurationException {
        SysUserPO u = null;
        if (firstDataRow <= rownum) {
            Row row = sheet.getRow(rownum);
            if (ExcelTools.isEmpty(row))
                return null;
            u = new SysUserPO();
            if (sheetType == StandardSheetType.doctor) {// 医生
                u.setAccount(checkLength(ExcelTools.toString(getCell(row, StandardColumnHeaders.doctorAccount)),
                                StandardColumnHeaders.doctorAccount.getValue(), 32, true));
                u.setName(checkLength(ExcelTools.toString(getCell(row, StandardColumnHeaders.doctorName)),
                                StandardColumnHeaders.doctorName.getValue(), 32, true));
                u.setSex(getSexValue(ExcelTools.toString(getCell(row, StandardColumnHeaders.doctorSex))));
                u.setRoleId(getRole(ExcelTools.toString(getCell(row, StandardColumnHeaders.doctorRole))));
                u.setBirthday(getDate(ExcelTools.toDay(getCell(row, StandardColumnHeaders.doctorBirthday)), null));
                u.setPosition(getUserPosition(ExcelTools.toString(getCell(row, StandardColumnHeaders.doctorPosition))));
                u.setMobile(checkMobile(ExcelTools.toString(getCell(row, StandardColumnHeaders.doctorMobile)), false));
                u.setSubPhone(checkLength(ExcelTools.toString(getCell(row, StandardColumnHeaders.doctorOtherContacts)),
                                StandardColumnHeaders.doctorOtherContacts.getValue(), 64, false));
            } else if (sheetType == StandardSheetType.nurse) {// 护士
                u.setAccount(checkLength(ExcelTools.toString(getCell(row, StandardColumnHeaders.nurseAccount)),
                                StandardColumnHeaders.nurseAccount.getValue(), 32, true));
                u.setName(checkLength(ExcelTools.toString(getCell(row, StandardColumnHeaders.nurseName)), StandardColumnHeaders.nurseName.getValue(),
                                32, true));
                u.setSex(getSexValue(ExcelTools.toString(getCell(row, StandardColumnHeaders.nurseSex))));
                u.setRoleId(getRole(ExcelTools.toString(getCell(row, StandardColumnHeaders.nurseRole))));
                u.setBirthday(getDate(ExcelTools.toDay(getCell(row, StandardColumnHeaders.nurseBirthday)), null));
                u.setPosition(getUserPosition(ExcelTools.toString(getCell(row, StandardColumnHeaders.nursePosition))));
                u.setMobile(checkMobile(ExcelTools.toString(getCell(row, StandardColumnHeaders.nurseMobile)), false));
                u.setSubPhone(checkLength(ExcelTools.toString(getCell(row, StandardColumnHeaders.nurseOtherContacts)),
                                StandardColumnHeaders.nurseOtherContacts.getValue(), 64, false));
            }
        }
        return u;
    }

    Row getRow(int rownum) {
        return sheet.getRow(rownum);
    }

    int getLastRowNum() {
        return sheet.getLastRowNum();
    }

    private void style(Row r, CellStyle style, int cellnum) {
        if (r == null)
            return;
        if (cellnum != -1) {
            setCellStyle(r, style, cellnum);
        } else {
            int c = r.getLastCellNum();
            for (int i = 0; i < c; i++) {
                setCellStyle(r, style, i);
            }
        }
    }

    private void setCellStyle(Row r, CellStyle style, int i) {
        Cell x = r.getCell(i);
        if (x == null) {
            x = ExcelTools.createCell(r, i, null);
        } else {
            CellStyle oldStyle = x.getCellStyle();
            short oldDataFormat = oldStyle.getDataFormat();
            if (oldDataFormat != style.getDataFormat()) {
                if (cellStyles.containsKey(oldDataFormat) && cellStyles.get(oldDataFormat).containsKey(style)) {
                    style = cellStyles.get(oldDataFormat).get(style);
                } else {
                    CellStyle newStyle = workbook.createCellStyle();
                    newStyle.cloneStyleFrom(style);
                    newStyle.setDataFormat(oldDataFormat);

                    if (!cellStyles.containsKey(oldDataFormat)) {
                        cellStyles.put(oldDataFormat, new HashMap<CellStyle, CellStyle>());
                    }
                    cellStyles.get(oldDataFormat).put(style, newStyle);
                    style = newStyle;
                }
            }
        }
        x.setCellStyle(style);
    }

    void setResultRowInvalid(int rowNum, String reason) {
        Row row = sheet.getRow(rowNum);
        try {
            style(row, errorRed, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Cell getCell(Row row, StandardColumnHeaders h) {
        short i = 0;
        for (StandardColumnHeaders x : columnHeaders) {
            if (x == h)
                return row.getCell(i);
            i++;
        }
        return null;
    }

    private String getSexValue(String str) {
        if (StringUtils.isBlank(str)) {
            throw new BadInputException(BadInputException.KEY_REQUIRED, "性别");
        }
        if ("男".equals(str)) {
            return "M";
        } else if ("女".equals(str)) {
            return "F";
        } else {
            throw new BadInputException(BadInputException.KEY_INVALID_SEX, str);
        }
    }

    private String getIdType(String str) {
        if (StringUtils.isBlank(str)) {
            throw new BadInputException(BadInputException.KEY_REQUIRED, "证件类型");
        }
        if ("身份证".equals(str)) {
            return "1";
        } else if ("护照".equals(str)) {
            return "2";
        } else if ("其它".equals(str)) {
            return "3";
        } else {
            throw new BadInputException(BadInputException.KEY_INVALID_VALUE, str);
        }
    }

    /**
     * 检查是否为身份证号码
     * 
     * @Title: checkIdNumber
     * @param str
     * @return
     * 
     */
    private String checkIdNumber(String str) {
        if (StringUtils.isBlank(str)) {
            throw new BadInputException(BadInputException.KEY_REQUIRED, "身份证号码");
        }
        Pattern p = Pattern.compile(
                        "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x)$");
        Matcher matcher = p.matcher(str);
        if (matcher.matches()) {
            return str;
        } else {
            throw new BadInputException(BadInputException.KEY_INVALID_ID_NUMBER, str);
        }
    }

    private Date getDate(XMLGregorianCalendar day, String idNumber) {
        if (day != null) {
            Calendar cal = Calendar.getInstance();
            cal.set(day.getYear(), day.getMonth() - 1, day.getDay());
            return cal.getTime();
        } else {// 没填生日时，根据身份证号码获取时间
            if (StringUtils.isNotBlank(idNumber) && idNumber.length() > 14) {
                String date = idNumber.substring(6, 14);
                Calendar cal = Calendar.getInstance();
                cal.set(Integer.valueOf(date.substring(0, 4)), Integer.valueOf(date.substring(4, 6)) - 1, Integer.valueOf(date.substring(6, 8)));
                return cal.getTime();
            }
        }
        return null;
    }

    /**
     * 检查String最大长度
     * 
     * @Title: checkLength
     * @param val
     * @param name
     *            字段名称
     * @param length
     *            最大长度
     * @param required
     *            是否必填
     * @return
     * 
     */
    private String checkLength(String val, String name, int length, boolean required) {
        if (StringUtils.isNotBlank(val)) {
            if (val.length() > length) {
                throw new BadInputException(BadInputException.KEY_DATA_TOO_LONG, name);
            }
        } else {
            if (required) {
                throw new BadInputException(BadInputException.KEY_REQUIRED, name);
            }
        }
        return val;
    }

    /**
     * 检查是否由字母、数字及'_'组成
     * 
     * @Title: checkIsNumberOrLetter
     * @param str
     * @param name
     * @return
     * 
     */
    private String checkIsNumberOrLetter(String str, String name) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        Pattern p = Pattern.compile("^[0-9a-zA-Z_]+$");
        Matcher m = p.matcher(str);
        if (!m.matches()) {
            throw new BadInputException(BadInputException.KEY_INVALID_VALUE, str);
        }
        return str;
    }

    /**
     * 检查数字的最大值和最小值
     * 
     * @Title: checkDecimal
     * @param val
     * @param name
     *            名称
     * @param min
     *            最小值
     * @param max
     *            最大值
     * @param required
     *            必填项
     * @return
     * 
     */
    private BigDecimal checkDecimal(BigDecimal val, String name, BigDecimal min, BigDecimal max, boolean required) {
        if (val != null) {
            if (min != null && val.compareTo(min) < -1) {
                throw new BadInputException(BadInputException.KEY_INVALID_VALUE, name);
            }
            if (max != null && val.compareTo(max) > 1) {
                throw new BadInputException(BadInputException.KEY_INVALID_VALUE, name);
            }
        } else {
            if (required)
                throw new BadInputException(BadInputException.KEY_REQUIRED, name);
        }
        return val;
    }

    /** 检查是否是手机号码 */
    private Integer checkInteger(String val, String name, boolean required) {
        if (required && StringUtils.isBlank(val)) {
            throw new BadInputException(BadInputException.KEY_REQUIRED, name);
        }
        if (StringUtils.isNotBlank(val)) {
            Pattern p = Pattern.compile("^\\d+$");
            Matcher matcher = p.matcher(val);
            if (matcher.matches()) {
                return Integer.valueOf(val);
            } else {
                throw new BadInputException(BadInputException.KEY_INVALID_INTEGER, val);
            }
        }
        return 0;
    }

    /** 检查是否是手机号码 */
    private String checkMobile(String mobile, boolean required) {
        if (required && StringUtils.isBlank(mobile)) {
            throw new BadInputException(BadInputException.KEY_REQUIRED, "手机号");
        }
        if (StringUtils.isNotBlank(mobile)) {
            Pattern p = Pattern.compile("^([0-9]{1,11})?$");
            Matcher matcher = p.matcher(mobile);
            if (matcher.matches()) {
                return mobile;
            } else {
                throw new BadInputException(BadInputException.KEY_INVALID_MOBILE, mobile);
            }
        }
        return mobile;
    }

    /**
     * 获取用户职称对应的值
     * 
     * @Title: getUserPosition
     * @param value
     * @return
     * 
     */

    private String getUserPosition(String value) {
        if (StringUtils.isBlank(value))
            return null;
        if (sheetType == StandardSheetType.doctor) {
            for (String s : doctorPositionArr) {
                if (s.equals(value)) {
                    return DictUtil.getValue(CmDictConstants.DOCTOR_PROFESSIONAL_TITLE, value);
                }
            }
            throw new BadInputException(BadInputException.KEY_INVALID_POSITION, value);
        } else if (sheetType == StandardSheetType.nurse) {
            for (String s : nursePositionArr) {
                if (s.equals(value)) {
                    return DictUtil.getValue(CmDictConstants.NURSE_PROFESSIONAL_TITLE, value);
                }
            }
            throw new BadInputException(BadInputException.KEY_INVALID_POSITION, value);
        }
        return null;
    }

    /**
     * 获取用户角色
     * 
     * @Title: getRole
     * @param value
     * @return
     * 
     */
    private String getRole(String value) {
        if (StringUtils.isBlank(value)) {
            throw new BadInputException(BadInputException.KEY_REQUIRED, "角色");
        }
        if (sheetType == StandardSheetType.doctor) {
            if ("主任".equals(value)) {
                return CommonConstants.ROLE_CONSTANT_DOCTOR_DIRECTOR + "";
            } else if ("医生".equals(value)) {
                return CommonConstants.ROLE_CONSTANT_DOCTOR + "";
            } else {
                throw new BadInputException(BadInputException.KEY_INVALID_ROLE, value);
            }
        } else if (sheetType == StandardSheetType.nurse) {
            if ("护士长".equals(value)) {
                return CommonConstants.ROLE_CONSTANT_HEAD_NURSE + "";
            } else if ("护士".equals(value)) {
                return CommonConstants.ROLE_CONSTANT_NURSE + "";
            } else {
                throw new BadInputException(BadInputException.KEY_INVALID_ROLE, value);
            }
        }
        return null;
    }

}