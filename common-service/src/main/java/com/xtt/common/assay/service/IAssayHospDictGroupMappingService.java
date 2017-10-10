/**   
 * @Title: IAssayHospDictGroupMappingService.java 
 * @Package com.xtt.common.assay.service
 * Copyright: Copyright (c) 2015
 * @author: ljz   
 * @date: 2017年8月21日 上午11:49:00 
 *
 */
package com.xtt.common.assay.service;

import java.util.List;

import com.xtt.common.dao.model.AssayHospDictGroupMapping;

public interface IAssayHospDictGroupMappingService {

    /**
     * 插入数据
     * 
     * @Title: insert
     * @param record
     * @return
     *
     */
    int insert(AssayHospDictGroupMapping record);

    /**
     * 批量插入
     * 
     * @Title: insertList
     * @param list
     *
     */
    void insertList(List<AssayHospDictGroupMapping> list);

    /**
     * 查询是否重复
     * 
     * @Title: getCountByGroupId
     * @param fkGroupId
     * @param fkItemCode
     * @return
     *
     */
    int getCountByGroupId(String fkGroupId, String fkItemCode);

    /**
     * 根据租户删除数据
     * 
     * @Title: deleteByTenant
     * @param tenantId
     *
     */
    void deleteByTenant(Integer tenantId);

    /**
     * 根据groupId itemCode 删除对应关系
     * 
     * @Title: deleteByGroupIdAndItemCode
     * @param groupId
     * @param itemCode
     * @param fkTenantId
     *
     */
    void deleteByGroupIdAndItemCode(String groupId, String itemCode, Integer fkTenantId);
}
