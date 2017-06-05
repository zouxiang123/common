/**   
 * @Title: CommonServiceImpl.java 
 * @Package com.xtt.common.common.service.impl
 * Copyright: Copyright (c) 2015
 * @author: Tik   
 * @date: 2015年9月16日 上午11:53:41 
 *
 */
package com.xtt.common.common.service.impl;

import java.util.List;

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
    public int saveFeedback(Feedback feedback) {
        return feedbackMapper.insert(feedback);
    }

}
