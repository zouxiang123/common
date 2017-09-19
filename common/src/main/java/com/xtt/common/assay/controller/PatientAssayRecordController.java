package com.xtt.common.assay.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtt.common.assay.service.IPatientAssayRecordBusiService;
import com.xtt.common.assay.vo.DictHospitalLabVO;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.po.PatientAssayRecordBusiPO;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.http.HttpResult;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateFormatUtil;

@Controller
@RequestMapping("/assay/patientAssayRecord/")
public class PatientAssayRecordController {
    @Autowired
    private IPatientAssayRecordBusiService patientAssayRecordBusiService;

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
