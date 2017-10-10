package com.xtt.common.dao.model;

/**
 * family_initial
 */
public class FamilyInitial {
    /**
     * 姓氏名称 family_initial.name
     */
    private String name;

    /**
     * 拼音首字母 family_initial.initial
     */
    private String initial;

    /**
     * 姓氏名称
     */
    public String getName() {
        return name;
    }

    /**
     * 姓氏名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 拼音首字母
     */
    public String getInitial() {
        return initial;
    }

    /**
     * 拼音首字母
     */
    public void setInitial(String initial) {
        this.initial = initial;
    }

}