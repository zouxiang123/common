package com.xtt.common.assay.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.service.IPatientAssayGroupRuleService;
import com.xtt.common.dao.mapper.PatientAssayGroupRuleMapper;
import com.xtt.common.dao.model.PatientAssayGroupRule;
import com.xtt.common.dao.po.PatientAssayGroupRulePO;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

@Service
public class PatientAssayGroupRuleServiceImpl implements IPatientAssayGroupRuleService {

    @Autowired
    private PatientAssayGroupRuleMapper patientAssayGroupRuleMapper;

    /**
     * 添加化验检查分组
     */
    @Override
    public void savePatientAssayGroupRule(List<PatientAssayGroupRulePO> ruleList, String getItemCodeValue, Integer tenantid, Long userid) {

        for (int i = 1; i < ruleList.size(); i++) {
            if (ruleList.get(i).getMinValue() == null) {
                continue;
            }
            PatientAssayGroupRule po = ruleList.get(i);
            // 封装 :创建人创建时间，更改人，更改时间
            DataUtil.setSystemFieldValue(po);
            po.setId(null);
            po.setFkTenantId(tenantid);
            po.setItemCode(getItemCodeValue);
            po.setMaxValue(0.f);
            po.setOperatorId((long) 1);
            po.setRule(1);
            patientAssayGroupRuleMapper.insertSelective(po);
        }

        // 获得最小值，设置最大值
        List<PatientAssayGroupRulePO> PatientAssayGroupRulePOList = this.selectByItemCode(getItemCodeValue);

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
        List<PatientAssayGroupRulePO> tempList = selectByItemCode(itemsCode);
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
     * 修改化验分组规则
     */
    @Override
    public void updateAssayGroupRule(List<PatientAssayGroupRulePO> ruleList, String itemCode) {
        if (CollectionUtils.isNotEmpty(ruleList)) {
            ruleList.forEach(rule -> {
                patientAssayGroupRuleMapper.updateByPrimaryKeySelective(rule);
            });
        }
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
}
