package com.xtt.common.dao.model;

public class County {
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column county.id
	 * 
	 * @mbggenerated Fri Sep 18 18:51:50 CST 2015
	 */
	private Integer id;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column county.name
	 * 
	 * @mbggenerated Fri Sep 18 18:51:50 CST 2015
	 */
	private String name;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column county.fk_province_id
	 * 
	 * @mbggenerated Fri Sep 18 18:51:50 CST 2015
	 */
	private Integer fkProvinceId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column county.is_enable
	 * 
	 * @mbggenerated Fri Sep 18 18:51:50 CST 2015
	 */
	private Boolean isEnable;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column county.id
	 * 
	 * @return the value of county.id
	 * 
	 * @mbggenerated Fri Sep 18 18:51:50 CST 2015
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column county.id
	 * 
	 * @param id
	 *            the value for county.id
	 * 
	 * @mbggenerated Fri Sep 18 18:51:50 CST 2015
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column county.name
	 * 
	 * @return the value of county.name
	 * 
	 * @mbggenerated Fri Sep 18 18:51:50 CST 2015
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column county.name
	 * 
	 * @param name
	 *            the value for county.name
	 * 
	 * @mbggenerated Fri Sep 18 18:51:50 CST 2015
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column county.fk_province_id
	 * 
	 * @return the value of county.fk_province_id
	 * 
	 * @mbggenerated Fri Sep 18 18:51:50 CST 2015
	 */
	public Integer getFkProvinceId() {
		return fkProvinceId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column county.fk_province_id
	 * 
	 * @param fkProvinceId
	 *            the value for county.fk_province_id
	 * 
	 * @mbggenerated Fri Sep 18 18:51:50 CST 2015
	 */
	public void setFkProvinceId(Integer fkProvinceId) {
		this.fkProvinceId = fkProvinceId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column county.is_enable
	 * 
	 * @return the value of county.is_enable
	 * 
	 * @mbggenerated Fri Sep 18 18:51:50 CST 2015
	 */
	public Boolean getIsEnable() {
		return isEnable;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column county.is_enable
	 * 
	 * @param isEnable
	 *            the value for county.is_enable
	 * 
	 * @mbggenerated Fri Sep 18 18:51:50 CST 2015
	 */
	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}
}