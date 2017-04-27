/**   
 * @Title: PatientAssayRecordServiceImpl.java 
 * @Package com.xtt.common.patient.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年1月29日 上午9:38:54 
 *
 */
package com.xtt.common.assay.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.xtt.common.assay.service.IPatientAssayRecordService;
import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.dao.mapper.DictHospitalLabMapper;
import com.xtt.common.dao.mapper.PatientAssayRecordMapper;
import com.xtt.common.dao.model.PatientAssayRecord;
import com.xtt.common.dao.po.DictHospitalLabPO;
import com.xtt.common.dao.po.PatientAssayRecordPO;
import com.xtt.common.dto.DictDto;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.SysParamUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.GenerationUUID;
import com.xtt.platform.util.lang.NumberUtil;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateFormatUtil;
import com.xtt.platform.util.time.DateUtil;

@Service
public class PatientAssayRecordServiceImpl implements IPatientAssayRecordService {
    private static final Logger log = LoggerFactory.getLogger(PatientAssayRecordServiceImpl.class);

    @Autowired
    private PatientAssayRecordMapper patientAssayRecordMapper;
    @Autowired
    private DictHospitalLabMapper dictHospitalLabMapper;

    @Override
    public List<PatientAssayRecordPO> getAssayDateRecord(PatientAssayRecordPO record) {
        record.setFkTenantId(UserUtil.getTenantId());
        List<PatientAssayRecordPO> list = patientAssayRecordMapper.selectDateAssayRecord(record);
        return list;
    }

    @Override
    public List<Map<String, Object>> getReportData(Long patientId, Date startTime, Date endTime, String itemCode) {
        return patientAssayRecordMapper.selectReportData(patientId, startTime, endTime, itemCode);
    }

    @Override
    public List<PatientAssayRecordPO> selectByCondition(PatientAssayRecordPO record) {
        record.setFkTenantId(UserUtil.getTenantId());
        return patientAssayRecordMapper.selectByCondition(record);
    }

    @Override
    public List<PatientAssayRecordPO> getByAssayDate(String date, Long patientId) {
        PatientAssayRecordPO record = new PatientAssayRecordPO();
        record.setFkTenantId(UserUtil.getTenantId());
        record.setAssayDate(date);
        record.setFkPatientId(patientId);
        return patientAssayRecordMapper.selectByCondition(record);
    }

    @Override
    public List<PatientAssayRecordPO> selectReportByAssayTime(PatientAssayRecordPO patientAssayRecord) {

        List<PatientAssayRecordPO> list = patientAssayRecordMapper.selectReportByAssayTime(patientAssayRecord);
        if (list == null) {
            list = new ArrayList<PatientAssayRecordPO>();
        }
        return list;
    }

    @Override
    public List<PatientAssayRecordPO> selectStatisticsReport(PatientAssayRecordPO patientAssayRecord) {

        List<PatientAssayRecordPO> list = patientAssayRecordMapper.selectStatisticsReport(patientAssayRecord);
        if (list == null) {
            list = new ArrayList<PatientAssayRecordPO>();
        }
        return list;
    }

    @Deprecated
    @Override
    public List<Map<String, Object>> getCategoryListByPatientId(Long patientId) {
        return patientAssayRecordMapper.selectCategoryListByPatientId(patientId, UserUtil.getTenantId());
    }

    @Override
    public List<PatientAssayRecordPO> getCategoryList(PatientAssayRecordPO record) {
        record.setFkTenantId(UserUtil.getTenantId());
        return patientAssayRecordMapper.selectCategoryList(record);
    }

    @Override
    public List<Map<String, Object>> selectLatestAssayDateByTenantId(Integer tenantId, Date startTime, Date endTIme) {
        return patientAssayRecordMapper.selectLatestAssayDateByTenantId(tenantId, startTime, endTIme);
    }

    @Override
    public List<PatientAssayRecordPO> selectByMonth(PatientAssayRecordPO patientAssayRecord) {
        List<PatientAssayRecordPO> list = patientAssayRecordMapper.selectByMonth(patientAssayRecord);
        if (list == null) {
            list = new ArrayList<PatientAssayRecordPO>();
        }
        return init(list);
    }

