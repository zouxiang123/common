package com.xtt.common.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.common.service.ISysDbSourceService;
import com.xtt.common.common.service.ISysLogService;
import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.constants.IDownConst;
import com.xtt.common.dao.mapper.SysDbSourceMapper;
import com.xtt.common.dao.model.PatientOrders;
import com.xtt.common.dao.model.SysDbSource;
import com.xtt.common.dao.po.PatientPO;
import com.xtt.common.dao.po.CmQueryPO;
import com.xtt.common.dao.po.SysDbSourcePO;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.http.HttpClientResultUtil;
import com.xtt.platform.util.http.HttpClientUtil;
import com.xtt.platform.util.io.JsonUtil;
import com.xtt.platform.util.lang.StringUtil;

@Service
public class SysDbSourceServiceImpl implements ISysDbSourceService {

    @Autowired
    private SysDbSourceMapper sysDbSourceMapper;

    @Autowired
    ISysLogService sysLogService;

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
    public String sendOrdersStatus(List<PatientOrders> list, String sysOwner) {
        sysLogService.insertSysLog(IDownConst.SEND_ORDER_STATUS, "SysDbSourceServiceImpl sendOrdersStatus Begin===>", sysOwner);
        String retMsg = "";
        if (list != null && list.size() > 0) {
            String orders = new JsonUtil(null).toJson(list);
            sysLogService.insertSysLog(IDownConst.SEND_ORDER_STATUS,
                            "SysDbSourceServiceImpl sendOrdersStatus req size:===>" + list.size() + ", req json:" + orders, sysOwner);
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
                sysLogService.insertSysLog(IDownConst.SEND_ORDER_STATUS, "SysDbSourceServiceImpl sendOrdersStatus retMsg:" + retMsg, sysOwner);
                if (!"1".equals(retMsg)) {
                    retMsg = "0";
                }
            } catch (Exception e) {
                sysLogService.insertSysLog(IDownConst.SEND_ORDER_STATUS, "SysDbSourceServiceImpl sendOrdersStatus Exception Msg:" + e.getMessage(),
                                sysOwner);
            }
        }
        sysLogService.insertSysLog(IDownConst.SEND_ORDER_STATUS, "SysDbSourceServiceImpl sendOrdersStatus End===>", sysOwner);
        return retMsg;
    }

    /* 
     * 传入4个参数(fkPatientId="",tenantId="",startDate='',endDate='')
     */
    @Override
    public String sendQueryOrderInfo(CmQueryPO query) {
        sysLogService.insertSysLog(IDownConst.DOWN_TYPE_ORDER, "SysDbSourceServiceImpl sendQueryOrderInfo Begin===>", query.getSysOwner());
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

            sysLogService.insertSysLog(IDownConst.DOWN_TYPE_ORDER, "SysDbSourceServiceImpl sendQueryOrderInfo retMsg:" + retMsg, query.getSysOwner());
        } catch (Exception e) {
            sysLogService.insertSysLog(IDownConst.SEND_ORDER_STATUS, "SysDbSourceServiceImpl sendQueryOrderInfo Exception Msg:" + e.getMessage(),
                            query.getSysOwner());
        }
        sysLogService.insertSysLog(IDownConst.DOWN_TYPE_ORDER, "SysDbSourceServiceImpl sendQueryOrderInfo End===>", query.getSysOwner());
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
    public String downDB(CmQueryPO db) {
        sysLogService.insertSysLog(IDownConst.DOWN_INPUT, "xtt SysDbSourceServiceImpl downDB Begin===>", db.getSysOwner());

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
        Long fkPatientId = db.getFkPatientId();

        // 租户ID
        Integer fkTenantId = UserUtil.getTenantId();
        if (fkTenantId != null) {
            String fkTenantIdStr = String.valueOf(fkTenantId);
            qmap.put("fkTenantId", fkTenantIdStr);
        }

        // 血透病患ID
        if (fkPatientId != null) {
            String fkPatientIdStr = String.valueOf(fkPatientId);
            qmap.put("fkPatientId", fkPatientIdStr);
        }

        // 开始时间
        qmap.put("startDate", startDate);
        // 结束时间
        qmap.put("endDate", endDate);

        String reqMsg = "cardNo:" + cardNo + ", downType:" + downType + ", startDate:" + startDate + ", endDate:" + endDate;
        sysLogService.insertSysLog(IDownConst.DOWN_INPUT, "xtt SysDbSourceServiceImpl downDB req Pram:" + reqMsg, db.getSysOwner());

        // 访问的目标地址
        String url = DictUtil.getItemName(CmDictConsts.URL, CmDictConsts.DOWN_DB_WS_URL_ALL);
        HttpClientResultUtil httpClientResultUtil = HttpClientUtil.post(url, qmap);
        String json = httpClientResultUtil.getContext();

        sysLogService.insertSysLog(IDownConst.DOWN_INPUT, "xtt SysDbSourceServiceImpl downDB retMsg:" + json, db.getSysOwner());
        sysLogService.insertSysLog(IDownConst.DOWN_INPUT, "xtt SysDbSourceServiceImpl downDB End===>", db.getSysOwner());
        return json;
    }

    /* 
     *获取病患基本信息
     */
    @Override
    public PatientPO patientDB(CmQueryPO query) throws Exception {
        sysLogService.insertSysLog(IDownConst.DOWN_TYPE_PT, "xtt SysDbSourceServiceImpl patientDB Begin===>", query.getSysOwner());
        // 租户ID
        Integer fkTenantId = UserUtil.getTenantId();
        String url = DictUtil.getItemName(CmDictConsts.URL, CmDictConsts.DOWN_DB_WS_URL_PT);
        String json = "";
        String cardNo = query.getCardNo(); // 卡号（住院是门诊号，住院号）
        // 患者类型
        String cardType = "";
        Long fkPatientId = query.getFkPatientId();// 血透病患系统ID
        Map<String, String> qmap = new HashMap<String, String>();
        qmap.put("cardNo", cardNo);
        if (fkPatientId != null) {
            qmap.put("fkPatientId", String.valueOf(fkPatientId));
        }
        if (StringUtil.isNotEmpty(query.getCardType())) {
            cardType = query.getCardType();
            qmap.put("cardType", cardType);
        }
        qmap.put("fkTenantId", String.valueOf(fkTenantId));
        HttpClientResultUtil httpClientResultUtil = HttpClientUtil.post(url, qmap);
        json = httpClientResultUtil.getContext();
        sysLogService.insertSysLog(IDownConst.DOWN_TYPE_PT, "xtt SysDbSourceServiceImpl patientDB json:" + json, query.getSysOwner());

        PatientPO patient = JsonUtil.AllJsonUtil().fromJson(json, PatientPO.class);
        String msg = "输入参数：" + cardNo + ",返回病患：";
        if (patient != null) {
            patient.setIdType("1");
            msg += patient.getName();
        }
        sysLogService.insertSysLog(IDownConst.DOWN_TYPE_PT, "xtt SysDbSourceServiceImpl patientDB End===>" + msg, query.getSysOwner());
        return patient;
    }

}
