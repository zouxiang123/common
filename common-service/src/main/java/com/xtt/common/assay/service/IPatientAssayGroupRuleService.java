package com.xtt.common.assay.service;

import java.util.List;

import com.xtt.common.dao.model.PatientAssayGroupRule;
import com.xtt.common.dao.po.AssayHospDictPO;
import com.xtt.common.dao.po.PatientAssayGroupRulePO;

public interface IPatientAssayGroupRuleService {
    /**
     * 保存化验分组规则
     * 
     * @Title: saveGroupRule
     * @param ruleList
     * @param itemCode
     * @param assayHospDict
     *
     */
    void saveGroupRule(List<PatientAssayGroupRulePO> ruleList, String itemCode, AssayHospDictPO assayHospDict);

    /**
     * 批量新增
     */
    void saveBatch(PatientAssayGroupRulePO[] patientAssayGroupRulePO);

    /**
     * 删除化验分组规则的值
     */
    void deleteGroupRuleValue(Long id, String itemCode);

    /**
     * 查询所有化验分组规则
     * 
     * @return
     */
    List<PatientAssayGroupRulePO> selectAllAssayGroupRule(String itemsCode);

    /**
     * 通过ID来查询单个对象
     * 
     * @param id
     */
    PatientAssayGroupRulePO selectPatientAssayGroupRulePOById(Long id);

    /**
     * 通过传进来的值在数据库中是否存在
     * 
     * @param inputValue
     * @return
     */
    int selectExitsByInput(Float inputValue, String getItemCode);

    /**
     * 通过ItemCode来查询
     * 
     * @param itemCode
     * @return
     */
    List<PatientAssayGroupRulePO> selectByItemCode(String itemCode);

    /**
     * 根据条件查询患者化验项的分组规则
     * 
     * @author GuangHao
     * @param record
     * @return
     */
    public List<PatientAssayGroupRulePO> selectByCondition(PatientAssayGroupRule record);

    /**
     * 删除所有关于传进来的ItemCode的数据
     * 
     * @param itemCode
     */
    void deleteByItemCode(String itemCode);

    /**
     * 根据itemCode获取对应的分组规则
     * 
     * @Title: listByItemCode
     * @param itemCode
     * @return
     *
     */
    List<PatientAssayGroupRulePO> listByItemCode(String itemCode);

    /**
     * 保存化验分组规则
     * 
     * @Title: saveGroupRule
     * @param ruleList
     * @param assayHospDict
     *
     */
    void saveGroupRule(List<PatientAssayGroupRulePO> ruleList, AssayHospDictPO assayHospDict);

}
