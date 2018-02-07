package com.xtt.common.assay.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.service.IAssayHospDictService;
import com.xtt.common.assay.service.IPatientAssayGroupRuleService;
import com.xtt.common.dao.mapper.PatientAssayGroupRuleMapper;
import com.xtt.common.dao.model.PatientAssayGroupRule;
import com.xtt.common.dao.po.AssayHospDictPO;
import com.xtt.common.dao.po.PatientAssayGroupRulePO;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

@Service
public class PatientAssayGroupRuleServiceImpl implements IPatientAssayGroupRuleService {

    @Autowired
    private PatientAssayGroupRuleMapper patientAssayGroupRuleMapper;
    @Autowired
    private IAssayHospDictService assayHospDictService;

    /**
     * 添加化验检查分组
     */
    @Override
    public void saveGroupRule(List<PatientAssayGroupRulePO> ruleList, AssayHospDictPO assayHospDict) {
        if (CollectionUtils.isNotEmpty(ruleList)) {// 过滤minValue 为null的值
            ruleList = ruleList.stream().filter(rule -> rule.getMinValue() != null).collect(Collectors.toList());
            // 排序
            ruleList.sort((o1, o2) -> {
                return o1.getMinValue().compareTo(o2.getMinValue());
            });
        }
        // 租户ID
        Integer tenantId = UserUtil.getTenantId();
        // 用户ID
        Long userId = UserUtil.getLoginUserId();
        List<String> itemCodes = assayHospDictService.listSimilarItemCode(assayHospDict.getItemCode(), tenantId);
        if (CollectionUtils.isEmpty(itemCodes)) {
            itemCodes = new ArrayList<>(1);
            itemCodes.add(assayHospDict.getItemCode());
        }
        // 同步分组规则到同类组中的其它itemCode
        for (String code : itemCodes) {
            // 删除patientAssayGroupRule的itemCode 的值
            deleteByItemCode(code);
            // 添加patientAssayGroupRule
            if (CollectionUtils.isNotEmpty(ruleList)) {
                insertGroupRule(ruleList, code, tenantId, userId);
            }
            AssayHospDictPO dict = assayHospDictService.getByItemCode(code);
            if (dict != null) {
                AssayHospDictPO dictRecord = assayHospDict;// 当前更新的项目
                Long dictId = dictRecord.getId();
                if (Objects.equals(dict.getId(), dictId)) {// 更新本条记录，设置其是否置顶，是否置顶标识不同步
                    dict.setIsTop(dictRecord.getIsTop());
                }
                dict.setPersonalMaxValue(dictRecord.getPersonalMaxValue());
                dict.setPersonalMinValue(dictRecord.getPersonalMinValue());
                // 修改DictHospitalLab表
                assayHospDictService.updateSomeValue(dict);
            }
        }
    }

    /**
     * 插入分组规则
     * 
     * @Title: insertGroupRule
     * @param ruleList
     * @param itemCode
     * @param tenantid
     * @param userid
     *
     */
    private void insertGroupRule(List<PatientAssayGroupRulePO> ruleList, String itemCode, Integer tenantid, Long userid) {
        for (int i = 0; i < ruleList.size(); i++) {
            PatientAssayGroupRule po = ruleList.get(i);
            po.setId(null);
            po.setItemCode(itemCode);
            po.setRule(1);
            // 如果是最后一条，maxValue为本身
            po.setMaxValue(i == ruleList.size() - 1 ? po.getMinValue() : ruleList.get(i + 1).getMinValue());
            po.setOperatorId(userid);
            po.setFkTenantId(tenantid);
            DataUtil.setSystemFieldValue(po);
            patientAssayGroupRuleMapper.insertSelective(po);
        }
    }

    /**
     * 批量新增
     */
    @Override
    public void saveBatch(PatientAssayGroupRulePO[] patientAssayGroupRulePO) {
        patientAssayGroupRuleMapper.saveByBatch(patientAssayGroupRulePO);

    }

