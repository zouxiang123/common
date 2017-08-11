package com.xtt.common.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.common.service.ICommonService;
import com.xtt.common.common.service.ISysDbSourceService;
import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.constants.IDownConst;
import com.xtt.common.dao.mapper.SysDbSourceMapper;
import com.xtt.common.dao.model.PatientOrders;
import com.xtt.common.dao.model.SysDbSource;
import com.xtt.common.dao.po.PatientPO;
import com.xtt.common.dao.po.QueryPO;
import com.xtt.common.dao.po.SysDbSourcePO;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.HttpServletUtil;
import com.xtt.platform.util.http.HttpClientResultUtil;
import com.xtt.platform.util.http.HttpClientUtil;
import com.xtt.platform.util.io.JsonUtil;

@Service
public class SysDbSourceServiceImpl implements ISysDbSourceService {

    @Autowired
    private SysDbSourceMapper sysDbSourceMapper;

    @Autowired
    ICommonService commonService;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return sysDbSourceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysDbSource record) {
        return sysDbSourceMapper.insert(record);
    }

    @Override
    public int insertSelective(SysDbSource record) {
        return sysDbSourceMapper.insertSelective(record);
    }

    @Override
    public SysDbSource selectByPrimaryKey(Long id) {
        return sysDbSourceMapper.selectByPrimaryKey(id);
    }

    @Override
    public SysDbSource selectByDbKey(String parm) {
        return sysDbSourceMapper.selectByDbKey(parm);
    }

    @Override
    public int updateByPrimaryKeySelective(SysDbSource record) {
        return sysDbSourceMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysDbSource record) {
        return sysDbSourceMapper.updateByPrimaryKey(record);
    }

    @Override
    public SysDbSourcePO selectByDbKeyPO(String key) {
        return sysDbSourceMapper.selectByDbKeyPO(key);
    }

    @Override
    public List<SysDbSourcePO> selectByDbKeyPPO(String key) {
        return sysDbSourceMapper.selectByDbKeyPPO(key);
    }

    @Override
    public List<SysDbSourcePO> selectBySysDbSourcePO(SysDbSourcePO po) {
        return sysDbSourceMapper.selectBySysDbSourcePO(po);
    }

    /* 
     * 传入一个医嘱实体对象
     */
    @Override
    public String sendOrdersStatus(List<PatientOrders> list) {
        commonService.insertSysLog(IDownConst.SEND_ORDER_STATUS, "SysDbSourceServiceImpl sendOrdersStatus Begin===>");
        String retMsg = "";
        if (list != null && list.size() > 0) {
            String orders = new JsonUtil(null).toJson(list);
            commonService.insertSysLog(IDownConst.SEND_ORDER_STATUS,
                            "SysDbSourceServiceImpl sendOrdersStatus req size:===>" + list.size() + ", req json:" + orders);
            try {
                Map<String, String> qmap = new HashMap<String, String>();
                // 卡号，住院号，门诊号，血透号
                qmap.put("orders", orders);
                // 11=病患 12=检验 13=影像 14=医嘱 ，必须
                qmap.put("downType", IDownConst.SEND_ORDER_STATUS);
                // 租户（判断调用哪家医院的服务，必须）
                PatientOrders patientOrders = list.get(0);
                if (patientOrders != null) {
                    Integer fkTenantId = patientOrders.getFkTenantId();
                    if (fkTenantId != null) {
                        String fkTenantIdStr = String.valueOf(fkTenantId);
                        qmap.put("fkTenantId", fkTenantIdStr);
                    }
                }
                // 访问的目标地址
                String url = DictUtil.getItemName(CmDictConsts.URL, CmDictConsts.DOWN_DB_WS_URL_ALL);
                HttpClientResultUtil httpClientResultUtil = HttpClientUtil.post(url, qmap);
                retMsg = httpClientResultUtil.getContext();
                commonService.insertSysLog(IDownConst.SEND_ORDER_STATUS, "SysDbSourceServiceImpl sendOrdersStatus retMsg:" + retMsg);
            } catch (Exception e) {
                commonService.insertSysLog(IDownConst.SEND_ORDER_STATUS, "SysDbSourceServiceImpl sendOrdersStatus Exception Msg:" + e.getMessage());
            }
        }
        commonService.insertSysLog(IDownConst.SEND_ORDER_STATUS, "SysDbSourceServiceImpl sendOrdersStatus End===>");
        return retMsg;
    }

    /* 
     * 传入4个参数(fkPatientId="",tenantId="",startDate='',endDate='')
     */
    @Override
    public String sendQueryOrderInfo(QueryPO query) {
        commonService.insertSysLog(IDownConst.DOWN_TYPE_ORDER, "SysDbSourceServiceImpl sendQueryOrderInfo Begin===>");
        Map<String, String> qmap = new HashMap<String, String>();
        String retMsg = "";
        try {
            // 卡号，住院号，门诊号，血透号
            Long fkPatientId = query.getFkPatientId();
            if (fkPatientId != null) {
                String fkPatientIdStr = String.valueOf(fkPatientId);
                qmap.put("fkPatientId", fkPatientIdStr);
            }
            qmap.put("ptId", query.getPtId());
            // 11=病患 12=检验13=影像 14=医嘱 ，必须
            qmap.put("downType", IDownConst.DOWN_TYPE_ORDER);
            // 租户（判断调用哪家医院的服务，必须）
            Integer fkTenantId = query.getFkTenantId();
            if (fkTenantId != null) {
                String fkTenantIdStr = String.valueOf(fkTenantId);
                qmap.put("fkTenantId", fkTenantIdStr);
            }
            // 开始时间(2016-01-01)
            qmap.put("startDate", query.getStartDate());
            // 结束时间(2016-01-02)
            qmap.put("endDate", query.getEndDate());

            // 访问的目标地址
            String url = DictUtil.getItemName(CmDictConsts.URL, CmDictConsts.DOWN_DB_WS_URL_ALL);
            HttpClientResultUtil httpClientResultUtil = HttpClientUtil.post(url, qmap);
            retMsg = httpClientResultUtil.getContext();

            commonService.insertSysLog(IDownConst.DOWN_TYPE_ORDER, "SysDbSourceServiceImpl sendQueryOrderInfo retMsg:" + retMsg);
        } catch (Exception e) {
            commonService.insertSysLog(IDownConst.SEND_ORDER_STATUS, "SysDbSourceServiceImpl sendQueryOrderInfo Exception Msg:" + e.getMessage());
        }
        commonService.insertSysLog(IDownConst.DOWN_TYPE_ORDER, "SysDbSourceServiceImpl sendQueryOrderInfo End===>");
        return retMsg;
    }

    /**
     * 下载服务
     * 
     * @Title: downDB
     * @param db
     * @return
     * 
     */
    @Override
    public String downDB(QueryPO db) {
        commonService.insertSysLog(IDownConst.DOWN_INPUT, "xtt SysDbSourceServiceImpl downDB Begin===>");

        Map<String, String> qmap = new HashMap<String, String>();
        String cardNo = db.getCardNo();
        String downType = db.getDownType();
        String startDate = db.getStartDate();
        String endDate = db.getEndDate();

        // 卡号，住院号，门诊号，血透号
        qmap.put("cardNo", cardNo);
        // 1=病患 2=检验 3=影像 4=医嘱 ，必须
        qmap.put("downType", downType);
        // 租户（判断调用哪家医院的服务，必须）
        Integer fkTenantId = db.getFkTenantId();
        if (fkTenantId != null) {
            String fkTenantIdStr = String.valueOf(fkTenantId);
            qmap.put("fkTenantId", fkTenantIdStr);
        }
        // 开始时间
        qmap.put("startDate", startDate);
        // 结束时间
        qmap.put("endDate", endDate);

        String reqMsg = "cardNo:" + cardNo + ", downType" + downType + ", startDate:" + startDate + ", endDate:" + endDate;
        commonService.insertSysLog(IDownConst.DOWN_INPUT, "xtt SysDbSourceServiceImpl downDB req Pram:" + reqMsg);

        // 访问的目标地址
        String url = DictUtil.getItemName(CmDictConsts.URL, CmDictConsts.DOWN_DB_WS_URL_ALL);
        HttpClientResultUtil httpClientResultUtil = HttpClientUtil.post(url, qmap);
        String json = httpClientResultUtil.getContext();

        commonService.insertSysLog(IDownConst.DOWN_INPUT, "xtt SysDbSourceServiceImpl downDB retMsg:" + json);
        commonService.insertSysLog(IDownConst.DOWN_INPUT, "xtt SysDbSourceServiceImpl downDB End===>");
        return json;
    }

    /* 
     *获取病患基本信息
     */
    @Override
    public PatientPO patientDB(QueryPO query) throws Exception {
        commonService.insertSysLog(IDownConst.DOWN_TYPE_PT, "xtt SysDbSourceServiceImpl patientDB Begin===>");
        String tenantId = HttpServletUtil.getCookieValueByName("tenantId");// 租户ID
        String url = DictUtil.getItemName(CmDictConsts.URL, CmDictConsts.DOWN_DB_WS_URL_PT);
        String json = "";
        String cardNo = query.getCardNo(); // 卡号（住院是门诊号，住院号）
        Long fkPatientId = query.getFkPatientId();// 血透病患系统ID
        Map<String, String> qmap = new HashMap<String, String>();
        qmap.put("cardNo", cardNo);
        if (fkPatientId != null) {
            qmap.put("fkPatientId", String.valueOf(fkPatientId));
        }
        qmap.put("fkTenantId", tenantId);
        HttpClientResultUtil httpClientResultUtil = HttpClientUtil.post(url, qmap);
        json = httpClientResultUtil.getContext();
        commonService.insertSysLog(IDownConst.DOWN_TYPE_PT, "xtt SysDbSourceServiceImpl patientDB json:" + json);

        PatientPO patient = JsonUtil.AllJsonUtil().fromJson(json, PatientPO.class);
        String msg = "输入参数：" + cardNo + ",返回病患：";
        if (patient != null) {
            patient.setIdType("1");
            msg += patient.getName();
        }
        commonService.insertSysLog(IDownConst.DOWN_TYPE_PT, "xtt SysDbSourceServiceImpl patientDB End===>" + msg);
        return patient;
    }

}
