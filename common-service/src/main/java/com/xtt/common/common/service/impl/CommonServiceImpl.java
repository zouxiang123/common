/**   
 * @Title: CommonServiceImpl.java 
 * @Package com.xtt.txgl.common.service.impl
 * Copyright: Copyright (c) 2015
 * @author: Tik   
 * @date: 2015年9月16日 上午11:53:41 
 *
 */
package com.xtt.common.common.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.common.service.ICommonService;
import com.xtt.common.dao.mapper.CountyMapper;
import com.xtt.common.dao.mapper.FeedbackMapper;
import com.xtt.common.dao.mapper.ProvinceMapper;
import com.xtt.common.dao.mapper.SysLogMapper;
import com.xtt.common.dao.mapper.SysTenantMapper;
import com.xtt.common.dao.mapper.SysUserMapper;
import com.xtt.common.dao.model.County;
import com.xtt.common.dao.model.Feedback;
import com.xtt.common.dao.model.Province;
import com.xtt.common.dao.model.SysLog;
import com.xtt.common.dao.po.SysLogPO;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.HttpServletUtil;
import com.xtt.common.util.UserUtil;

/**
 * 
 * @ClassName: CommonServiceImpl
 * @date: 2016年4月29日 下午4:47:37
 * @version: V1.0
 * 
 */
@Service
public class CommonServiceImpl implements ICommonService {

    @Autowired
    SysTenantMapper sysTenantMapper;
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    ProvinceMapper provinceMapper;
    @Autowired
    CountyMapper countyMapper;
    @Autowired
    SysLogMapper sysLogMapper;
    @Autowired
    FeedbackMapper feedbackMapper;

    @Override
    public List<Province> getProvinceList() {
        return provinceMapper.selectProvinceList();
    }

    @Override
    public List<County> getCountyList(Integer provinceId) {
        return countyMapper.selectByProvinceId(provinceId);
    }

    @Override
    public SysLog selectSysLog(SysLogPO record) {
        record.setSysOwner(HttpServletUtil.getSysName());
        List<SysLog> list = sysLogMapper.selectSysLog(record);
        record.setResults(list);
        return record;
    }

    @Override
    public int insertSysLog(String type, String logInfo) {
        logInfo = UserUtil.getLoginUser().getName() + " " + UserUtil.getLoginUser().getPositionShow() + "：" + logInfo;
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
        sysLog.setSysOwner(HttpServletUtil.getSysName());
        DataUtil.setSystemFieldValue(sysLog);
        sysLog.setSysOwner(HttpServletUtil.getSysName());
        return sysLogMapper.insert(sysLog);
    }

    @Override
    public int saveFeedback(Feedback feedback) {
        feedback.setSysOwner(HttpServletUtil.getSysName());
        return feedbackMapper.insert(feedback);
    }

}
