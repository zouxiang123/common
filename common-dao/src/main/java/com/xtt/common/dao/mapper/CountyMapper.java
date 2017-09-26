package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.County;

@Repository
public interface CountyMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table province
	 * 
	 * @mbggenerated Wed Sep 16 11:45:16 CST 2015
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table province
	 * 
	 * @mbggenerated Wed Sep 16 11:45:16 CST 2015
	 */
	int insert(County record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table province
	 * 
	 * @mbggenerated Wed Sep 16 11:45:16 CST 2015
	 */
	int insertSelective(County record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table province
	 * 
	 * @mbggenerated Wed Sep 16 11:45:16 CST 2015
	 */
	County selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table province
	 * 
	 * @mbggenerated Wed Sep 16 11:45:16 CST 2015
	 */
	int updateByPrimaryKeySelective(County record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table province
	 * 
	 * @mbggenerated Wed Sep 16 11:45:16 CST 2015
	 */
	int updateByPrimaryKey(County record);

	List<County> selectByProvinceId(Integer fkProvinceId);
}