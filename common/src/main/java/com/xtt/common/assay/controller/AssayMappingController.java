/**   
 * @Title: SYSParamController.java 
 * @Package com.xtt.txgl.system.controller
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年12月29日 下午2:03:44 
 *
 */
package com.xtt.common.assay.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.assay.service.IAssayHospDictService;
import com.xtt.common.assay.service.IPatientAssayDictionaryService;
import com.xtt.common.constants.AssayEnum;
import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.po.AssayHospDictPO;
import com.xtt.common.dao.po.PatientAssayDictionaryPO;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.http.HttpResult;

@Controller
@RequestMapping("/assay/assayMapping/")
public class AssayMappingController {
    @Autowired
    private IAssayHospDictService assayHospDictService;
    @Autowired
    private IPatientAssayDictionaryService patientAssayDictionaryService;

    /**
     * 跳转到化验关联配置页面
     * 
     * @Title: view
     * @return
     *
     */
    @RequestMapping("view")
    public ModelAndView view() {
        ModelAndView model = new ModelAndView("cm/assay/assay_mapping");
        model.addObject(CmDictConsts.ASSAY_TEXT_TYPE, DictUtil.listByPItemCode(CmDictConsts.ASSAY_TEXT_TYPE));
        return model;
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
        assayHospDictService.deleteAssayMapping(id);
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
    public Map<String, Object> updateDict(AssayHospDictPO record) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(CommonConstants.STATUS, assayHospDictService.updateDictById(record));
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
        List<PatientAssayDictionaryPO> list = patientAssayDictionaryService.getByFuzzyCondition(record);
        if (CollectionUtils.isNotEmpty(list)) {
            for (Iterator<PatientAssayDictionaryPO> it = list.iterator(); it.hasNext();) {
                PatientAssayDictionaryPO par = it.next();
                if (Objects.equals(AssayEnum.preBUN.getValue(), par.getItemCode())
                                || Objects.equals(AssayEnum.afterBUN.getValue(), par.getItemCode())) {// 不能关联透前透后血尿素氮
                    it.remove();
                }
            }
        }
        map.put("items", list);
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    /**
     * 获取血透字典列表
     * 
     * @Title: getXttDictList
     * @return
     */
    @RequestMapping("getXttDictList")
    @ResponseBody
    public HttpResult getXttDictList(PatientAssayDictionaryPO record) {
        HttpResult result = HttpResult.getSuccessInstance();
        List<PatientAssayDictionaryPO> list = patientAssayDictionaryService.listByCondition(record);
        result.setRs(list);
        return result;
    }

    /**
     * 自动关联
     * 
     * @Title: autoMapping
     * @return
     */
    @RequestMapping("autoMapping")
    @ResponseBody
    public HttpResult autoMapping() {
        HttpResult result = HttpResult.getSuccessInstance();
        assayHospDictService.autoMappingDict(UserUtil.getTenantId());
        return result;
    }

    /**
     * 建立映射关系
     * 
     */
    @RequestMapping("mapping")
    @ResponseBody
    public HttpResult mapping(AssayHospDictPO record) {
        HttpResult result = HttpResult.getSuccessInstance();
        if (StringUtils.isEmpty(record.getFkDictCode()) || CollectionUtils.isEmpty(record.getIds())) {
            result.setStatus(HttpResult.WARNING);
            result.setErrmsg("需要建立关联的双方不能为空");
            return result;
        }
        assayHospDictService.saveMappingDictByIds(record);
        return result;
    }

}
