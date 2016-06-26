/**   
 * @Title: DataUtil.java 
 * @Package com.xtt.txgl.common.util
 * Copyright: Copyright (c) 2015
 * @author: Tik   
 * @date: 2015年9月17日 上午10:30:55 
 *
 */
package com.xtt.common.util;

import java.lang.reflect.Method;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xtt.common.util.user.UserUtil;
import com.xtt.platform.util.CommonUtil;

/**
 * @ClassName: DataUtil
 * @date: 2015年9月17日 上午10:30:55
 * @version: V1.0
 * 
 */
public class DataUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataUtil.class);

	/**
	 * 设置model系统字段值（createTime,createUserId,updateTime,updateUserId)
	 * 
	 * @Title: setSystemFieldValue
	 * @param model
	 * 
	 */
	public static void setSystemFieldValue(Object model) {
		Class<? extends Object> clazz = model.getClass();
		try {
			Method m1 = clazz.getDeclaredMethod("getCreateTime");
			Date date = new Date();
			Long userId = UserUtil.getLoginUserId();
			clazz.getDeclaredMethod("setUpdateTime", Date.class).invoke(model, date);
			clazz.getDeclaredMethod("setUpdateUserId", Long.class).invoke(model, userId);
			if (m1.invoke(model) == null) {
				clazz.getDeclaredMethod("setCreateTime", Date.class).invoke(model, date);
				clazz.getDeclaredMethod("setCreateUserId", Long.class).invoke(model, userId);
			}
		} catch (Exception e) {
			if ((clazz = clazz.getSuperclass()) != null) {
				try {
					Method m1 = clazz.getDeclaredMethod("getCreateTime");
					Date date = new Date();
					Long userId = UserUtil.getLoginUserId();
					clazz.getDeclaredMethod("setUpdateTime", Date.class).invoke(model, date);
					clazz.getDeclaredMethod("setUpdateUserId", Long.class).invoke(model, userId);
					if (m1.invoke(model) == null) {
						clazz.getDeclaredMethod("setCreateTime", Date.class).invoke(model, date);
						clazz.getDeclaredMethod("setCreateUserId", Long.class).invoke(model, userId);
					}
				} catch (Exception ex) {
					LOGGER.info("当前类和父类中不存在：", CommonUtil.getExceptionMessage(ex));
					if ((clazz = clazz.getSuperclass()) != null) {

					}
				}
			} else {
				LOGGER.info("当前类不存在：", CommonUtil.getExceptionMessage(e));
			}
		}

	}

	/**
	 * 注意：会将Id设置为null insert或者update操作时,设置model字段值(id,operatorId,isEnable,version,setUpdateTime ,setUpdateUserId,setCreateTime,setCreateUserId)
	 * 
	 * @Title: updateVersion
	 * @param model
	 * @param version
	 * 
	 */
	public static void setVersion(Object model, Integer version) {
		Class<? extends Object> clazz = model.getClass();
		Long userId = UserUtil.getLoginUserId();
		Long id = null;
		try {
			try {
				clazz.getDeclaredMethod("setId", Long.class).invoke(model, id);
				clazz.getDeclaredMethod("setOperatorId", Long.class).invoke(model, userId);
				clazz.getDeclaredMethod("setIsEnable", Boolean.class).invoke(model, true);
				clazz.getDeclaredMethod("setVersion", Integer.class).invoke(model, version);
				Method m1 = clazz.getDeclaredMethod("getCreateTime");
				Date date = new Date();
				clazz.getDeclaredMethod("setUpdateTime", Date.class).invoke(model, date);
				clazz.getDeclaredMethod("setUpdateUserId", Long.class).invoke(model, userId);
				if (m1.invoke(model) == null) {
					clazz.getDeclaredMethod("setCreateTime", Date.class).invoke(model, date);
					clazz.getDeclaredMethod("setCreateUserId", Long.class).invoke(model, userId);
				}
			} catch (Exception e) {
				if ((clazz = clazz.getSuperclass()) != null) {
					clazz.getDeclaredMethod("setId", Long.class).invoke(model, id);
					clazz.getDeclaredMethod("setOperatorId", Long.class).invoke(model, userId);
					clazz.getDeclaredMethod("setIsEnable", Boolean.class).invoke(model, true);
					clazz.getDeclaredMethod("setVersion", Integer.class).invoke(model, version);
					Method m1 = clazz.getDeclaredMethod("getCreateTime");
					Date date = new Date();
					clazz.getDeclaredMethod("setUpdateTime", Date.class).invoke(model, date);
					clazz.getDeclaredMethod("setUpdateUserId", Long.class).invoke(model, userId);
					if (m1.invoke(model) == null) {
						clazz.getDeclaredMethod("setCreateTime", Date.class).invoke(model, date);
						clazz.getDeclaredMethod("setCreateUserId", Long.class).invoke(model, userId);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * 获取对象指定方法值（方法不带参数）
	 * 
	 * @Title: getValue
	 * @param obj
	 * @param methodName
	 * @return
	 * @throws Exception
	 * 
	 */
	public static Object getValue(Object obj, String methodName) throws Exception {
		Class<? extends Object> clazz = obj.getClass();
		try {
			Method m1 = clazz.getDeclaredMethod(methodName);
			return m1.invoke(obj);
		} catch (Exception e) {
			try {
				if ((clazz = clazz.getSuperclass()) != null) {
					Method m1 = clazz.getDeclaredMethod(methodName);
					return m1.invoke(obj);
				} else {
					throw e;
				}
			} catch (Exception ex) {
				throw ex;
			}
		}

	}

	/**
	 * 处理动态SQL特殊字符转义
	 * 
	 * @Title: escape
	 * @param sql
	 * @return
	 * 
	 */
	public static String escape(String sql) {
		if (sql == null)
			return "";
		return sql.replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\'").replaceAll("\"", "\\\"");
	}

	/**
	 * 处理动态SQL特殊字符转义
	 * 
	 * @Title: sqlStr
	 * @param sql
	 * @return
	 * 
	 */
	public static String sqlStr(Object field) {
		if (field == null)
			return "''";
		if (field instanceof String) {
			return "'" + field + "'";
		} else if (field instanceof Boolean) {
			return (Boolean) field ? "1" : "0";
		} else {
			return field.toString();
		}
	}
}
