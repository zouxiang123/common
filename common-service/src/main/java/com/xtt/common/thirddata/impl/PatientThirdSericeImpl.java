/**   
 * @Title: PatientThirdSericeImpl.java 
 * @Package com.xtt.txgl.third.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年1月12日 下午4:30:03 
 *
 */
package com.xtt.common.thirddata.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.common.service.ISysDbSourceService;
import com.xtt.common.constants.CmSysParamConsts;
import com.xtt.common.dao.po.QueryPO;
import com.xtt.common.thirddata.IPatientThirdSerice;
import com.xtt.common.util.SysParamUtil;

@Service
public class PatientThirdSericeImpl implements IPatientThirdSerice {
    protected Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private ISysDbSourceService sysDbSourceService;

    @Override
    public void callInterfacePro(Long patientId, String downType, String sysOwner) {
        String isOpen = SysParamUtil.getValueByName(CmSysParamConsts.IS_OPEN_PT_TYPE);
        /** 是否调用第三方平台的接口，获取患者类型的开关（1=开启，0=未开启） */
        if (null == patientId || !CmSysParamConsts.IS_OPEN_PT_TYPE_OPEN.equals(isOpen)) {
            return;
        }
        QueryPO query = new QueryPO();
        query.setFkPatientId(patientId);
        query.setDownType(downType);
        query.setSysOwner(sysOwner);
        LOGGER.info("PatientThirdSericeImpl.updatePatientType(patientId:{}) start ", patientId);
        long start = System.currentTimeMillis();
        String ptType = sysDbSourceService.downDB(query);
        LOGGER.info("PatientThirdSericeImpl.updatePatientType(patientId:{}) return ptType{} end .cost {}", patientId, ptType,
                        System.currentTimeMillis() - start);
        if (StringUtils.isBlank(ptType)) {
            LOGGER.info("PatientThirdSericeImpl.updatePatientType(patientId:{}): return empty", patientId);
            return;
        }
    }

}
