package com.xtt.common.dao.mapper;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.SysTenant;
import com.xtt.common.dao.po.SysTenantPO;

@Repository
public interface SysTenantMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysTenant record);

    int insertSelective(SysTenant record);

    SysTenant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysTenant record);

    int updateByPrimaryKey(SysTenant record);

    /* user define */

    /**
     * 根据自定义条件查询租户数据
     * 
     * @Title: listByCondition
     * @param record
     * @return
     *
     */
    List<SysTenant> listByCondition(SysTenant record);

    /**
     * 根据账户名称查询对应的租户数据
     *
     * @param account
     * @param sysOwner
     * @param delFlag
     * @return
     */
    List<SysTenant> listByAccount(@Param("account") String account, @Param("sysOwner") String sysOwner, @Param("delFlag") Boolean delFlag);

    /**
     * 根据集团id查询所有普通租户
     * 
     * @Title: listByGroupId
     * @param groupId
     * @param isEnable
     * @return
     *
     */
    List<SysTenant> listAllNormalByGroupId(@Param("groupId") Integer groupId, @Param("isEnable") Boolean isEnable);

    /**
     * 根据多个租户id查询数据
     * 
     * @Title: listByIds
     * @param ids
     * @return
     *
     */
    List<SysTenant> listByIds(@Param("ids") Collection<Integer> ids);

    /**
     * 更新新插入并发症字典中pid 这列 表中内关联
     * 
     * @Title: upComplicationDictionary
     * @param sysBasicsGroupPo
     *
     */
    void updateComplicationDictionary(SysTenantPO sysTenant);

    /**
     * 更新预估配置表pid 表中设计为内关联
     * 
     * @Title: upMedicalOrderDict
     * @param sysBasicsGroupPo
     *
     */
    void updateMedicalOrderDict(SysTenantPO sysTenant);

    /**
     * 更新高价值耗材表Pid
     * 
     * @Title: saveSupplies
     * @param sysBasicsGroupPo
     *
     */
    void updateSupplies(SysTenantPO sysTenant);

    /**
     * 质控上传化验项配置
     * 
     * @Title: saveZkAssayRef
     * @param sysBasicsGroupPo
     *
     */
    void saveZkAssayRef(SysTenantPO sysTenant);

    /**
     * 医嘱套餐和医嘱项目的关系表
     * 
     * @Title: saveMedicalOrderDictRPackage
     * @param sysBasicsGroupPo
     *
     */
    void saveMedicalOrderDictRPackage(SysTenantPO sysTenant);

    /**
     * 获取字典表中的列名
     * 
     * @Title: getTablePropertyName
     * @param tableSchema
     * @param tableName
     * @return
     *
     */
    List<String> listTablePropertyName(@Param("tableSchema") String tableSchema, @Param("tableName") String tableName);

    /**
     * 通用方法初始化数据
     * 
     * @Title: saveSysBasiCsGroup
     * @param tableName
     * @param tablePropertys
     * @param newtablePropertys
     * @param sysBasicsGroupPo
     *
     */
    void saveSysBasiCsGroup(@Param("tableName") String tableName, @Param("tablePropertys") String tablePropertys,
                    @Param("newtablePropertys") String newtablePropertys, @Param("param") SysTenantPO sysTenant);

    /**
     * 设置主键值
     * 
     * @Title: setPrimaryKeyById
     * @param string
     *
     */
    void setPrimaryKeyById(@Param("tableName") String tableName);

    /**
     * 更加父级点查询
     * 
     * @Title: listByPTenantId
     * @param pTenantId
     * @return
     *
     */
    List<SysTenant> listByPTenantId(Integer pTenantId);
}