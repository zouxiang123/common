/**   
 * @Title: IAppGatherInfoService.java 
 * @Package com.xtt.common.common.service
 * Copyright: Copyright (c) 2015
 * @author: xyw   
 * @date: 2018年11月13日 下午3:22:46 
 *
 */
package com.xtt.common.common.service;

import java.util.Date;

import com.xtt.common.dao.model.AppGatherInfo;

public interface IAppGatherInfoService {
    void insertSelective(AppGatherInfo record);

    void updateByPrimaryKeySelective(AppGatherInfo record);

    void save(AppGatherInfo record);

    /**
     * 保存
     * 
     * @Title: save
     * @param record
     * @param endTime
     *
     */
    void save(AppGatherInfo record, Date endTime);

    /**
     * 根据className获取数据
     * 
     * @Title: getByClassName
     * @param record
     * @return
     *
     */
    AppGatherInfo getByClassName(AppGatherInfo record);

    /**
     * 根据className获取数据
     * 
     * @Title: getByClassName
     * @param className
     * @return
     *
     */
    AppGatherInfo getByClassName(String className);
}
