/**   
 * @Title: ExcelReportUtil.java 
 * @Package com.xtt.txgl.report.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年5月30日 上午9:01:26 
 *
 */
package com.xtt.common.util.report;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xtt.common.util.report.entity.ReportSheetEntity;

public class ReportExcelTemplate {
    protected Logger log = LoggerFactory.getLogger(getClass());

    private HSSFWorkbook wb;
    /** 标题样式 */
    private CellStyle titleStyle;
    /** 头部单元格样式 */
    private CellStyle headCellStyle;
    /** 奇数行单元格样式 */
    private CellStyle contentOddCellStyle;
    /** 偶数行单元格样式 */
    private CellStyle contentEvenCellStyle;

    public ReportExcelTemplate() {
        wb = new HSSFWorkbook();
        initStyle();
    }

    public ReportExcelTemplate(ReportSheetEntity[] sheetEntitys) {
        wb = new HSSFWorkbook();
        initStyle();

        for (ReportSheetEntity sheetEntity : sheetEntitys) {
            if (sheetEntity == null) {
                continue;
            }
            HSSFSheet sheet = createSheet(sheetEntity.getSheetName());// 创建sheet
            createTitle(sheet, sheetEntity);// 创建标题
            createHeadRow(sheet, sheetEntity);// 创建表头
            createContents(sheet, sheetEntity);// 创建表单内容
        }

    }

    /******************************************* 样式 start *******************************************/

    /** 样式初始化 */
    private void initStyle() {
        {
            // 自定义颜色
            addCustomColor((short) 58, (byte) (192), (byte) (192), (byte) (192));// headlinecolor
            addCustomColor((short) 59, (byte) (225), (byte) (225), (byte) (225));// contentlinecolor
            addCustomColor((short) 60, (byte) 233, (byte) 234, (byte) 236);// headcolor
            addCustomColor((short) 61, (byte) (254), (byte) (254), (byte) (254));// oddcolor
            addCustomColor((short) 62, (byte) (246), (byte) (246), (byte) (246));// evencolor
        }
        {// 样式初始化
            titleStyle = generateTitleCellStyle();
            headCellStyle = generateHeadCellStyle();
            contentOddCellStyle = generateContentCellStyle(true);
            contentEvenCellStyle = generateContentCellStyle(false);
        }

    }

    /**
     * 自定义颜色,index须位于1到63之间；超出无效
     * 
     * @Title: addCustomColor
     * @param index
     * @param r
     * @param g
     * @param b
     * 
     */
    public void addCustomColor(short index, byte r, byte g, byte b) {
        HSSFPalette palette = this.wb.getCustomPalette();
        palette.setColorAtIndex(index, r, g, b);
    }

    /**
     * @Description: 初始化标题行样式
     */
    private CellStyle generateTitleCellStyle() {
        Font titleFont = wb.createFont();
        titleFont.setFontName("宋体");
        titleFont.setFontHeightInPoints((short) 20);
        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        titleFont.setCharSet(Font.DEFAULT_CHARSET);
        titleFont.setColor(IndexedColors.BLUE_GREY.index);

        CellStyle titleStyle = wb.createCellStyle();
        titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
        titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        titleStyle.setFont(titleFont);
        titleStyle.setFillBackgroundColor(IndexedColors.SKY_BLUE.index);
        return titleStyle;
    }

    /**
     * @Description: 初始化表头行样式
     */
    private CellStyle generateHeadCellStyle() {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 11);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setCharSet(Font.DEFAULT_CHARSET);
        font.setColor(HSSFColor.BLACK.index);

