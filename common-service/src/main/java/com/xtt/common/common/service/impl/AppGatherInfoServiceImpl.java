/**   
 * @Title: AppGatherInfoServiceImpl.java 
 * @Package com.xtt.common.common.service.impl
 * Copyright: Copyright (c) 2015
 * @author: xyw   
 * @date: 2018年11月13日 下午3:25:40 
 *
 */
package com.xtt.common.common.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.common.service.IAppGatherInfoService;
import com.xtt.common.dao.mapper.AppGatherInfoMapper;
import com.xtt.common.dao.model.AppGatherInfo;
import com.xtt.platform.util.PrimaryKeyUtil;

@Service
public class AppGatherInfoServiceImpl implements IAppGatherInfoService {
    @Autowired
    private AppGatherInfoMapper appGatherInfoMapper;

    @Override
    public AppGatherInfo getByClassName(AppGatherInfo record) {
        return appGatherInfoMapper.getByClassName(record);
    }

    @Override
    public AppGatherInfo getByClassName(String className) {
        AppGatherInfo record = new AppGatherInfo();
        record.setClassName(className);
        AppGatherInfo appGatherInfo = this.getByClassName(record);
        if (appGatherInfo == null) {
            appGatherInfo = record;
        }
        return appGatherInfo;
    }

    @Override
    public void insertSelective(AppGatherInfo record) {
        appGatherInfoMapper.insertSelective(record);
    }

    @Override
    public void updateByPrimaryKeySelective(AppGatherInfo record) {
        appGatherInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void save(AppGatherInfo record) {
        if (record.getId() == null) {
            record.setId(PrimaryKeyUtil.getPrimaryKey("AppGatherInfo", null));
            this.insertSelective(record);
        } else {
            this.updateByPrimaryKeySelective(record);
        }
    }

    @Override
    public void save(AppGatherInfo record, Date endTime) {
        record.setLastGatherTime(endTime);
        this.save(record);
    }

}
