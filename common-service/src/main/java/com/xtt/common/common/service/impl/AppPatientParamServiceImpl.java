/**   
 * @Title: AppPatientParamServiceImpl.java 
 * @Package com.xtt.common.common.service.impl
 * Copyright: Copyright (c) 2015
 * @author: xyw   
 * @date: 2018年11月9日 下午5:28:52 
 *
 */
package com.xtt.common.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.cache.AppPatientParamCache;
import com.xtt.common.common.service.IAppPatientParamService;
import com.xtt.common.dao.mapper.AppPatientParamMapper;
import com.xtt.common.dao.model.AppPatientParam;
import com.xtt.common.dto.AppPatientParamDto;
import com.xtt.common.util.DataUtil;

@Service
public class AppPatientParamServiceImpl implements IAppPatientParamService {
    @Autowired
    private AppPatientParamMapper appPatientParamMapper;

    @Override
    public void save(AppPatientParam record) throws Exception {
        // 删除
        appPatientParamMapper.deleteByUniquekey(record.getFkUserId(), record.getItemCode());
        // 新增
        DataUtil.setAllSystemFieldValue(record);
        appPatientParamMapper.insertSelective(record);
        // 刷新缓存
        List<AppPatientParamDto> list = AppPatientParamCache.listByFkUserId(record.getFkUserId());
        boolean flag = false;// 原来是否存在
        if (list != null && list.size() > 0) {
            AppPatientParamCache.clear(record.getFkUserId());
            for (AppPatientParamDto entity : list) {
                if (entity.getFkUserId().longValue() == record.getFkUserId().longValue() && entity.getItemCode().equals(record.getItemCode())) {
                    BeanUtils.copyProperties(entity, record);
                    flag = true;
                }
            }
        }
        if (!flag) {
            AppPatientParamDto entity = new AppPatientParamDto();
            BeanUtils.copyProperties(entity, record);
            if (list == null) {
                list = new ArrayList<AppPatientParamDto>(1);
            }
            list.add(entity);
        }
        AppPatientParamCache.cacheALL(list);
    }

    @Override
    public List<AppPatientParam> listByCond(AppPatientParam record) {
        return appPatientParamMapper.listByCond(record);
    }

}
