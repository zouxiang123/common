/**   
 * @Title: ISyncGroupService.java 
 * @Package com.xtt.common.conf.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年6月22日 上午11:14:41 
 *
 */
package com.xtt.common.conf.service;

import java.util.List;
import java.util.Map;

import com.xtt.common.dao.po.SyncGroupPO;

public interface ISyncGroupService {
    /**
     * 根据集团id查询显示用同步组数据
     * 
     * @Title: listByGroupIdForShow
     * @param fkGroupId
     * @return
     *
     */
    List<SyncGroupPO> listByGroupIdForShow(Integer fkGroupId);

    /**
     * 
     * @Title: getById
     * @return
     *
     */
    SyncGroupPO getById(Long id);

    /**
     * 保存或者更新数据
     * 
     * @Title: save
     * @param record
     *
     */
    void saveOrUpdate(SyncGroupPO record);

    /**
     * 根据租户id和模块查询需要同步的租户id
     * 
     * @Title: listTenantId
     * @param tenantId
     * @param modules
     * @return
     *
     */
    List<Integer> listTenantId(Integer tenantId, String... modules);

    /**
     * 删除id删除同步组
     * 
     * @Title: removeById
     * @param id
     *
     */
    void removeById(Long id);

    /**
     * 查询可以关联的租户列表
     * 
     * @Title: listCanAssociateTenants
     * @param syncGroupId
     * @return
     *
     */
    List<Map<String, String>> listCanAssociateTenants(Long syncGroupId);
}
