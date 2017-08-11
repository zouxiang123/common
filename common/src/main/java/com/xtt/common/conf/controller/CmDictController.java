/**   
 * @Title: SYSParamController.java 
 * @Package com.xtt.txgl.system.controller
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年12月29日 下午2:03:44 
 *
 */
package com.xtt.common.conf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.assay.service.IDictHospitalLabService;
import com.xtt.common.assay.service.IPatientAssayDictionaryService;
import com.xtt.common.common.service.ICmDictService;
import com.xtt.common.common.service.ICommonCacheService;
import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.CmDict;
import com.xtt.common.dao.model.DictHospitalLab;
import com.xtt.common.dao.po.CmDictPO;
import com.xtt.common.dao.po.DictHospitalLabPO;
import com.xtt.common.dao.po.PatientAssayDictionaryPO;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.UserUtil;

@Controller
@RequestMapping("/system/dictionary/")
public class CmDictController {
    @Autowired
    private ICmDictService cmDictService;
    @Autowired
    private IDictHospitalLabService dictHospitalLabService;
    @Autowired
    private IPatientAssayDictionaryService patientAssayDictionaryService;
    @Autowired
    private ICommonCacheService commonCacheService;

    @RequestMapping("maintain")
    public ModelAndView maintain(@RequestParam(required = true) String sys) {
        ModelAndView model = new ModelAndView("system/dictionary_maintain");
        model.addObject(CmDictConsts.ASSAY_TEXT_TYPE, DictUtil.listByPItemCode(CmDictConsts.ASSAY_TEXT_TYPE));
        model.addObject("sysOwner", sys);
        return model;
    }

    @RequestMapping("delDictionary")
    @ResponseBody
    public Map<String, Object> delDictionary(@RequestParam(value = "dictId", required = true) Long dictId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", cmDictService.deleteByPrimaryKey(dictId));
        commonCacheService.cacheDict(UserUtil.getTenantId());
        return map;
    }

    /**
     * 获取所有的检查项
     * 
     * @Title: getAssayList
     * @param record
     * @return
     * 
     */
    @RequestMapping("getAssayList")
    @ResponseBody
    public Map<String, Object> getAssayList(DictHospitalLabPO record) {
        Map<String, Object> map = new HashMap<String, Object>();
        record.setValueType(DictHospitalLabPO.VALUE_TYPE_NUMBER);
        List<DictHospitalLabPO> list = dictHospitalLabService.getByCondition(record);
        map.put("status", CommonConstants.SUCCESS);
        map.put("items", list);
        return map;
    }

    /** 获取所有的化验单检查类别列表 */
    @RequestMapping("getAssayCategoryList")
    @ResponseBody
    public Map<String, Object> getAssayCategoryList() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", CommonConstants.SUCCESS);