    private List<PatientAssayRecordPO> init(List<PatientAssayRecordPO> list) {
        for (PatientAssayRecordPO item : list) {
            item.setResultTipsShow(DictUtil.getItemName(CmDictConsts.IS_OR_NOT, ("1".equals(item.getResultTips()) ? item.getResultTips() : "0")));
        }

        return list;
    }

    @Override
    public List<PatientAssayRecordPO> selectByFkDictCode(PatientAssayRecordPO record) {
        record.setFkTenantId(UserUtil.getTenantId());
        return patientAssayRecordMapper.selectByFkDictCode(record);
    }

    /**
     * 查询患者化验详情
     */
    @Override
    public List<PatientAssayRecordPO> selectPatientAssayDetail(String startTime, String endTime, Long patientId, String assayClass) {
        return patientAssayRecordMapper.selectPatientAssayDetail(startTime, endTime, patientId, assayClass, UserUtil.getTenantId());

    }

    @Override
    public List<Map<String, Object>> selectAssayList(Long patientId, String assayDateFrom, String assayDateTo) {
        return patientAssayRecordMapper.selectAssayList(patientId, assayDateFrom, assayDateTo);
    }

    @Override
    public List<PatientAssayRecord> selectAssayItems(Long patientId, String assayDate) {
        return patientAssayRecordMapper.selectAssayItems(patientId, assayDate);
    }

    @Override
    public List<PatientAssayRecordPO> selectByItemCodes(Collection<String> itemCodes, Date startDate, Date endDate, String patientTempValue) {
        return patientAssayRecordMapper.selectByItemCodes(itemCodes, startDate, endDate, patientTempValue, UserUtil.getTenantId());
    }

    /**
     * 查询特定患者的阶段小结数据
     */
    @Override
    public List<Map<String, Object>> selectLatestAssayDateByPatient(Integer tenantId, Date startTime, Date endTime, Long fkPatientId) {
        return patientAssayRecordMapper.getLatestAssayDateByPatient(tenantId, startTime, endTime, fkPatientId);

    }

    /**
     * 手动录入化验项
     */
    @Override
    public void insertPatientAssay(List<DictHospitalLabPO> dHL) {
        PatientAssayRecordPO po = null;
        List<PatientAssayRecordPO> list = new ArrayList<>();
        // 获取化验单id
        // DictHospitalLabPO dhPO=null;
        String reQid = StringUtil.getUUIDFive();
        Date date = new Date();
        for (DictHospitalLabPO dPO : dHL) {
            // 设置
            po = new PatientAssayRecordPO();
            po.setFkTenantId(UserUtil.getTenantId());
            po.setCreateTime(date);

            po.setId(GenerationUUID.getGenerationUUID());
            po.setFkPatientId(dPO.getFkPatientId());

            String assayDate = dPO.getAssayDateStr();// 化验日期
            Date formartAssayDate = DateFormatUtil.getStartTime(assayDate);// 化验日期格式化
            po.setReportTime(formartAssayDate);// 报告日期
            po.setAssayDate(assayDate);// 化验日期
            po.setAssayMonth(DateUtil.format(DateFormatUtil.convertStrToDate(assayDate), "yyyy-MM"));// 化验日期
            po.setSampleTime(formartAssayDate);// 取样时间

            po.setCheckTime(formartAssayDate);// 检查时间
            po.setReqTime(formartAssayDate);// 申请时间

            String groupId = PinyinHelper.convertToPinyinString(dPO.getGroupName(), "", PinyinFormat.WITHOUT_TONE);
            po.setGroupId(groupId);
            po.setGroupName(dPO.getGroupName());
            po.setItemCode(dPO.getItemCode());
            po.setItemName(dPO.getItemName());
            po.setResultTips(dPO.getResultTips());

            // po.setResultActual(Double.parseDouble(po.getResult()));//结果

            po.setReference(dPO.getReference());
            if (dPO.getMinValue() != null) {
                po.setValueMin(dPO.getMinValue());
            }
            if (dPO.getMaxValue() != null) {
                po.setValueMax(dPO.getMaxValue());
            }
            if (dPO.getResult() != null && !"".equals(dPO.getResult())) {
                po.setResult(dPO.getResult());
                if (NumberUtil.isNumeric(dPO.getResult())) {
                    po.setResultActual(Double.parseDouble(po.getResult()));// 结果
                    dictHospitalLabMapper.updateByPrimaryKeySelective(dPO);
                } else {
                    dictHospitalLabMapper.updateByPrimaryKeySelective(dPO);
                }
            }
            po.setFlage(true);
            po.setUnit(dPO.getUnit());
            po.setValueUnit(dPO.getUnit());
            po.setReqId(reQid);
            // dhPO=dPO;
            list.add(po);
        }
        // 其他选项保存到字典表
        /*if("others".equals(dhPO.getGroupId())){
        	//一个化验单下有多项
        	for (DictHospitalLabPO dictHospitalLabPO : dHL) {
        		String groupId=CnPinyin.getPingYin(dhPO.getGroupName());
        		dictHospitalLabPO.setGroupId(groupId);
        		dictHospitalLabPO.setFkTenantId(UserUtil.getTenantId());
        		DataUtil.setSystemFieldValue(dictHospitalLabPO);
        		dictHospitalLabPO.setFlage(true);
        	}
        	dictHospitalLabMapper.insertDictHospital(dHL);
        }*/
        patientAssayRecordMapper.insertPatientAssay(list);
    }

