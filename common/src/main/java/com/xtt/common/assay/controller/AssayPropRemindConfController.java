package com.xtt.common.assay.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.assay.consts.AssayConsts;
import com.xtt.common.assay.service.IAssayHospDictService;
import com.xtt.common.assay.service.IPatientAssayPropConfService;
import com.xtt.common.dao.model.PatientAssayPropConf;
import com.xtt.common.dao.po.AssayHospDictPO;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.http.HttpResult;

@Controller
@RequestMapping("/assayPropRemindConf/")
public class AssayPropRemindConfController {
    @Autowired
    private IAssayHospDictService assayHospDictService;
    @Autowired
    private IPatientAssayPropConfService assayPropConfService;

    @RequestMapping("view")
    public ModelAndView view() {
        ModelAndView mav = new ModelAndView("cm/assay/assay_prop_remind_conf");
        return mav;
    }

    /**
     * 获取字典数据列表
     * 
     * @Title: getDictList
     * @return
     *
     */
    @RequestMapping("getDictList")
    @ResponseBody
    public HttpResult getDictList() {
        HttpResult result = HttpResult.getSuccessInstance();
        Integer tenantId = UserUtil.getTenantId();
        PatientAssayPropConf propConfrecord = new PatientAssayPropConf();
        propConfrecord.setFkTenantId(tenantId);
        List<PatientAssayPropConf> list = assayPropConfService.listByCond(propConfrecord);
        Set<String> classCode = new HashSet<>();
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(pac -> {
                classCode.add(pac.getItemCode());
            });
        }
        AssayHospDictPO record = new AssayHospDictPO();
        record.setFkTenantId(tenantId);
        List<AssayHospDictPO> dictList = assayHospDictService.listProcessedItemCodeRec(record);
        dictList.forEach(dict -> {
            String newItemCode = dict.getItemCode().replace(AssayConsts.DICT_UK_SUFFIX, "");
            // 已经选中已经添加的项目
            dict.setSelectFlag(classCode.contains(newItemCode));
            // 显示用itemCode,如果是血透唯一标识的item_code，去除其后缀
            dict.setItemCodeShow(newItemCode);
        });
        result.setRs(dictList);
        return result;
    }

    /**
     * 获取提醒配置项目列表
     * 
     * @Title: getList
     * @return
     *
     */
    @RequestMapping("getList")
    @ResponseBody
    public HttpResult getList() {
        HttpResult result = HttpResult.getSuccessInstance();
        Integer tenantId = UserUtil.getTenantId();
        PatientAssayPropConf record = new PatientAssayPropConf();
        record.setFkTenantId(tenantId);
        List<PatientAssayPropConf> list = assayPropConfService.listByCond(record);
        result.setRs(list);
        return result;
    }

    /**
     * 
     * @Title: save
     * @param list
     * @return
     *
     */
    @RequestMapping("save")
    public HttpResult save(@RequestBody List<PatientAssayPropConf> list) {
        HttpResult result = HttpResult.getSuccessInstance();
        // 添加数据
        assayPropConfService.insertBatch(list);
        // 刷新化验提醒项数据
        // refreshAssayNewestData();
        return result;
    }

    /**
     * 刷新化验提醒项数据
     * 
     * @Title: refreshAssayNewestData
     *
     *//*
      private void refreshAssayNewestData() {
      LoginUser user = UserUtil.getLoginUser();
      new Thread(() -> {
      UserUtil.setThreadLoginUser(user);
      patientAssayNewstService.insertAuto(user.getTenantId());
      }).start();
      }*/
}
