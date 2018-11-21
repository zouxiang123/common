/**   
 * @Title: AppDictServiceImpl.java 
 * @Package com.xtt.common.common.service.impl
 * Copyright: Copyright (c) 2015
 * @author: xyw   
 * @date: 2018年11月8日 下午1:35:25 
 *
 */
package com.xtt.common.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.common.service.IAppDictService;
import com.xtt.common.dao.mapper.AppDictMapper;
import com.xtt.common.dao.po.AppDictPO;

@Service
public class AppDictServiceImpl implements IAppDictService {
    @Autowired
    private AppDictMapper appDictMapper;

    @Override
    public List<AppDictPO> listByCond(AppDictPO record) {
        return appDictMapper.listByCond(record);
    }

    @Override
    public AppDictPO getByUniqueCondition(String pItemCode, String itemCode) {
        return appDictMapper.getByUniqueCondition(pItemCode, itemCode);
    }

}