    /**
     * 修改录入的化验项
     */
    @Override
    public void updatePatientAssay(List<DictHospitalLabPO> dHL) {
        PatientAssayRecordPO patientAssayRecordPO = null;
        // Long dictID=null;
        for (DictHospitalLabPO dPo : dHL) {
            // dictID=dictHospitalLabMapper.getDictId(dPo);
            patientAssayRecordPO = new PatientAssayRecordPO();
            patientAssayRecordPO.setItemName(dPo.getItemName());
            patientAssayRecordPO.setItemCode(dPo.getItemCode());
            patientAssayRecordPO.setResult(dPo.getResult());
            patientAssayRecordPO.setResultTips(dPo.getResultTips());
            patientAssayRecordPO.setValueMin(dPo.getMinValue());
            patientAssayRecordPO.setValueMax(dPo.getMaxValue());
            patientAssayRecordPO.setValueUnit(dPo.getUnit());
            patientAssayRecordPO.setId(dPo.getPatientAssayId());
            /*dPo.setId(dictID);
            dictHospitalLabMapper.updateByPrimaryKeySelective(dPo);*/
            patientAssayRecordMapper.updateByPrimaryKeySelective(patientAssayRecordPO);

        }
    }

    /**
     * 删除手动录入的化验项
     */
    @Override
    public int deleteById(String id) {
        return patientAssayRecordMapper.deleteById(id);
    }

    /**
     * 查询手动录入的化验项
     */
    @Override
    public List<PatientAssayRecordPO> selectByGroupId(PatientAssayRecordPO par) {
        return patientAssayRecordMapper.selectByGroupId(par);
    }

    @Override
    public List<PatientAssayRecordPO> selectItemLatestDataByCondition(PatientAssayRecordPO record) {
        record.setFkTenantId(UserUtil.getTenantId());
        return patientAssayRecordMapper.selectItemLatestDataByCondition(record);
    }

    @Override
    public List<PatientAssayRecordPO> listPatientAssayRecord(PatientAssayRecordPO po) {
        return patientAssayRecordMapper.listPatientAssayRecord(po);
    }

    @Override
    public void updateListPatientAssayRecord(List<PatientAssayRecordPO> list) {
        for (PatientAssayRecordPO patientAssayRecordPO : list) {
            patientAssayRecordMapper.updateByPrimaryKeySelective(patientAssayRecordPO);
        }
    }

