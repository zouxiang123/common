/**

 */
package com.xtt.common.assay.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.assay.service.IAssayHospDictGroupMappingService;
import com.xtt.common.assay.service.IAssayHospDictGroupService;
import com.xtt.common.assay.service.IAssayHospDictService;
import com.xtt.common.constants.AssayConsts;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.AssayHospDictGroup;
import com.xtt.common.dao.po.AssayHospDictPO;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.PinyinUtil;
import com.xtt.platform.util.http.HttpResult;
import com.xtt.platform.util.lang.StringUtil;

/**
 * @author liujun
 * @2016年5月27日
 * 
 */
@Controller
@RequestMapping("/assay/hospDict")
public class AssayHospDictController {

    @Autowired
    private IAssayHospDictService assayHospDictService;

    @Autowired
    private IAssayHospDictGroupService assayHospDictGroupService;
    @Autowired
    private IAssayHospDictGroupMappingService assayHospDictGroupMappingService;

    /**
     * 跳转到手动维护页面
     * 
     * @Title: manualView
     * @return
     *
     */
    @RequestMapping("manualView")
    public ModelAndView manualView() {
        ModelAndView model = new ModelAndView("cm/assay/assay_dict_manual");
        model.addAllObjects(listAllManualAdd());
        return model;
    }

    /**
     * 获取所有手动添加的项目
     * 
     * @Title: listAllManualAdd
     * @return
     *
     */
    @RequestMapping("listAllManualAdd")
    public Map<String, Object> listAllManualAdd() {
        Map<String, Object> map = new HashMap<>();
        List<AssayHospDictPO> list = assayHospDictService.listAllManualAdd();
        map.put("allDictHospitalLabPO", list);
        map.put("status", CommonConstants.SUCCESS);
        return map;
    }

    /**
     * 根据itemCode获取数据
     * 
     * @Title: selectAllByItemCode
     * @param itemCode
     * @return
     *
     */
    @RequestMapping("getByItemCode")
    @ResponseBody
    public HttpResult getByItemCode(String itemCode) {
        HttpResult result = HttpResult.getSuccessInstance();
        result.setRs(assayHospDictService.getByItemCode(itemCode));
        return result;
    }

    /**
     * 手动添加化验项
     * 
     * @param record
     * @return
     */
    @RequestMapping("insertSelective")
    @ResponseBody
    public HttpResult insertSelective(AssayHospDictPO record) {
        HttpResult result = HttpResult.getSuccessInstance();
        record.setFkTenantId(UserUtil.getTenantId());
        DataUtil.setSystemFieldValue(record);
        int itemCodeCount = assayHospDictService.queryByItemCodeandGroupId(record);
        if (itemCodeCount > 0) {
            result.setStatus(HttpResult.WARNING);
            result.setErrmsg("化验单存在相同的化验项");
            return result;
        } else {
            assayHospDictService.insertSelective(record);
        }
        return result;
    }

    /**
     * 校验化验项名称是否与his系统重复
     * 
     * @param record
     * @return
     */
    public int queryByGroupName(AssayHospDictPO record) {
        return assayHospDictService.queryByGroupName(record);
    }

    /**
     * 校验化验项 项目代号是否重复
     * 
     * @param record
     * @return
     */
    public int queryByItemCode(AssayHospDictPO record) {
        return assayHospDictService.queryByItemCode(record);
    }

    /**
     * 手动添加化验项的id
     * 
     * @param record
     * @return
     */
    @RequestMapping("getDictId")
    @ResponseBody
    public Long getDictId(AssayHospDictPO record) {
        return assayHospDictService.getDictId(record);
    }

    /**
     * 删除化验单
     * 
     * @param id
     * @return
     */
    @RequestMapping("deleteById")
    @ResponseBody
    public int deleteById(AssayHospDictPO record) {
        assayHospDictGroupMappingService.deleteByGroupIdAndItemCode(record.getGroupId(), record.getItemCode(), UserUtil.getTenantId());
        return 0;
    }

    /**
     * @param record
     * @return
     */
    @RequestMapping("updateAssay")
    @ResponseBody
    public HttpResult updateAssay(AssayHospDictPO record) {
        HttpResult result = HttpResult.getSuccessInstance();
        // 当化验单发生改变时,查询当前化验单是否存在化验项
        if (!StringUtil.equals(record.getItemCode(), record.getOldItemCode())) {
            Integer itemCodeandGroupIdCount = assayHospDictService.queryByItemCodeandGroupId(record);
            if (itemCodeandGroupIdCount > 0) {
                result.setStatus(HttpResult.WARNING);
                result.setErrmsg("项目在当前化验单中已存在");
                return result;
            }
        }
        // 更新化验项目
        assayHospDictService.updateAssay(record);
        return result;
    }

    /**
     * 查询手动生成的化验分组
     * 
     * @Title: listManualAddGroup
     * @return
     *
     */
    @RequestMapping("listManualAddGroup")
    @ResponseBody
    public Map<String, Object> listManualAddGroup() {
        Map<String, Object> map = new HashMap<>();
        List<AssayHospDictGroup> listAssayHospDictGroup = assayHospDictGroupService.listByIsAuto(false);
        map.put("items", listAssayHospDictGroup);
        map.put("status", CommonConstants.SUCCESS);
        return map;
    }

