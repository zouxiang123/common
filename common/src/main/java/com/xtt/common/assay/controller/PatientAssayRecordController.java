package com.xtt.common.assay.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.assay.service.IPatientAssayRecordBusiService;
import com.xtt.common.assay.service.IPatientAssayResultService;
import com.xtt.common.assay.vo.DictHospitalLabVO;
import com.xtt.common.constants.CmSysParamConsts;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.po.PatientAssayRecordBusiPO;
import com.xtt.common.dao.po.PatientAssayResultPO;
import com.xtt.common.patient.service.IPatientService;
import com.xtt.common.util.BusinessReportUtil;
import com.xtt.common.util.SysParamUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.http.HttpResult;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateFormatUtil;

@Controller
@RequestMapping("/assay/patientAssayRecord/")
public class PatientAssayRecordController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientAssayRecordController.class);

    @Autowired
    private IPatientAssayRecordBusiService patientAssayRecordBusiService;
    @Autowired
    private IPatientService patientService;
    @Autowired
    private IPatientAssayResultService patientAssayResultService;

    @RequestMapping("record")
    public ModelAndView record(@RequestParam(value = "patientId") Long patientId) {
        ModelAndView model = new ModelAndView("assay/patient_assay_record");

        // 是否显示传染病标志
        PatientAssayRecordBusiPO record = new PatientAssayRecordBusiPO();
        record.setFkPatientId(patientId);
        model.addObject("tenantId", UserUtil.getTenantId());
        model.addObject("assayResult", patientAssayResultService.getByPatientId(patientId));
        model.addObject("patientId", patientId);
        model.addObject("patient", patientService.selectById(patientId));
        // 报告日期，检查日期 开关配置
        model.addObject("labTimeFlag", SysParamUtil.getValueByName(CmSysParamConsts.PATIENT_ASSAY_LOAD_CONDITION_TIME_SWITCH));
        model.addObject("roleType", UserUtil.getRoleType());

        return model;
    }

    /**
     * 所获不同的检查时间
     * 
     * @Title: getAssayDateRecord
     * @param record
     * @return
     *
     */
    @RequestMapping("getAssayDateRecord")
    @ResponseBody
    public Map<String, Object> getAssayDateRecord(PatientAssayRecordBusiPO record) {
        long start = System.currentTimeMillis();
        if (record.getPageNo() == 0) {
            record.setPageNo(1);
            record.setPageSize(20);
        }
        record.setIspaging(true);// 使用数据库分页
        // 查询检验透析前后统计
        Map<String, Object> map = new HashMap<String, Object>();
        List<PatientAssayRecordBusiPO> items = patientAssayRecordBusiService.listCategory(record);
        List<PatientAssayRecordBusiPO> records = new ArrayList<PatientAssayRecordBusiPO>();
        // List<PatientAssayRecordBusiPO> categoryList = new ArrayList<PatientAssayRecordBusiPO>();
        // 类别切换时默认显示最新一次检查的数据
        if (items != null && !items.isEmpty() && record.getPageNo() == 1) {
            PatientAssayRecordBusiPO temp = items.get(0);
            PatientAssayRecordBusiPO query = new PatientAssayRecordBusiPO();
            query.setReqId(temp.getReqId());
            query.setFkPatientId(temp.getFkPatientId());
            query.setReportTime(temp.getReportTime());
            records = patientAssayRecordBusiService.listByCondition(query);
        }
        map.put("records", records);
        map.put("items", items);

        map.put("record", record);
        // 报告日期，检查日期 开关配置
        String labTimeFlag = SysParamUtil.getValueByName(CmSysParamConsts.PATIENT_ASSAY_LOAD_CONDITION_TIME_SWITCH);
        map.put("labTimeFlag", labTimeFlag);
        LOGGER.info("get assay date record total cost {} ms", System.currentTimeMillis() - start);
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    /**
     * 所获不同的检查时间
     * 
     * @Title: getAssayDateRecord
     * @param record
     * @return
     *
     */
    @RequestMapping("getAssayRecord")
    @ResponseBody
    public Map<String, Object> getAssayRecord(PatientAssayRecordBusiPO record, Boolean needCategory) {
        long start = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("records", patientAssayRecordBusiService.listByCondition(record));
        LOGGER.info("get assay record total cost {} ms", System.currentTimeMillis() - start);
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    /**
     * 获取某项化验单报表数据
     * 
     * @Title: getAssayReport
     * @return
     * @throws UnsupportedEncodingException
     *
     */
    @RequestMapping("getAssayReport")
    @ResponseBody
    public List<Map<String, Object>> getAssayReport(String startDateStr, String endDateStr, Long patientId, String itemCode)
                    throws UnsupportedEncodingException {
        return patientAssayRecordBusiService.listReportData(patientId, BusinessReportUtil.getStartOrEndDate(startDateStr, true),
                        BusinessReportUtil.getStartOrEndDate(endDateStr, false), itemCode);
    }

    /**
     * 保存化验项汇总数据
     * 
     * @Title: saveAssayResult
     * @param record
     * @return
     *
     */
    @RequestMapping("saveAssayResult")
    @ResponseBody
    public Map<String, Object> saveAssayResult(PatientAssayResultPO record) {
        Map<String, Object> map = new HashMap<String, Object>();
        patientAssayResultService.saveAssayResult(record);
        map.put("id", record.getId());
        map.put("status", CommonConstants.SUCCESS);
        return map;
    }

    /**
     * 添加化验项
     * 
     * @param dHL
     * @return
     */
    @RequestMapping("insertPatientAssay")
    @ResponseBody
    public Map<String, Object> insertPatientAssay(DictHospitalLabVO dHL) {
        Map<String, Object> map = new HashMap<String, Object>();
        patientAssayRecordBusiService.insertPatientAssay(dHL.getdHL());
        map.put("status", CommonConstants.SUCCESS);
        return map;
    }

    /**
     * 删除手动录入的化验项
     * 
     * @param groupId
     * @return
     */
    @RequestMapping("deleteById")
    @ResponseBody
    public Map<String, Object> deleteByGroupId(Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        patientAssayRecordBusiService.removeById(id);
        map.put("status", CommonConstants.SUCCESS);
        return map;
    }

    /**
     * 查询所有化验单
     * 
     * @param par
     * @return
     */
    @RequestMapping("selectByReqId")
    @ResponseBody
    public Map<String, Object> selectByReqId(PatientAssayRecordBusiPO par) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<PatientAssayRecordBusiPO> patientAssayList = patientAssayRecordBusiService.listByReqId(par);
        map.put("patientAssayList", patientAssayList);
        map.put("status", CommonConstants.SUCCESS);
        return map;
    }

    /**
     * 更新化验项
     * 
     * @param dHL
     * @return
     */
    @RequestMapping("updatePatientAssay")
    @ResponseBody
    public Map<String, Object> updatePatientAssay(DictHospitalLabVO dHL) {
        Map<String, Object> map = new HashMap<String, Object>();
        patientAssayRecordBusiService.updatePatientAssay(dHL.getdHL());
        map.put("status", CommonConstants.SUCCESS);
        return map;
    }

    /**
     * 自动插入数据
     * 
     * @Title: insertAuto
     * @param dateStr
     * @param tenantId
     * @param patientId
     * @param isDelete
     * @return
     *
     */
    @RequestMapping("insertAuto")
    @ResponseBody
    public HttpResult insertAuto(String dateStr, Integer tenantId, Long patientId, Boolean isDelete) {
        if (StringUtil.isBlank(dateStr) || tenantId == null) {
            return HttpResult.getWarningInstance();
        }
        UserUtil.setThreadTenant(tenantId);
        HttpResult result = HttpResult.getSuccessInstance();
        isDelete = isDelete == null ? false : isDelete;
        result.setRs(patientAssayRecordBusiService.selectInsertFromSource(DateFormatUtil.getStartTime(dateStr), DateFormatUtil.getEndTime(dateStr),
                        null, patientId, isDelete, tenantId));
        return result;
    }

    /**
     * 手动维护透前透后标识
     * 
     * @Title: updateDiaAbFlag
     * @param assayRecord
     * @return
     *
     */
    @RequestMapping("updateHandDiaAbFlag")
    @ResponseBody
    public Map<String, Object> updateHandDiaAbFlag(PatientAssayRecordBusiPO assayRecord) {
        Map<String, Object> map = new HashMap<>();
        patientAssayRecordBusiService.updateHandDiaAbFlag(assayRecord);
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

}
