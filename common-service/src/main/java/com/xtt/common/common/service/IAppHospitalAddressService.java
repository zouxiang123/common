/**   
 * @Title: IAppHospitalAddressService.java 
 * @Package com.xtt.txgl.api.service
 * Copyright: Copyright (c) 2015
 * @author: xyw   
 * @date: 2018年4月23日 下午3:14:16 
 *
 */
package com.xtt.common.common.service;

import java.util.List;

import com.xtt.common.dao.model.AppHospitalAddress;

public interface IAppHospitalAddressService {
    void deleteByPrimaryKey(Long id);

    void insert(AppHospitalAddress record);

    void insertSelective(AppHospitalAddress record);

    AppHospitalAddress selectByPrimaryKey(Long id);

    void updateByPrimaryKeySelective(AppHospitalAddress record);

    void updateByPrimaryKey(AppHospitalAddress record);

    /**
     * 根据条件查询
     * 
     * @Title: listByCond
     * @param record
     * @return
     *
     */
    List<AppHospitalAddress> listByCond(AppHospitalAddress record);
}