    @Override
    public void updateLisAfterBefore() {
        // 检验透析前后判断逻辑控制（检验结果表<patient_assay_record> 1：根据group_name判断 2：根据sample_class判断 3：根据item_code判断 4：根据sample_time判断）
        String labAfterBefore = SysParamUtil.getValueByName(UserUtil.getTenantId(), PatientAssayRecordPO.LAB_AFTER_BEFORE);
        String assayMonth = DateFormatUtil.getCurrentDateStr(DateFormatUtil.FORMAT_YYYY_MM);

        // 根据指定的条件获取检验结果集
        List<PatientAssayRecordPO> listPatientAssayRecord = listPatientAssayRecord(assayMonth, null, null);

        log.info(assayMonth + ":==========labAfterBefore 判断逻辑：" + labAfterBefore + ",获取集合大小：" + listPatientAssayRecord.size());
        List<PatientAssayRecordPO> newList = new ArrayList<PatientAssayRecordPO>();

        // 如果为（返回的集合为null|只有一条数据|没有配置开个信息）不做任何处理
        if (StringUtil.isNotEmpty(labAfterBefore) || CollectionUtils.isEmpty(newList) || newList.size() <= 1) {
            return;
        }

        // 1：根据group_name判断
        if (PatientAssayRecordPO.LAB_AFTER_BEFORE_ONE.equals(labAfterBefore)) {
            for (PatientAssayRecordPO parPO : listPatientAssayRecord) {
                String groupName = parPO.getGroupName();// 申请单名
                PatientAssayRecordPO newParPO = newPatientAssayRecordPO(parPO, groupName);
                newList.add(newParPO);
            }
        }
        // 2：根据sample_class判断
        if (PatientAssayRecordPO.LAB_AFTER_BEFORE_TWO.equals(labAfterBefore)) {
            for (PatientAssayRecordPO parPO : listPatientAssayRecord) {
                String sampleClass = parPO.getSampleClass();// 样本类型
                PatientAssayRecordPO newParPO = newPatientAssayRecordPO(parPO, sampleClass);
                newList.add(newParPO);
            }
        }
        // 3：根据item_code判断
        if (PatientAssayRecordPO.LAB_AFTER_BEFORE_THREE.equals(labAfterBefore)) {
            for (PatientAssayRecordPO parPO : listPatientAssayRecord) {
                String itemCode = parPO.getItemCode();// 项目名称
                PatientAssayRecordPO newParPO = newPatientAssayRecordPO(parPO, itemCode);
                newList.add(newParPO);
            }
        }
        // 4：根据sample_time判断
        if (PatientAssayRecordPO.LAB_AFTER_BEFORE_FOUR.equals(labAfterBefore)) {
            // 根据项目编码过滤查询
            List<DictDto> itemCodeList = DictUtil.listByPItemCode(PatientAssayRecordPO.WHERE_IN_ITEM_CODE_LIST);
            List<String> itemCode = new ArrayList<String>();
            for (DictDto dictDto : itemCodeList) {
                itemCode.add(dictDto.getItemCode());
            }
            // a.根据指定的条件获取检验结果集(根据指定的项目编码获取项目所在的申请单信息)
            List<PatientAssayRecordPO> listItemCodePO = listPatientAssayRecord(assayMonth, itemCode, null);
            List<String> reqList = new ArrayList<String>();
            for (PatientAssayRecordPO patientAssayRecordPO : listItemCodePO) {
                String reqId = patientAssayRecordPO.getReqId();// 申请单ID
                reqList.add(reqId);
            }
            // b.根据指定的条件获取检验结果集(根据申请单获取所有的数据)
            List<PatientAssayRecordPO> listReqIdPO = listPatientAssayRecord(assayMonth, null, reqList);

            // c.对查询的结果集做逻辑处理
            newList = listPatientAssayRecordPO(listReqIdPO);
        }

        // 更新检验结果（透析前后逻辑）
        if (CollectionUtils.isNotEmpty(newList)) {
            updateListPatientAssayRecord(newList);
        }
        log.info("==========更新了检验透析前后逻辑标示：" + newList.size() + "条记录.");
    }

    /**
     * @Title: diaAbFlag
     * @Description:根据指定的条件判断是透析前还是透析后
     * @param where
     * @return String @throws
     */
    private String diaAbFlag(String where) {
        String ab = "0";// 非透析前后
        // 关键字
        String labBefore = DictUtil.getItemCode("lab_after_before_keyword", PatientAssayRecordPO.LAB_BEFORE);// 透析前=1
        String labAfter = DictUtil.getItemCode("lab_after_before_keyword", PatientAssayRecordPO.LAB_AFTER);// 透析后=2
        // 透析前
        if (StringUtil.isNotEmpty(labBefore) && where.indexOf(labBefore) > 0) {
            ab = PatientAssayRecordPO.LAB_BEFORE;
        }
        // 透析后
        if (StringUtil.isNotEmpty(labAfter) && where.indexOf(labAfter) > 0) {
            ab = PatientAssayRecordPO.LAB_AFTER;
        }
        return ab;
    }

