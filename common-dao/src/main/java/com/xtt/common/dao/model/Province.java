package com.xtt.common.dao.model;

public class Province {
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column province.id
     *
     * @mbggenerated Fri Sep 18 18:51:50 CST 2015
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column province.name
     *
     * @mbggenerated Fri Sep 18 18:51:50 CST 2015
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column province.is_enable
     *
     * @mbggenerated Fri Sep 18 18:51:50 CST 2015
     */
    private Boolean isEnable;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column province.id
     *
     * @return the value of province.id
     *
     * @mbggenerated Fri Sep 18 18:51:50 CST 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column province.id
     *
     * @param id
     *            the value for province.id
     *
     * @mbggenerated Fri Sep 18 18:51:50 CST 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column province.name
     *
     * @return the value of province.name
     *
     * @mbggenerated Fri Sep 18 18:51:50 CST 2015
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column province.name
     *
     * @param name
     *            the value for province.name
     *
     * @mbggenerated Fri Sep 18 18:51:50 CST 2015
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column province.is_enable
     *
     * @return the value of province.is_enable
     *
     * @mbggenerated Fri Sep 18 18:51:50 CST 2015
     */
    public Boolean getIsEnable() {
        return isEnable;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column province.is_enable
     *
     * @param isEnable
     *            the value for province.is_enable
     *
     * @mbggenerated Fri Sep 18 18:51:50 CST 2015
     */
    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }
}