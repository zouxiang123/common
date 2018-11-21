/**   
 * @Title: IAppPatientParamService.java 
 * @Package com.xtt.common.common.service
 * Copyright: Copyright (c) 2015
 * @author: xyw   
 * @date: 2018年11月9日 下午5:23:10 
 *
 */
package com.xtt.common.common.service;

import java.util.List;

import com.xtt.common.dao.model.AppPatientParam;

public interface IAppPatientParamService {
    /**
     * 保存
     * 
     * @Title: save
     * @param record
     *
     */
    void save(AppPatientParam record) throws Exception;

    /**
     * 根据条件查询
     * 
     * @Title: listByCond
     * @param record
     * @return
     *
     */
    List<AppPatientParam> listByCond(AppPatientParam record);
}