    /**
     * @Title: listPatientAssayRecordPO
     * @Description:根据样本时间来判断
     * @param listPO
     * @return List<PatientAssayRecordPO> @throws
     */
    private List<PatientAssayRecordPO> listPatientAssayRecordPO(List<PatientAssayRecordPO> listPO) {
        List<PatientAssayRecordPO> newList = new ArrayList<PatientAssayRecordPO>();
        String sjStr = DictUtil.getItemCode("lab_after_before_keyword", PatientAssayRecordPO.LAB_GJZ_SJ);// 关键字：设置为24小时
        if (StringUtil.isNotEmpty(sjStr)) {
            Long sj = Long.valueOf(sjStr);
            for (int i = 0; i < listPO.size() - 1; i++) {
                PatientAssayRecordPO po1 = listPO.get(i);// 集合中第一条记录
                PatientAssayRecordPO po2 = listPO.get(i + 1);// 集合的第二条记录
                Date sampleTime1 = po1.getSampleTime();
                Date sampleTime2 = po2.getSampleTime();
                if (sampleTime1 != null && sampleTime2 != null) {
                    long time1 = sampleTime1.getTime();
                    long time2 = sampleTime2.getTime();

                    long dd = (time1 - time2) / (1000 * 3600); // 共计小时
                    if (dd <= sj) {
                        po1 = newPatientAssayRecordPOToDiaAbFlag(po1, PatientAssayRecordPO.LAB_AFTER);
                        po2 = newPatientAssayRecordPOToDiaAbFlag(po1, PatientAssayRecordPO.LAB_BEFORE);
                        newList.add(po1);
                        newList.add(po2);
                    }
                }
            }
        }
        return newList;
    }

    /**
     * @Title: newPatientAssayRecordPO
     * @Description:生成新的对象
     * @param parPO
     * @param ifStr
     * @return PatientAssayRecordPO @throws
     */
    private PatientAssayRecordPO newPatientAssayRecordPO(PatientAssayRecordPO parPO, String ifStr) {
        String diaAbFlag = diaAbFlag(ifStr); // 透析前后标示
        parPO = newPatientAssayRecordPOToDiaAbFlag(parPO, diaAbFlag);
        return parPO;
    }

    /**
     * @Title: newPatientAssayRecordPO
     * @Description:生成新的对象2
     * @param parPO
     * @param ifStr
     * @return PatientAssayRecordPO @throws
     */
    private PatientAssayRecordPO newPatientAssayRecordPOToDiaAbFlag(PatientAssayRecordPO parPO, String diaAbFlag) {
        // 非透析前后不做任何处理
        if (PatientAssayRecordPO.NOT_AFTER_BEFORE.equals(diaAbFlag)) {
            parPO.setDiaAbFlag(diaAbFlag); // 存储透析前后标示
            return parPO;
        }
        String itemCode = parPO.getItemCode();// 原始item_code
        String itemName = parPO.getItemName();// 项目名称
        String newItemCode = itemCode + "_" + diaAbFlag;// 新的项目编码

        // 新的项目编码（透析前）
        if (PatientAssayRecordPO.LAB_BEFORE.equals(diaAbFlag)) {
            itemName = itemName + PatientAssayRecordPO.LAB_BEFORE_CN;
        }
        // 新的项目编码（透析后）
        if (PatientAssayRecordPO.LAB_AFTER.equals(diaAbFlag)) {
            itemName = itemName + PatientAssayRecordPO.LAB_AFTER_CN;
        }

        parPO.setDiaAbFlag(diaAbFlag); // 存储透析前后标示
        parPO.setItemCode(newItemCode);// 存储新的项目编码
        parPO.setNewItemCode(itemCode);// 存储原始项目编码

        return parPO;
    }

    /**
     * @Title: listPatientAssayRecord
     * @Description:根据指定条件获取结果集数据
     * @param assayMonth
     * @param itemCodeList
     * @param reqIdList
     * @return List <PatientAssayRecordPO> @throws
     */
    private List<PatientAssayRecordPO> listPatientAssayRecord(String assayMonth, List<String> itemCodeList, List<String> reqIdList) {
        PatientAssayRecordPO po = new PatientAssayRecordPO();
        po.setAssayMonth(assayMonth);
        po.setItemCodeList(itemCodeList);
        po.setReqIdList(reqIdList);
        po.setDiaAbFlag(PatientAssayRecordPO.NOT_AFTER_BEFORE);// 只处理未处理的非透析前后的数据
        List<PatientAssayRecordPO> listPatientAssayRecord = listPatientAssayRecord(po);
        return listPatientAssayRecord;
    }

}