        DictHospitalLabPO condition = new DictHospitalLabPO();
        condition.setFkTenantId(UserUtil.getTenantId());
        condition.setValueType(DictHospitalLabPO.VALUE_TYPE_NUMBER);
        map.put("items", dictHospitalLabService.getAllCategory(condition));
        return map;
    }

    /**
     * 删除关联
     * 
     * @Title: deleteAssayMapping
     * @return
     */
    @RequestMapping("deleteAssayMapping")
    @ResponseBody
    public Map<String, Object> deleteAssayMapping(Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        dictHospitalLabService.deleteAssayMapping(id);
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    /**
     * 更新字典表
     * 
     * @Title: updateDict
     * @return
     */
    @RequestMapping("updateDict")
    @ResponseBody
    public Map<String, Object> updateDict(DictHospitalLabPO record) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(CommonConstants.STATUS, dictHospitalLabService.updateDictById(record));
        return map;
    }

    /**
     * 获取需要关联的字典表数据
     * 
     * @Title: getPatientAssayDict
     * @return
     */
    @RequestMapping("getPatientAssayDict")
    @ResponseBody
    public Map<String, Object> getPatientAssayDict(PatientAssayDictionaryPO record) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", patientAssayDictionaryService.getByFuzzyCondition(record));
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    /** 获取字典表数据 */
    @RequestMapping("getDictionaryList")
    @ResponseBody
    public Map<String, Object> getDictionaryList(CmDictPO entity) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<CmDictPO> list = cmDictService.getByCondition(entity);
        map.put("status", CommonConstants.SUCCESS);
        map.put("items", list);
        return map;
    }

    /** 获取字典表类别列表数据 */
    @RequestMapping("getDictCategoryList")
    @ResponseBody
    public Map<String, Object> getDictionaryCategoryList(CmDictPO entity) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<CmDictPO> list = cmDictService.getDictCategory(entity);
        map.put("items", list);
        map.put("status", CommonConstants.SUCCESS);
        return map;
    }

    /**
     * 更新字典表数据
     * 
     * @Title: updateDictionary
     * @param record
     * @return
     * 
     */
    @RequestMapping("updateDictionary")
    @ResponseBody
    public Map<String, Object> updateDictionary(CmDictPO record) {
        Map<String, Object> map = new HashMap<String, Object>();
        boolean successFlag = true;
        if (record.getId() == null) {// 添加操作
            record.setItemName(record.getItemName().trim());
            if (StringUtils.isNotBlank(DictUtil.getItemName(record.getpItemCode(), record.getItemCode()))
                            || StringUtils.isNotBlank(DictUtil.getItemCode(record.getpItemCode(), record.getItemName()))) {// 检查名字和对应的值是否存在
                map.put("status", CommonConstants.WARNING);
                successFlag = false;
            } else if (checkDictionaryOrderByExists(record)) {
                map.put("status", CommonConstants.FAILURE);
                successFlag = false;
            }
        } else {// 修改操作
            CmDict d = cmDictService.getById(record.getId());
            if (record.getIsEnable() == null) {// 不是更新状态操作
                record.setItemName(record.getItemName().trim());// 去除左右空格
                if (!d.getItemName().equals(record.getItemName()))// 名称发生了变化
                    if (StringUtils.isNotBlank(DictUtil.getItemCode(record.getpItemCode(), record.getItemName()))) {// 检查名字是否存在
                        map.put("status", CommonConstants.WARNING);
                        successFlag = false;
                    }
            }
        }
        if (!successFlag) {
            return map;
        }
        cmDictService.updateDictionary(record);
        commonCacheService.cacheDict(UserUtil.getTenantId());
        map.put("status", CommonConstants.SUCCESS);
        return map;
    }

    /**
     * 当前类别下是否存在相同的排序
     * 
     * @Title: checkDictionaryOrderBy
     * @param record
     * @return
     * 
     */
    private boolean checkDictionaryOrderByExists(CmDictPO record) {
        CmDictPO query = new CmDictPO();
        query.setpItemCode(record.getpItemCode());
        query.setOrderBy(record.getOrderBy());
        List<CmDictPO> list = cmDictService.getByCondition(query);
        if (list == null || list.size() == 0) {
            return false;
        }
        return true;
    }

    /**
     * 刷新缓存
     * 
     * @Title: refreshDictionary
     * @return
     */
    @RequestMapping("refresh")
    @ResponseBody
    public Map<String, Object> refreshDictionary() {
        Map<String, Object> map = new HashMap<String, Object>();
        commonCacheService.cacheDict(UserUtil.getTenantId());
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    /**
     * 查询是否为常用项
     * 
     * @Title: selectTop
     * @param record
     * @return
     *
     */
    @RequestMapping("selectTop")
    @ResponseBody
    public Map<String, Object> selectTop(DictHospitalLab record) {
        record.setFkTenantId(UserUtil.getTenantId());
        Map<String, Object> map = new HashMap<String, Object>();
        DictHospitalLab dic = dictHospitalLabService.selectTop(record);
        // 默认标志
        String resultFlag = CommonConstants.FAILURE;
        if (dic.getIsTop() != null && dic.getIsTop()) {
            resultFlag = CommonConstants.SUCCESS;
        }
        map.put(CommonConstants.STATUS, resultFlag);
        return map;
    }

    /**
     * 获取置顶的检查项
     * 
     * @Title: getAssayListByTop
     * @param record
     * @return
     * 
     */
    @RequestMapping("getAssayListByTop")
    @ResponseBody
    public Map<String, Object> getAssayListByTop() {
        Map<String, Object> map = new HashMap<String, Object>();
        DictHospitalLabPO record = new DictHospitalLabPO();
        record.setFkTenantId(UserUtil.getTenantId());
        record.setIsTop(Boolean.TRUE);
        List<DictHospitalLabPO> list = dictHospitalLabService.getByCondition(record);
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        map.put("items", list);
        return map;
    }
}
