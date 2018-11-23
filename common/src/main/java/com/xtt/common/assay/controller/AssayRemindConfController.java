package com.xtt.common.assay.controller;

import java.util.Date;
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

import com.xtt.common.assay.service.IAssayHospDictService;
import com.xtt.common.assay.service.IPatientAssayClassService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.PatientAssayClass;
import com.xtt.common.dao.model.ReportExConfig;
import com.xtt.common.dao.po.AssayHospDictPO;
import com.xtt.common.dao.po.PatientAssayClassPO;
import com.xtt.common.dto.LoginUser;
import com.xtt.common.report.service.IPatientAssayNewstService;
import com.xtt.common.report.service.IReportExceptionService;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.http.HttpResult;

@Controller
@RequestMapping("/assay/assayRemindConf/")
public class AssayRemindConfController {
    @Autowired
    private IAssayHospDictService assayHospDictService;
    // 病患化验类
    @Autowired
    private IPatientAssayClassService assayClassService;
    @Autowired
    private IPatientAssayNewstService patientAssayNewstService;
    @Autowired
    private IReportExceptionService reportExceptionService;

    @RequestMapping("view")
    public ModelAndView view() {
        ModelAndView mav = new ModelAndView("cm/assay/assay_remind_conf");
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
        List<PatientAssayClassPO> list = assayClassService.listByTenantId(tenantId);
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
            // 已经选中已经添加的项目
            dict.setSelectFlag(classCode.contains(dict.getItemCode()));
            // 显示用itemCode,如果是血透唯一标识的item_code，去除其后缀
            if (dict.getItemCode() != null) {
                dict.setItemCodeShow(dict.getItemCode().replace(CommonConstants.DICT_UK_SUFFIX, ""));
            }

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
        List<PatientAssayClassPO> list = assayClassService.listByTenantId(tenantId);
        result.setRs(list);
        return result;
    }

    /**
     * 添加化验病患类
     * 
     * @requestBody代表请求过来的是Json格式
     * @return
     */
    @RequestMapping("save")
    public HttpResult save(@RequestBody List<PatientAssayClass> list) {
        HttpResult result = HttpResult.getSuccessInstance();
        // 添加数据
        assayClassService.saveList(list);

        // 统计报表-异常提醒 (检验结果)
        ReportExConfig config = new ReportExConfig();
        config.setBeforeConfigJson("记录数:" + assayClassService.countByCondition());
        config.setAfterConfigJson("记录数:" + (list == null ? 0 : list.size()));
        config.setCreateTime(new Date());
        config.setCreateUserId(UserUtil.getLoginUserId());
        config.setUpdateTime(new Date());
        config.setUpdateUserId(UserUtil.getLoginUserId());
        config.setStatus(CommonConstants.REPORT_EX_STATUS_INIT);
        config.setExType(CommonConstants.REPORT_EX_ASSAY);
        config.setFkTenantId(UserUtil.getTenantId());
        reportExceptionService.insertReportExConfig(config);

        // 刷新化验提醒项数据
        refreshAssayNewestData();
        return result;
    }

    /**
     * 刷新化验提醒项数据
     * 
     * @Title: refreshAssayNewestData
     *
     */
    private void refreshAssayNewestData() {
        LoginUser user = UserUtil.getLoginUser();
        new Thread(() -> {
            UserUtil.setThreadLoginUser(user);
            patientAssayNewstService.insertAuto(user.getTenantId());
        }).start();
    }
}
