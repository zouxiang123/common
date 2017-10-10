package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.AssayHospDictGroup;

@Repository
public interface AssayHospDictGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AssayHospDictGroup record);

    int insertSelective(AssayHospDictGroup record);

    AssayHospDictGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AssayHospDictGroup record);

    int updateByPrimaryKey(AssayHospDictGroup record);

    /*--------- 自定义 ------------*/
    /**
     * 查询是否存在
     * 
     * @Title: getCountByGroupId
     * @param groupId
     * @param tenantId
     * @return
     *
     */
    int getCountByGroupId(@Param("groupId") String groupId, @Param("tenantId") Integer tenantId);

    /**
     * 根据租户号删除数据
     * 
     * @Title: deleteByTenant
     * @param tenantId
     *
     */
    void deleteByTenant(@Param("tenantId") Integer tenantId);

    /**
     * 根据是否是手动插入标识查询数据
     * 
     * @Title: listByIsAuto
     * @param isAuto
     * @param fkTenantId
     * @return
     *
     */
    List<AssayHospDictGroup> listByIsAuto(@Param("isAuto") Boolean isAuto, @Param("fkTenantId") Integer fkTenantId);

}