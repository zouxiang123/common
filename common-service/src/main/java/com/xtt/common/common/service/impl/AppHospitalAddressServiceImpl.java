/**   
 * @Title: AppHospitalAddressServiceImpl.java 
 * @Package com.xtt.txgl.api.service.impl
 * Copyright: Copyright (c) 2015
 * @author: xyw   
 * @date: 2018年4月23日 下午3:15:48 
 *
 */
package com.xtt.common.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.common.service.IAppHospitalAddressService;
import com.xtt.common.dao.mapper.AppHospitalAddressMapper;
import com.xtt.common.dao.model.AppHospitalAddress;
import com.xtt.common.util.DataUtil;

@Service
public class AppHospitalAddressServiceImpl implements IAppHospitalAddressService {
    @Autowired
    private AppHospitalAddressMapper appHospitalAddressMapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        appHospitalAddressMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(AppHospitalAddress record) {
        DataUtil.setAllSystemFieldValue(record);
        appHospitalAddressMapper.insert(record);
    }

    @Override
    public void insertSelective(AppHospitalAddress record) {
        DataUtil.setAllSystemFieldValue(record);
        appHospitalAddressMapper.insertSelective(record);
    }

    @Override
    public AppHospitalAddress selectByPrimaryKey(Long id) {
        return appHospitalAddressMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateByPrimaryKeySelective(AppHospitalAddress record) {
        DataUtil.setUpdateSystemFieldValue(record);
        appHospitalAddressMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void updateByPrimaryKey(AppHospitalAddress record) {
        DataUtil.setUpdateSystemFieldValue(record);
        appHospitalAddressMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<AppHospitalAddress> listByCond(AppHospitalAddress record) {
        return appHospitalAddressMapper.listByCond(record);
    }

}
