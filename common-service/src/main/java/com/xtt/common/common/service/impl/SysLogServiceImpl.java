/**   
 * @Title: SysLogServiceImpl.java 
 * @Package com.xtt.common.common.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年8月15日 下午5:55:58 
 *
 */
package com.xtt.common.common.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.common.service.ISysLogService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.SysLogMapper;
import com.xtt.common.dao.model.SysLog;
import com.xtt.common.dao.po.SysLogPO;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.io.JsonUtil;
import com.xtt.platform.util.kafka.KafkaExceptionCallback;
import com.xtt.platform.util.kafka.KafkaProducerUtil;
import com.xtt.platform.util.lang.StringUtil;

@Service
public class SysLogServiceImpl implements ISysLogService {
    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public void insert(SysLog record) {
        sysLogMapper.insert(record);
    }

    @Override
    public SysLog selectSysLog(SysLogPO record) {
        List<SysLog> list = sysLogMapper.selectSysLog(record);
        record.setResults(list);
        return record;
    }

    @Override
    public void insertSysLog(String type, String logInfo, String sysOwner) {
        insertSysLog(type, logInfo, sysOwner, true);
    }

    private void insertSysLog(String type, String logInfo, String sysOwner, boolean async) {
        String position = UserUtil.getLoginUser().getPositionShow();
        position = StringUtil.isBlank(position) ? "" : position;
        logInfo = UserUtil.getLoginUser().getName() + " " + position + "：" + logInfo;
        if (StringUtils.isNotEmpty(logInfo) && logInfo.getBytes().length > 1024) {
            logInfo = logInfo.substring(0, 512);
        }
        Date now = new Date();
        SysLog sysLog = new SysLog();
        sysLog.setLogType(type);
        sysLog.setLogInfo(logInfo);
        sysLog.setLogTime(now);
        sysLog.setOperatorId(UserUtil.getLoginUserId());
        sysLog.setFkTenantId(UserUtil.getTenantId());
        sysLog.setSysOwner(sysOwner);
        DataUtil.setSystemFieldValue(sysLog);
        if (StringUtil.isEmpty(sysOwner)) {
            sysLog.setSysOwner(CommonConstants.SYS_HD);
        }

        if (async) {
            KafkaProducerUtil.send(CommonConstants.TOPIC_SYS_LOG, JsonUtil.AllJsonUtil().toJson(sysLog), new KafkaExceptionCallback() {
                @Override
                public void callBack() {
                    sysLogMapper.insert(sysLog);
                }
            });
        } else {
            sysLogMapper.insert(sysLog);
        }
    }

}
