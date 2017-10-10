package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.AssayHospDictGroupMapping;

@Repository
public interface AssayHospDictGroupMappingMapper {
    int insert(AssayHospDictGroupMapping record);

    int insertSelective(AssayHospDictGroupMapping record);

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
     * @param tenantId
     * @return
     *
     */
    int getCountByGroupId(@Param("fkGroupId") String fkGroupId, @Param("fkItemCode") String fkItemCode, @Param("tenantId") Integer tenantId);

    /**
     * 根据租户id删除数据
     * 
     * @Title: deleteByTenant
     * @param tenantId
     *
     */
    void deleteByTenant(@Param("tenantId") Integer tenantId);

    /**
     * 根据groupId itemCode 删除数据
     * 
     * @Title: deleteByGroupIdAndItemCode
     * @param groupId
     * @param itemCode
     * @param fkTenantId
     *
     */
    void deleteByGroupIdAndItemCode(@Param("fkGroupId") String groupId, @Param("fkItemCode") String itemCode, @Param("tenantId") Integer fkTenantId);
}