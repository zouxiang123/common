/**

 */
package com.xtt.common.assay.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.assay.service.IDictHospitalLabService;
import com.xtt.common.constants.AssayConsts;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.po.DictHospitalLabPO;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.PinyinUtil;

/**
 * @author liujun
 * @2016年5月27日
 * 
 */
@Controller
@RequestMapping("/dictHospitalLab/")
public class DictHospitalLabController {

    @Autowired
    private IDictHospitalLabService dictHospitalLabService;

    /*化验单维护界面 */
    @RequestMapping("selectAllGroup")
    public ModelAndView selectAllGroup() {
        ModelAndView model = new ModelAndView("patient/patient_assay_record_maintain");
        List<DictHospitalLabPO> list = dictHospitalLabService.selectAdminGroup();
        model.addObject("allDictHospitalLabPO", list);
        model.addObject("status", CommonConstants.SUCCESS);
        return model;
    }

    @RequestMapping("selectAllByItemCode")
    @ResponseBody
    public List<DictHospitalLabPO> selectAllByItemCode(String itemCode) {
        return dictHospitalLabService.selectAllByItemCode(itemCode);
    }

    /**
     * 修改检查项规则的PersonalMinValue，isTop,PersonalMaxValue,
     */
    @RequestMapping("updateDictHospitalLabSomeValue")
    public void updateDictHospitalLabSomeValue(DictHospitalLabPO pictHospitalLabPO) {
        dictHospitalLabService.updateDictHospitalLabSomeValue(pictHospitalLabPO);
    }

    /**
     * 手动添加化验项
     * 
     * @param record
     * @return
     */
    @RequestMapping("insertSelective")
    @ResponseBody
    public Map<String, Object> insertSelective(DictHospitalLabPO record) {
        Map<String, Object> map = new HashMap<String, Object>();
        String groupId = PinyinUtil.getPinyin(record.getGroupName());
        record.setGroupId(groupId);
        record.setFkTenantId(UserUtil.getTenantId());
        DataUtil.setSystemFieldValue(record);
        int groupNameCount = queryByGroupName(record);
        int itemCodeCount = queryByItemCode(record);
        if (groupNameCount > 0) {
            map.put("repetitionGroupName", AssayConsts.REPETITION_GROUP_NAME);
            return map;
        } else if (itemCodeCount > 0) {
            map.put("repetitionItemCode", AssayConsts.REPETITION_ITEM_CODE);
            return map;
        } else {
            dictHospitalLabService.insertSelective(record);
            map.put("status", CommonConstants.SUCCESS);
        }
        return map;
    }

    /**
     * 校验化验项名称是否与his系统重复
     * 
     * @param record
     * @return
     */
    public int queryByGroupName(DictHospitalLabPO record) {
        return dictHospitalLabService.queryByGroupName(record);
    }

    /**
     * 校验化验项 项目代号是否重复
     * 
     * @param record
     * @return
     */
    public int queryByItemCode(DictHospitalLabPO record) {
        return dictHospitalLabService.queryByItemCode(record);
    }

    /**
     * 手动添加化验项的id
     * 
     * @param record
     * @return
     */
    @RequestMapping("getDictId")
    @ResponseBody
    public Long getDictId(DictHospitalLabPO record) {
        return dictHospitalLabService.getDictId(record);
    }

    /**
     * 删除化验单
     * 
     * @param id
     * @return
     */
    @RequestMapping("deleteById")
    @ResponseBody
    public int deleteById(Long id) {
        return dictHospitalLabService.deleteById(id);
    }

    /**
     * @param record
     * @return
     */
    @RequestMapping("updateAssay")
    @ResponseBody
    public Map<String, Object> updateAssay(DictHospitalLabPO record) {
        Map<String, Object> map = new HashMap<String, Object>();
        String groupId = PinyinUtil.getPinyin(record.getGroupName());
        record.setGroupId(groupId);
        int groupNameCount = queryByGroupName(record);
        int itemCodeCount = queryByItemCode(record);
        if (groupNameCount > 0) {
            map.put("repetitionGroupName", AssayConsts.REPETITION_GROUP_NAME);
            return map;
        } else if (itemCodeCount > 0) {
            map.put("repetitionItemCode", AssayConsts.REPETITION_ITEM_CODE);
            return map;
        } else {
            int num = dictHospitalLabService.updateDictHospital(record);
            if (num > 0) {
                map.put("status", CommonConstants.SUCCESS);
            } else {
                map.put("status", CommonConstants.FAILURE);
            }
        }
        return map;
    }

}
