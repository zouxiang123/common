/**   
 * @Title: FamilyInitialDto.java 
 * @Package com.xtt.common.dto
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年4月28日 上午11:22:32 
 *
 */
package com.xtt.common.dto;

public class FamilyInitialDto {
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