    /**
     * 删除化验分组规则的值
     */
    @Override
    public void deleteGroupRuleValue(Long id, String itemCode) {
        patientAssayGroupRuleMapper.deleteByPrimaryKey(id);

        // 获得最小值，设置最大值
        List<PatientAssayGroupRulePO> PatientAssayGroupRulePOList = this.selectByItemCode(itemCode);

        // 循环得到最小值集合
        List<Float> getMinValueList = new ArrayList<Float>();
        for (int i = 0; i < PatientAssayGroupRulePOList.size(); i++) {
            getMinValueList.add(PatientAssayGroupRulePOList.get(i).getMinValue());
        }

        for (int i = 0; i < PatientAssayGroupRulePOList.size(); i++) {
            // 最后 一条数据的最大值就是本身
            if (i == PatientAssayGroupRulePOList.size() - 1) {
                PatientAssayGroupRulePOList.get(i).setMaxValue(PatientAssayGroupRulePOList.get(i).getMinValue());
                patientAssayGroupRuleMapper.updateByPrimaryKeySelective(PatientAssayGroupRulePOList.get(i));
            } else {
                // 设置每条数据的最大值
                PatientAssayGroupRulePOList.get(i).setMaxValue(getMinValueList.get(i + 1));
                patientAssayGroupRuleMapper.updateByPrimaryKeySelective(PatientAssayGroupRulePOList.get(i));
            }
        }
    }

    /**
     * 查询所有化验分组规则
     */
    @Override
    public List<PatientAssayGroupRulePO> selectAllAssayGroupRule(String itemsCode) {
        List<PatientAssayGroupRulePO> tempList = patientAssayGroupRuleMapper.selectByItemCode(itemsCode, UserUtil.getTenantId());
        String createTime = null;
        String updateTime = null;
        try {
            for (int i = 0; i < tempList.size(); i++) {
                // 转时间格式
                SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
                createTime = sm.format(tempList.get(i).getCreateTime());
                updateTime = sm.format(tempList.get(i).getUpdateTime());
                // 设置值
                tempList.get(i).setShowCreateTime(createTime);
                tempList.get(i).setShowUpdateTime(updateTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempList;
    }

    /**
     * 通过ID来查询单个对象
     */
    @Override
    public PatientAssayGroupRulePO selectPatientAssayGroupRulePOById(Long id) {
        return patientAssayGroupRuleMapper.selectPatientAssayGroupRulePOById(id);
    }

    /**
     * 通过传进来的值在数据库中是否存在
     */
    @Override
    public int selectExitsByInput(Float inputValue, String getItemCode) {
        List<PatientAssayGroupRulePO> list = patientAssayGroupRuleMapper.selectExitsByInput(inputValue, getItemCode, UserUtil.getTenantId());
        // 没有查到说明输入的值可以用，1表示可以用
        if (list == null || list.size() == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 通过ItemCode的来查询
     */
    @Override
    public List<PatientAssayGroupRulePO> selectByItemCode(String itemCode) {
        return patientAssayGroupRuleMapper.selectByItemCode(itemCode, UserUtil.getTenantId());
    }

    /**
     * @Author=GuangHao
     * 
     * @param record
     * @return
     */
    @Override
    public List<PatientAssayGroupRulePO> selectByCondition(PatientAssayGroupRule record) {
        if (record.getFkTenantId() == null) {
            record.setFkTenantId(UserUtil.getTenantId());
        }
        List<PatientAssayGroupRulePO> list = patientAssayGroupRuleMapper.selectByCondition(record);
        if (list == null) {
            list = new ArrayList<PatientAssayGroupRulePO>();
        }
        return list;
    }

    /**
     * 删除所有传进来的ItemCode的数据
     */
    @Override
    public void deleteByItemCode(String itemCode) {
        patientAssayGroupRuleMapper.deleteByItemCode(itemCode, UserUtil.getTenantId());
    }

    @Override
    public List<PatientAssayGroupRulePO> listByItemCode(String itemCode) {
        return patientAssayGroupRuleMapper.selectByItemCode(itemCode, UserUtil.getTenantId());
    }
}