        style.setFont(font);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        // style.setFillBackgroundColor(HSSFColor.YELLOW.index);
        style.setFillForegroundColor((short) 60);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        style.setBorderTop(CellStyle.BORDER_MEDIUM);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setTopBorderColor((short) 58);
        style.setBottomBorderColor((short) 58);
        style.setLeftBorderColor((short) 58);
        style.setRightBorderColor((short) 58);
        style.setWrapText(true);
        return style;
    }

    /**
     * @Description: 初始化内容行样式
     */
    public CellStyle generateContentCellStyle(boolean isOdd) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);
        font.setBoldweight(Font.BOLDWEIGHT_NORMAL);
        font.setCharSet(Font.DEFAULT_CHARSET);
        font.setColor(HSSFColor.BLACK.index);

        style.setFont(font);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

        if (isOdd) {// 奇数行
            style.setFillForegroundColor((short) 61);
        } else {
            style.setFillForegroundColor((short) 62);
        }
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        style.setBorderTop(CellStyle.BORDER_MEDIUM);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setTopBorderColor((short) 59);
        style.setBottomBorderColor((short) 59);
        style.setLeftBorderColor((short) 59);
        style.setRightBorderColor((short) 59);
        style.setWrapText(true);
        return style;
    }

    /**
     * 获取excel头部单元格样式
     * 
     * @Title: getContentCellStyle
     * @return
     * 
     */
    public CellStyle getHeadCellStyle() {
        return headCellStyle;
    }

    /**
     * 获取正文单元格样式
     * 
     * @Title: getContentCellStyle
     * @param isOdd
     *            (是否是奇数行)
     * @return
     * 
     */
    public CellStyle getContentCellStyle(boolean isOdd) {
        if (isOdd) {
            return contentOddCellStyle;
        } else {
            return contentEvenCellStyle;
        }
    }

    /******************************************* 样式 end *******************************************/

    /**
     * 批量创建sheet
     * 
     * @Title: createSheets
     * @param sheetNames
     * 
     */
    public void createSheets(String[] sheetNames) {
        for (String sheetName : sheetNames) {
            createSheet(sheetName);
        }
    }

    /**
     * 创建sheet
     * 
     * @Title: createSheet
     * @param sheetName
     * 
     */
    public HSSFSheet createSheet(String sheetName) {
        HSSFSheet sheet = wb.createSheet(sheetName);
        sheet.setDefaultColumnWidth(27);// 设置默认宽度
        return sheet;
    }

    /**
     * 创建标题
     * 
     * @Title: createTitle
     * @param sheet
     * @param sheetEntity
     * 
     */
    public void createTitle(HSSFSheet sheet, ReportSheetEntity sheetEntity) {
        String title = sheetEntity.getTitle();
        if (StringUtils.isNotEmpty(title)) {
            int cellNum = sheetEntity.getHeadNames().length;
            // 判断是否需要序号，如果需要需要，需多合并一列
            if (sheetEntity.isHasRowNo()) {
                cellNum++;
            }

            CellRangeAddress titleRange = new CellRangeAddress(0, 0, 0, cellNum);
            sheet.addMergedRegion(titleRange);
            HSSFRow titleRow = createRow(sheet, sheetEntity);
            titleRow.setHeight(ReportConstants.TITLE_HEIGHT_800);
            HSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellStyle(titleStyle);
            titleCell.setCellValue(title);
        }
    }

    /**
     * 创建表头
     */
    public void createHeadRow(HSSFSheet sheet, ReportSheetEntity sheetEntity) {
        // 表头
        HSSFRow headRow = createRow(sheet, sheetEntity);
        headRow.setHeight(ReportConstants.HEAD_HEIGHT_500);

        // 判断是否需要序号
        int cellNum = 0;
        if (sheetEntity.isHasRowNo()) {
            HSSFCell snCell = headRow.createCell(cellNum++);
            snCell.setCellStyle(headCellStyle);
            snCell.setCellValue("序号");
        }

        // 列头名称
        String[] headNames = sheetEntity.getHeadNames();
        for (int i = 0; i < headNames.length; i++, cellNum++) {
            HSSFCell cell = headRow.createCell(cellNum);
            cell.setCellStyle(headCellStyle);
            cell.setCellValue(headNames[i]);
        }
    }

    /**
     * 创建表单内容
     * 
     * @Title: createContents
     * @param sheet
     * @param sheetEntity
     * 
     */
    public void createContents(HSSFSheet sheet, ReportSheetEntity sheetEntity) {
        String[] columnNames = sheetEntity.getColumnNames();
        List<?> contents = sheetEntity.getContents();
        Set<String> mergeColumns = sheetEntity.getMergeColumnNames();
        // Class<T> clazz = sheetEntity.getClazz();

        for (int i = 0; i < contents.size(); i++) {
            Object content = contents.get(i);
            HSSFRow contentRow = createRow(sheet, sheetEntity);
            int cellNum = 0;

            if (sheetEntity.isHasRowNo()) {
                HSSFCell snCell = contentRow.createCell(cellNum++);
                snCell.setCellStyle(getContentCellStyle(sheet.getLastRowNum() % 2 == 0));
                snCell.setCellValue(i + 1);
            }
            for (String columnName : columnNames) {

                String getterMethodName = "get" + StringUtils.capitalize(columnName);
                if (getterMethodName.equals("getOkIndex")) {
                    System.out.println(1);
                }
                String result = getContentValue(content, getterMethodName);

                HSSFCell contentCell = contentRow.createCell(cellNum);
                contentCell.setCellStyle(getContentCellStyle(sheet.getLastRowNum() % 2 == 0));
                contentCell.setCellValue(result);

                // 合并单元格
                int rowIndex = sheetEntity.getRowNum() - 1;// 每次创建行时自增1，所以需先-1,才是当前行坐标
                if (mergeColumns != null) {
                    if (mergeColumns.contains(columnName)) {
                        // 从第二行开始，比较是否和上一个相同，相同则已被合并，直接结束本次循环
                        if (i > 0) {
                            Object prevContent = contents.get(i - 1);
                            String prevResult = getContentValue(prevContent, getterMethodName);
                            if (prevResult.equals(result)) {
                                rowIndex++;
                                cellNum++;
                                continue;
                            }
                        }

                        // 当前行和下面合并,如果达标标示一致，则合并，不一致，跳出本次循环
                        int rowspan = 0;
                        for (int j = i + 1; j < contents.size(); j++) {
                            Object nextContent = contents.get(j);
                            String nextResult = getContentValue(nextContent, getterMethodName);
                            if (nextResult.equals(result)) {
                                rowspan++;
                            } else {
                                break;
                            }
                        }

                        if (rowspan > 0) {
                            CellRangeAddress cellRange = new CellRangeAddress(rowIndex, rowIndex + rowspan, cellNum, cellNum);
                            sheet.addMergedRegion(cellRange);
                        }
                    }
                }

                cellNum++;
            }
        }
    }

    /**
     * 创建表单底部，一般用于汇总计算
     * 
     * @Title: createFoot
     * @param sheet
     * @param sheetEntity
     * 
     */
    public void createFoot(HSSFSheet sheet, ReportSheetEntity sheetEntity) {

    }

    // 获取表体内容
    private static String getContentValue(Object content, String getterMethodName) {
        Class<? extends Object> clazz = content.getClass();
        Object obj = null;
        String result = "";
        try {
            obj = clazz.getDeclaredMethod(getterMethodName).invoke(content);
            if (obj != null) {
                result = obj.toString();
            }
        } catch (Exception e) {
            if ((clazz = clazz.getSuperclass()) != null) {
                try {
                    obj = (String) clazz.getDeclaredMethod(getterMethodName).invoke(content);
                    if (obj != null) {
                        result = obj.toString();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

        }
        return result;
    }

    /**
     * 创建sheet页
     * 
     * @Title: createSheet
     * @param sheetNames
     * @return
     * 
     */
    public HSSFSheet[] createSheet(String[] sheetNames) {
        return createSheet(sheetNames, null);
    }

    /**
     * 创建sheet页
     * 
     * @Title: createSheet
     * @param sheetNames
     * @param columnWidth
     * @return
     * 
     */
    public HSSFSheet[] createSheet(String[] sheetNames, Integer columnWidth) {
        HSSFSheet[] sheets = new HSSFSheet[sheetNames.length];
        for (int i = 0; i < sheetNames.length; i++) {
            // sheet页 不支持"/"字符
            sheets[i] = createSheet(sheetNames[i]);
        }
        return sheets;
    }

    /**
     * 创建单个sheet页
     * 
     * @Title: createSheet
     * @param sheetName
     * @param columnWidth
     * @return
     *
     */
    public HSSFSheet createSheet(String sheetName, Integer columnWidth) {
        // sheet页 不支持"/"字符
        HSSFSheet sheet = wb.createSheet(sheetName.replace("/", " "));
        if (columnWidth != null) {
            sheet.setDefaultColumnWidth(columnWidth);// 设置默认宽度
        } else {
            // sheets[i].autoSizeColumn(1, true);// 设置自适应宽度
        }
        return sheet;
    }

    /**
     * 设置sheet页默认宽度
     * 
     * @Title: setDefaultColumnWidth
     * @param sheetIndex
     *
     */
    public void setDefaultColumnWidth(int sheetIndex, Integer columnWidth) {
        wb.getSheetAt(sheetIndex).setDefaultColumnWidth(columnWidth);
    }

    /**
     * 创建行，且自增行索引下标
     * 
     * @Title: createRow
     * @param sheet
     * @param sheetEntity
     * @return
     * 
     */
    public HSSFRow createRow(HSSFSheet sheet, ReportSheetEntity sheetEntity) {
        HSSFRow row = sheet.createRow(sheetEntity.incrementRow());
        row.setHeight(ReportConstants.HEAD_HEIGHT_500);
        return row;
    }

    /**
     * 创建head行
     * 
     * @Title: createHeadRow
     * @param sheetIndex第几个sheet页
     * @param rowNum起始行
     * @param colNames列名
     * @return rowNum
     * 
     */
    public int createHeadRow(int sheetIndex, int rowNum, String[] colNames) {
        return createHeadRow(sheetIndex, rowNum, colNames, null);
    }

    /**
     * 创建head行
     * 
     * @Title: createHeadRow
     * @param sheetIndex第几个sheet页
     * @param rowNum起始行
     * @param colNames列名
     * @param titleStr合并标题列
     *            (为空时不生成)
     * @return rowNum
     * 
     */
    public int createHeadRow(int sheetIndex, int rowNum, String[] colNames, String titleStr) {
        HSSFSheet sheet = wb.getSheetAt(sheetIndex);
        if (StringUtils.isNotBlank(titleStr)) {// 生成日期行
            HSSFRow row = sheet.createRow(rowNum);
            for (int i = 0; i < colNames.length; i++) {
                row.setHeight((short) 500);
                row.createCell(i).setCellStyle(getHeadCellStyle());
                if (i == 0)
                    row.getCell(i).setCellValue(titleStr);
            }
            sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, colNames.length - 1));
            rowNum++;
        }
        HSSFRow row = sheet.createRow(rowNum);
        for (int i = 0; i < colNames.length; i++) {
            row.setHeight((short) 500);
            row.createCell(i).setCellStyle(getHeadCellStyle());
            row.getCell(i).setCellValue(colNames[i]);
        }
        return ++rowNum;
    }

    /**
     * 创建正文
     * 
     * @Title: createContent
     * @param sheetIndex第几个sheet页
     * @param rowNum起始行
     * @param rowData行数据
     * @return rowNum
     * 
     */
    public int createContent(int sheetIndex, int rowNum, List<List<String>> rowData) {
        HSSFSheet sheet = wb.getSheetAt(sheetIndex);
        int contentInx = 1;
        for (List<String> dataRow : rowData) {
            HSSFRow row = sheet.createRow(rowNum);
            row.setHeight((short) 750);
            int cellNum = 0;
            for (String str : dataRow) {
                row.createCell(cellNum).setCellValue(str);
                row.getCell(cellNum).setCellStyle(getContentCellStyle(contentInx % 2 != 0));
                cellNum++;
            }
            rowNum++;
            contentInx++;
        }
        return rowNum;
    }

    /**
     * 将excel文件以流的方式生成
     * 
     * @Title: writeToOutStream
     * @param request
     * @param response
     * @param fileName
     *            (文件名称)
     * @throws IOException
     * 
     */
    public void writeToOutStream(HttpServletRequest request, HttpServletResponse response, String fileName) throws IOException {
        fileName = URLDecoder.decode(fileName, "UTF-8");
        String agent = request.getHeader("User-Agent").toLowerCase();
        if (agent.indexOf("firefox") != -1) {
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "iso8859-1"));
        } else {
            response.setHeader("Content-Disposition",
                            "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20").replaceAll("%28", "\\(")
                                            .replaceAll("%29", "\\)").replaceAll("%3B", ";").replaceAll("%40", "@").replaceAll("%23", "\\#")
                                            .replaceAll("%26", "\\&"));

        }
        response.setContentType("application/octet-stream;charset=utf-8");
        OutputStream outputStream = response.getOutputStream();
        wb.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 获取到当前时间，作为我excle标题
     * 
     * @Title: getCurrentTime
     * @return String
     *
     */
    public String getCurrentTime(String timeStyle) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(timeStyle);
        Date date = new Date();
        String currentTime = dateFormat.format(date);
        return currentTime;
    }

    public HSSFWorkbook getWorkbook() {
        return wb;
    }

    /** 自动调整列宽 */
    public void autoAdjustColumnSize(int sheetIndex) {
        HSSFSheet sheet = wb.getSheetAt(sheetIndex);
        for (int j = 0; j < sheet.getLastRowNum() + 1; j++) {
            sheet.autoSizeColumn(j, true);
        }
    }

    /**
     * 冻结行、列
     * 
     * @Title: freezePane
     * @param sheetIndex
     * @param colNum
     * @param rowNum
     *
     */
    public void freezePane(int sheetIndex, int colNum, int rowNum) {
        HSSFSheet sheet = wb.getSheetAt(sheetIndex);
        sheet.createFreezePane(colNum, rowNum);
    }

}

class ReportConstants {
    public static final short HEAD_HEIGHT_500 = 500;// 表头高度
    public static final short TITLE_HEIGHT_800 = 800;// 标题高度
    public static final short CONTENT_HEIGHT_700 = 700;// 标题高度
}
