package com.xtt.common.assay.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.assay.service.IAssayGroupService;
import com.xtt.common.assay.service.IDictHospitalLabService;
import com.xtt.common.assay.service.IPatientAssayClassService;
import com.xtt.common.assay.service.IPatientAssayMonthRScopeService;
import com.xtt.common.assay.vo.PatientAssayClassVO;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.AssayGroupConf;
import com.xtt.common.dao.model.AssayGroupConfDetail;
import com.xtt.common.dao.po.AssayGroupConfPO;
import com.xtt.common.dao.po.DictHospitalLabPO;
import com.xtt.common.dao.po.PatientAssayClassPO;
import com.xtt.common.dao.po.PatientAssayMonthRScopePO;
import com.xtt.common.util.UserUtil;

@Controller
@RequestMapping("/assay/assayRemindDict")
public class AssayRemindDictController {

    @Autowired
    private IDictHospitalLabService dictHospitalLabService;

    // 病患化验类
    @Autowired
    private IPatientAssayClassService assayClassService;

    // 病患化验里面的月patient_assay_month_r_scope表
    @Autowired
    private IPatientAssayMonthRScopeService patientAssayMonthRScopeService;
    // 病患化验类
    @Autowired
    private IAssayGroupService assayGroupService;

    @RequestMapping("view")
    public ModelAndView view() {
        ModelAndView mav = new ModelAndView("assay/assay_remind_dict");
        /*List<DictionaryPO> tempList = DictionaryUtil.getListByType(DictionaryConstants.ASSAYTYPE);
        mav.addObject("listDictionaryPO", tempList);*/
        return mav;
    }

    /**
     * 根据化验类查询所有的化验项
     * 
     * @Title: selectAllDictHospitalLab
     * @return
     * 
     */
    @RequestMapping("selectAssayByClass")
    public Map<String, Object> selectAssayByClass(String assayClass) {
        // 查询化验单
        DictHospitalLabPO condition = new DictHospitalLabPO();
        condition.setFkTenantId(UserUtil.getTenantId());
        condition.setAssayClass(assayClass);
        List<DictHospitalLabPO> categoryList = dictHospitalLabService.selectAllCategoryByClass(condition);
        List<DictHospitalLabPO> itemList = dictHospitalLabService.selectAllItemByClass(condition);
        itemList.addAll(0, categoryList);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("itemList", itemList);
        return map;
    }

    /* add by chenguanghao end */

    /**
     * 添加化验病患类
     * 
     * @requestBody代表请求过来的是Json格式
     * @return
     */
    @RequestMapping("savePatientAssayClass")
    public Map<String, String> savePatientAssayClass(@RequestBody PatientAssayClassVO vo) {
        Map<String, String> map = new HashMap<String, String>();
        // 删除表中所有的数据
        assayClassService.deleteAllByAssayClass(vo.getAssayClass());
        // 添加数据
        assayClassService.savePatientAssayClass(vo.getDetailList());
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    /**
     * 查询所有的病患化验类
     */
    @RequestMapping("selectAllPatientAssayClass")
    public Map<String, Object> selectAllPatientAssayClass(@RequestParam String assayClass) {
        System.out.println("assayClass");
        Map<String, Object> map = new HashMap<String, Object>();
        List<PatientAssayClassPO> assayClassData = assayClassService.selectAllByAssayClass(assayClass);
        List<PatientAssayClassPO> assayClassGroupNameData = assayClassService.selectAllGroupName(assayClass);
        map.put("assayClassData", assayClassData);
        map.put("assayClassGroupNameData", assayClassGroupNameData);
        return map;
    }

    /*
     * 查询patient_assay_month_r_scope表的所有数据
     */
    @RequestMapping("selectAllMonth")
    public Map<String, Object> selectAllMonth() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<PatientAssayMonthRScopePO> PatientAssayMonthRScopePOList = patientAssayMonthRScopeService.selectAll();
        map.put("PatientAssayMonthRScopePOList", PatientAssayMonthRScopePOList);
        return map;
    }

    /*
     * 保存patient_assay_month_r_scope
     */
    @RequestMapping("saveAssayMonth")
    public Map<String, String> saveAssayMonth(@RequestBody String[] assayClass) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            patientAssayMonthRScopeService.updateAssayMonth(assayClass);
            map.put("result", "success");
        } catch (Exception e) {
            map.put("result", "fail");
        }
        return map;
    }

    /**
     * 修改化验开始时间结束时间
     */
    /*
    @RequestMapping("updateStartTimeAndEndTime")
    @ResponseBody
    public void updateStartTimeAndEndTime(String startDay, String endDay) {
    try {
    	patientAssayMonthRScopeService.updateStartTimeAndEndTime(startDay, endDay);
    } catch (ParseException e) {
    	e.printStackTrace();
    }
    }*/

    /**
     * 查询化验项及选中的分组项
     */
    @RequestMapping("selectAllItemByGroupDetail")
    public Map<String, Object> selectAllItemByGroupDetail(Long id) {

        AssayGroupConfDetail agcd = new AssayGroupConfDetail();
        agcd.setFkAssayGroupConfId(id);
        // 查询全部
        List<DictHospitalLabPO> allDictHospitalLabPOList = dictHospitalLabService.selectAllItemByGroupDetail(agcd);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("itemList", allDictHospitalLabPOList);
        if (id != null) {
            AssayGroupConf assayGroupConf = assayGroupService.selectByPrimaryKey(id);
            map.put("assayGroupConf", assayGroupConf);
        }
        return map;
    }

    /*
     * 查询所有同类分组
     */
    @RequestMapping("selectAssayGroupConfAll")
    @ResponseBody
    public Map<String, Object> selectAssayGroupConfAll() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", assayGroupService.selectByCondition(new AssayGroupConf()));
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    /**
     * 查询所有的父节点
     */
    @RequestMapping("selectAssayGroupConfDetail")
    public Map<String, Object> selectAllDictHospitalLab() {
        // 查询父节点
        List<DictHospitalLabPO> dictHospitalLabPOList = dictHospitalLabService.selectGroupName();
        // 查询全部
        List<DictHospitalLabPO> allDictHospitalLabPOList = dictHospitalLabService.selectAll();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dictHospitalLabPOList", dictHospitalLabPOList);
        map.put("allDictHospitalLabPOList", allDictHospitalLabPOList);
        return map;
    }

    /*
     * 查询同类分组
     */
    @RequestMapping("selectAssayGroupConfById")
    @ResponseBody
    public Map<String, Object> selectAssayGroupConfById(Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("agc", assayGroupService.selectByPrimaryKey(id));
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    /*
     * 删除同类分组
     */
    @RequestMapping("deleteAssayGroupConf")
    @ResponseBody
    public Map<String, Object> deleteAssayGroupConf(Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        assayGroupService.deleteByPrimaryKey(id);
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    /*
     * 保存同类分组
     */
    @RequestMapping("saveAssayGroupConf")
    @ResponseBody
    public Map<String, Object> saveAssayGroupConf(@RequestBody AssayGroupConfPO record) {
        Map<String, Object> map = new HashMap<String, Object>();
        assayGroupService.save(record);
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    /*
     * 保存同类分组
     */
    @RequestMapping("selectAllByAssayClass")
    @ResponseBody
    public Map<String, Object> selectAllByAssayClass(String assayClass) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<PatientAssayClassPO> list = assayClassService.selectAllByAssayClass(assayClass);
        map.put("list", list);
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

}
