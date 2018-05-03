package com.xtt.common.assay.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.assay.service.IPatientAssayGroupRuleService;
import com.xtt.common.assay.vo.PatientAssayGroupRuleListVO;
import com.xtt.common.assay.vo.SortComparatorImpl;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.po.PatientAssayGroupRulePO;

@Controller
@RequestMapping("/assay/patientAssayGroupRule")
public class PatientAssayGroupRuleController {

    @Autowired
    private IPatientAssayGroupRuleService patientAssayGroupRuleService;

    /**
     * 跳转到化验配置规则
     * 
     * @return
     */
    @RequestMapping("assayCheckGrouping")
    public ModelAndView assayCheckGrouping() {
        ModelAndView model = new ModelAndView("cm/assay/assayCheckGrouping");
        return model;
    }

    /**
     * 保存检查项规则
     * 
     * @param patientAssayGroupRule
     */
    @RequestMapping(value = "savePatientAssayGroupRule")
    @ResponseBody
    public Map<String, String> savePatientAssayGroupRule(PatientAssayGroupRuleListVO tempvo) throws Exception {
        patientAssayGroupRuleService.saveGroupRule(tempvo.getPatientAssayGroupRuleList(), tempvo.getItemCode(), tempvo.getAssayHospDict());
        Map<String, String> map = new HashMap<String, String>();
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    /**
     * /** 删除化验分组规则值
     */
    @RequestMapping("deleteAssayGroupValue")
    public Map<String, String> deleteAssayGroupValue(PatientAssayGroupRuleListVO tempvo, String itemCode) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        try {
            // 先删除所有ItemCode =传进来的itemCode的数据
            patientAssayGroupRuleService.deleteByItemCode(itemCode);
            // 再新增
            map.put("result", "success");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", "fail");
            return map;
        }
    }

    /**
     * 查询所有的化验分组规则
     * 
     * @return
     */
    @RequestMapping("selectAllAssayGroupRule")
    @ResponseBody
    public List<PatientAssayGroupRulePO> selectAllAssayGroupRule(String itemsCode) {
        return patientAssayGroupRuleService.selectAllAssayGroupRule(itemsCode);
    }

    /**
     * 通过传进来的值来判断数据库中是否存在
     * 
     * @param inputValue
     * @return
     */
    @RequestMapping("selectExistByInput")
    public Map<String, String> selectExistByInput(Float getValue, String getItemCode) {
        Map<String, String> map = new HashMap<String, String>();
        int result = patientAssayGroupRuleService.selectExitsByInput(getValue, getItemCode);
        if (result == 1) {
            map.put("Result", "success");
        } else {
            map.put("Result", "fail");
        }
        return map;
    }

    /**
     * 排序分组规则
     * 
     * @return
     */
    @RequestMapping("sortAssayGroupRule")
    @ResponseBody
    public List<PatientAssayGroupRulePO> sortAssayGroupRule(PatientAssayGroupRuleListVO tempvo) {

        // 传进来的集合
        List<PatientAssayGroupRulePO> patientAssayGroupRulePOListBySelect = tempvo.getPatientAssayGroupRuleList();

        for (int i = 0; i < patientAssayGroupRulePOListBySelect.size(); i++) {
            if (patientAssayGroupRulePOListBySelect.get(i).getMinValue() == null && patientAssayGroupRulePOListBySelect.get(i).getId() == null) {
                patientAssayGroupRulePOListBySelect.remove(i);
                if (i > 0) {
                    i = i - 1;
                }

            }
        }
        // 排序再返回到前台
        Collections.sort(patientAssayGroupRulePOListBySelect, new SortComparatorImpl());
        return patientAssayGroupRulePOListBySelect;
    }
}
