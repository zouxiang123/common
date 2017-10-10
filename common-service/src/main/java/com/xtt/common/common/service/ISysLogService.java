/**   
 * @Title: ISysLogService.java 
 * @Package com.xtt.common.common.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年8月15日 下午5:55:42 
 *
 */
package com.xtt.common.common.service;

import com.xtt.common.dao.model.SysLog;
import com.xtt.common.dao.po.SysLogPO;

public interface ISysLogService {
    public void insert(SysLog record);

    /**
     * 查询系统日志
     * 
     * @Title: selectSysLog
     * @param logInfo
     *            日志内容
     * @return
     * 
     */
    public SysLog selectSysLog(SysLogPO record);

    /**
     * 插入系统日志
     * 
     * @Title: insertSysLog
     * @param type
     *            日志类型 1：流程操作 2：登入、登出、个人资料修改 3：其它
     * @param logInfo
     *            日志内容
     * @param sysOwner
     *            所属系统
     * @return
     * 
     */
    public void insertSysLog(String type, String logInfo, String sysOwner);
}
