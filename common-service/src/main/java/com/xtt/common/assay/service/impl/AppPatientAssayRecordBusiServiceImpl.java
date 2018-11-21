/**   
 * @Title: AppPatientAssayRecordBusiServiceImpl.java 
 * @Package com.xtt.common.assay.service.impl
 * Copyright: Copyright (c) 2015
 * @author: xyw   
 * @date: 2018年11月14日 下午5:39:44 
 *
 */
package com.xtt.common.assay.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.service.IAppPatientAssayRecordBusiService;
import com.xtt.common.dao.mapper.AppPatientAssayRecordBusiMapper;
import com.xtt.common.dao.model.AppPatientAssayRecordBusi;
import com.xtt.common.dao.po.AppPatientAssayRecordBusiPO;
import com.xtt.platform.util.time.DateFormatUtil;

@Service
public class AppPatientAssayRecordBusiServiceImpl implements IAppPatientAssayRecordBusiService {
    @Autowired
    private AppPatientAssayRecordBusiMapper appPatientAssayRecordBusiMapper;

    @Override
    public List<Map<String, Object>> listApiAssayList(Long patientId, Date startDate, Date endDate) {
        return appPatientAssayRecordBusiMapper.listApiAssayList(patientId, startDate, endDate);
    }

    @Override
    public List<AppPatientAssayRecordBusi> listApiAssayItems(Long patientId, Date assayDate) {
        return appPatientAssayRecordBusiMapper.listApiAssayItems(patientId, assayDate);
    }

    @Override
    public List<AppPatientAssayRecordBusiPO> listByCondition(AppPatientAssayRecordBusiPO record) {
        List<AppPatientAssayRecordBusiPO> list = appPatientAssayRecordBusiMapper.listByCondition(record);
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(item -> {
                item.setAssayDateStr(DateFormatUtil.convertDateToStr(item.getAssayDate()));
            });
        }
        return list;
    }
}
