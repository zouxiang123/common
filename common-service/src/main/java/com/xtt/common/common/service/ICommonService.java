/**   
 * @Title: ICommonService.java 
 * @Package com.xtt.txgl.common.service
 * Copyright: Copyright (c) 2015
 * @author: Tik   
 * @date: 2015年9月16日 上午11:50:43 
 *
 */
package com.xtt.common.common.service;

import java.util.List;

import com.xtt.common.dao.model.County;
import com.xtt.common.dao.model.Feedback;
import com.xtt.common.dao.model.Province;
import com.xtt.common.dao.model.SysLog;
import com.xtt.common.dao.po.SysLogPO;
import com.xtt.common.dao.po.SysUserPO;

/**
 * @ClassName: ICommonService
 * @date: 2015年9月16日 上午11:50:43
 * @version: V1.0
 * 
 */
public interface ICommonService {

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
	 * @return
	 * 
	 */
	public int insertSysLog(String type, String logInfo);

	/**
	 * 保存意见反馈
	 * 
	 * @Title: saveFeedback
	 * @param feedback
	 * @return
	 *
	 */
	public int saveFeedback(Feedback feedback);

	/**
	 * 获取所有省份
	 * 
	 * @Title: getProvinceList
	 * @return
	 * 
	 */
	public List<Province> getProvinceList();

	/**
	 * 获取指定省份下所有的县/区
	 * 
	 * @Title: getCountyList
	 * @param provinceId
	 * @return
	 * 
	 */
	public List<County> getCountyList(Integer provinceId);

	/**
	 * 根据姓名查询患者
	 * 
	 * @Title: searchPersonByName
	 * @param name
	 * @param tenantId
	 * @return
	 *
	 */
	public List<SysUserPO> searchPersonByName(String name, Integer tenantId);
}