    /**
     * 
     * @Title: insertGroupName
     * @param assay
     * @return
     *
     */
    @RequestMapping("insertGroupName")
    @ResponseBody
    public HttpResult insertGroupName(AssayHospDictPO assay) {
        assay.setGroupId(PinyinUtil.getPinyin(assay.getGroupName()));
        assay.setFkTenantId(UserUtil.getTenantId());
        int groupNameCount = assayHospDictGroupService.getCountByGroupId(assay.getGroupId());
        HttpResult result = HttpResult.getSuccessInstance();
        // 当化验单发生改变时,查询当前化验单是否存在化验项
        if (groupNameCount > 0) {
            result.setStatus(HttpResult.WARNING);
            result.setErrmsg("His系统已存在该化验单名称");
            return result;
        } else {
            AssayHospDictGroup assayHospDictGroup = new AssayHospDictGroup();
            assayHospDictGroup.setGroupId(assay.getGroupId());
            assayHospDictGroup.setGroupName(assay.getGroupName());
            assayHospDictGroup.setFkTenantId(UserUtil.getTenantId());
            assayHospDictGroup.setIsAuto(false);
            DataUtil.setSystemFieldValue(assayHospDictGroup);
            assayHospDictGroupService.insert(assayHospDictGroup);
            return result;
        }
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
    public Map<String, Object> getAssayList(AssayHospDictPO record) {
        Map<String, Object> map = new HashMap<String, Object>();
        // record.setValueType(DictHospitalLabPO.VALUE_TYPE_NUMBER);
        List<AssayHospDictPO> list = assayHospDictService.getByCondition(record);
        map.put("status", CommonConstants.SUCCESS);
        map.put("items", list);
        return map;
    }

    /**
     * 根据自定义条件查询不包含groupId的数据
     * 
     * @Title: listAllBasic
     * @param record
     * @return
     * 
     */
    @RequestMapping("listAllBasic")
    @ResponseBody
    public Map<String, Object> listAllBasic() {
        Map<String, Object> map = new HashMap<String, Object>();
        AssayHospDictPO record = new AssayHospDictPO();
        record.setFkTenantId(UserUtil.getTenantId());
        List<AssayHospDictPO> list = assayHospDictService.listBasicByCondition(record);
        map.put("status", CommonConstants.SUCCESS);
        map.put("items", list);
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
    @RequestMapping("listTop")
    @ResponseBody
    public Map<String, Object> listTop() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<AssayHospDictPO> list = assayHospDictService.listByIsTop(true);
        map.put("status", CommonConstants.SUCCESS);
        map.put("items", list);
        return map;
    }

    /** 获取所有的化验单检查类别列表 */
    @RequestMapping("getAssayCategoryList")
    @ResponseBody
    public Map<String, Object> getAssayCategoryList() {
        Map<String, Object> map = new HashMap<String, Object>();

        AssayHospDictPO condition = new AssayHospDictPO();
        condition.setFkTenantId(UserUtil.getTenantId());
        condition.setValueType(AssayConsts.VALUE_TYPE_NUMBER);
        map.put("items", assayHospDictService.getAllCategory(condition));
        map.put("status", CommonConstants.SUCCESS);
        return map;
    }

    /**
     * 手动添加化验项
     * 
     * @param record
     * @return
     */
    @RequestMapping("saveDict")
    @ResponseBody
    public HttpResult saveDict(AssayHospDictPO record) {
        HttpResult result = HttpResult.getSuccessInstance();
        if (StringUtil.isBlank(record.getItemCode()) || StringUtil.isBlank(record.getGroupId())) {
            result.setStatus(HttpResult.WARNING);
            result.setErrmsg("化验项编号及化验单名称不能为空");
            return result;
        }
        boolean needCheck = true;
        if (StringUtil.isNotBlank(record.getOldItemCode()) && Objects.equals(record.getItemCode(), record.getOldItemCode())) {// 更新操作,但没有变更itemCode
            needCheck = false;
        }
        if (needCheck) {
            record.setFkTenantId(UserUtil.getTenantId());
            if (assayHospDictService.queryByItemCodeandGroupId(record) > 0) {// 判断当前分组下itemCode是否存在
                result.setStatus(HttpResult.WARNING);
                result.setErrmsg("化验单存在相同的化验项");
                return result;
            }
            // 检测项目代号是否与his系统重复
            record.setIsAuto(true);
            if (assayHospDictService.queryByItemCode(record) > 0) {
                result.setStatus(HttpResult.WARNING);
                result.setErrmsg("HIS系统中已存在对应的itemCode：" + record.getItemCode());
                return result;
            }
        }
        if (StringUtil.isNotBlank(record.getOldItemCode())) {
            assayHospDictService.updateAssay(record);
        } else {
            record.setIsAuto(false);
            DataUtil.setAllSystemFieldValue(record);
            assayHospDictService.insertManual(record);
        }
        return result;
    }

    /**
     * 根据组id和itemCode查询数据
     * 
     * @Title: getByItemCode
     * @param groupId
     * @param itemCode
     * @return
     *
     */
    @RequestMapping("getByGroupIdAndItemCode")
    @ResponseBody
    public HttpResult getByItemCode(String groupId, String itemCode) {
        HttpResult result = HttpResult.getSuccessInstance();
        AssayHospDictPO record = new AssayHospDictPO();
        record.setGroupId(groupId);
        record.setItemCode(itemCode);
        result.setRs(assayHospDictService.getByGroupIdAndItemCode(record));
        return result;
    }

    /**
     * 根据分组id和itemCode删除化验单数据
     * 
     * @param id
     * @return
     */
    @RequestMapping("deleteByGroupIdAndCode")
    @ResponseBody
    public HttpResult deleteByGroupIdAndCode(AssayHospDictPO record) {
        HttpResult result = HttpResult.getSuccessInstance();
        assayHospDictGroupMappingService.deleteByGroupIdAndItemCode(record.getGroupId(), record.getItemCode(), UserUtil.getTenantId());
        return result;
    }

}
