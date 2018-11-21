package com.xtt.common.dao.model;

import java.util.Date;

/**
 * app_gather_info
 */
public class AppGatherInfo {
    /**
     * app_gather_info.id
     */
    private Long id;

    /**
     * app_gather_info.class_name
     */
    private String className;

    /**
     * 上次收集时间
     * app_gather_info.last_gather_time
     */
    private Date lastGatherTime;

    /**
     */
    public Long getId() {
        return id;
    }

    /**
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     */
    public String getClassName() {
        return className;
    }

    /**
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * 上次收集时间
     */
    public Date getLastGatherTime() {
        return lastGatherTime;
    }

    /**
     * 上次收集时间
     */
    public void setLastGatherTime(Date lastGatherTime) {
        this.lastGatherTime = lastGatherTime;
    }
}