/**   
 * @Title: IAssayHospDictGroupService.java 
 * @Package com.xtt.common.assay.service
 * Copyright: Copyright (c) 2015
 * @author: Administrator   
 * @date: 2017年8月21日 上午11:47:32 
 *
 */
package com.xtt.common.assay.service;

import java.util.List;

import com.xtt.common.dao.model.AssayHospDictGroup;

public interface IAssayHospDictGroupService {

    /**
     * 插入数据
     * 
     * @Title: insert
     * @param record
     * @return
     *
     */
    int insert(AssayHospDictGroup record);

    /**
     * 批量插入
     * 
     * @Title: insertList
     * @param list
     *
     */
    void insertList(List<AssayHospDictGroup> list);

    /**
     * 根据groupId查询是否重复
     * 
     * @Title: getCountByGroupId
     * @param GroupId
     * @return
     *
     */
    int getCountByGroupId(String groupId);

    /**
     * 根据租户删除数据
     * 
     * @Title: deleteByTenant
     * @param tenantId
     *
     */
    void deleteByTenant(Integer tenantId);

}
