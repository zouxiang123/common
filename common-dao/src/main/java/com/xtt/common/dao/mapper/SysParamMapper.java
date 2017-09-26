package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.SysParam;
import com.xtt.common.dao.po.SysParamPO;

@Repository
public interface SysParamMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_param
	 *
	 * @mbggenerated Wed Oct 21 13:21:25 CST 2015
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_param
	 *
	 * @mbggenerated Wed Oct 21 13:21:25 CST 2015
	 */
	int insert(SysParam record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_param
	 *
	 * @mbggenerated Wed Oct 21 13:21:25 CST 2015
	 */
	int insertSelective(SysParam record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_param
	 *
	 * @mbggenerated Wed Oct 21 13:21:25 CST 2015
	 */
	SysParam selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_param
	 *
	 * @mbggenerated Wed Oct 21 13:21:25 CST 2015
	 */
	int updateByPrimaryKeySelective(SysParam record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_param
	 *
	 * @mbggenerated Wed Oct 21 13:21:25 CST 2015
	 */
	int updateByPrimaryKey(SysParam record);

	/**
	 * 通过参数名和租户id查询对象
	 * 
	 * @Title: selectByName
	 * @param name
	 * @param tenantId
	 * @return
	 *
	 */
	SysParam selectByName(@Param("name") String name, @Param("tenantId") Integer tenantId, @Param("sysOwner") String sysOwner);

	/**
	 * 查询该租户下所有的自定义参数数据
	 * 
	 * @Title: selectByTenantId
	 * @param tenantId
	 * @return
	 *
	 */
	List<SysParamPO> selectByTenantId(@Param("tenantId") Integer tenantId, @Param("sysOwner") String sysOwner);

	/**
	 * 根据条件查询数据
	 * 
	 * @Title: selectByCondition
	 * @param record
	 * @return
	 *
	 */
	List<SysParamPO> selectByCondition(SysParam record);
}