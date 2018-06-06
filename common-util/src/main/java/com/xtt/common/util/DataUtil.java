/**   
 * @Title: DataUtil.java 
 * @Package com.xtt.common.common.util
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
        setSystemFieldValue(model, UserUtil.getLoginUserId());
    }

    /**
     * 设置model系统字段值（createTime,createUserId,updateTime,updateUserId)
     * 
     * @Title: setSystemFieldValue
     * @param model
     * 
     */
    public static void setSystemFieldValue(Object model, Long userId) {
        Class<? extends Object> clazz = model.getClass();
        setSystemFieldValue(model, clazz, userId, 1);
    }

    private static void setSystemFieldValue(Object model, Class<? extends Object> clazz, Long userId, int times) {
        if (times > 4)// max try 4 times
            return;
        try {
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
                setSystemFieldValue(model, clazz, userId, ++times);
            } else {
                LOGGER.info("当前类和父类中不存在：", e);
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
        setVersion(model, version, UserUtil.getLoginUserId());
    }

    /**
     * 注意：会将Id设置为null insert或者update操作时,设置model字段值(id,operatorId,isEnable,version,setUpdateTime ,setUpdateUserId,setCreateTime,setCreateUserId)
     * 
     * @Title: updateVersion
     * @param model
     * @param version
     * @param userId
     * 
     */
    public static void setVersion(Object model, Integer version, Long userId) {
        Class<? extends Object> clazz = model.getClass();
        setVersion(model, version, userId, clazz, 1);
    }

    /**
     * 注意：会将Id设置为null insert或者update操作时,设置model字段值(id,operatorId,isEnable,version,setUpdateTime ,setUpdateUserId,setCreateTime,setCreateUserId)
     * 
     * @Title: updateVersion
     * @param model
     * @param version
     * 
     */
    private static void setVersion(Object model, Integer version, Long userId, Class<? extends Object> clazz, int times) {
        if (times > 4)// max try 4 times
            return;
        Long id = null;
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
                setVersion(model, version, userId, clazz, ++times);
            } else {
                LOGGER.error(e.getMessage(), e);
            }
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
        return getValue(obj, methodName, clazz, 1);
    }

    public static Object getValue(Object obj, String methodName, Class<? extends Object> clazz, int times) throws Exception {
        if (times > 4)// max try 4 times
            return null;
        try {
            Method m1 = clazz.getDeclaredMethod(methodName);
            return m1.invoke(obj);
        } catch (Exception e) {
            if ((clazz = clazz.getSuperclass()) != null) {
                return getValue(obj, methodName, clazz, ++times);
            } else {
                throw e;
            }
        }

    }

    /**
     * 设置设置model系统字段值（createTime,createUserId,updateTime,updateUserId)，不论是否已经存在
     * 
     * @Title: setSystemFieldValue
     * @param model
     * 
     */
    public static void setAllSystemFieldValue(Object model) {
        setAllSystemFieldValue(model, UserUtil.getLoginUserId());
    }

    /**
     * 设置设置model系统字段值（createTime,createUserId,updateTime,updateUserId)，不论是否已经存在
     * 
     * @Title: setAllSystemFieldValue
     * @param model
     * @param userId
     *
     */
    public static void setAllSystemFieldValue(Object model, Long userId) {
        Class<? extends Object> clazz = model.getClass();
        setAllSystemFieldValue(model, clazz, userId, 1);
    }

    private static void setAllSystemFieldValue(Object model, Class<? extends Object> clazz, Long userId, int times) {
        if (times > 4)// max try 4 times
            return;
        Date date = new Date();
        try {
            clazz.getDeclaredMethod("setUpdateTime", Date.class).invoke(model, date);
            clazz.getDeclaredMethod("setUpdateUserId", Long.class).invoke(model, userId);
            clazz.getDeclaredMethod("setCreateTime", Date.class).invoke(model, date);
            clazz.getDeclaredMethod("setCreateUserId", Long.class).invoke(model, userId);
        } catch (Exception e) {
            if ((clazz = clazz.getSuperclass()) != null) {
                setAllSystemFieldValue(model, clazz, userId, ++times);
            } else {
                LOGGER.info("当前类和父类中不存在：", e);
            }
        }
    }

    /**
     * 设置更新的系统字段(setUpdateTime ,setUpdateUserId)
     * 
     * @Title: setUpdateSystemFieldValue
     * @param model
     *
     */
    public static void setUpdateSystemFieldValue(Object model) {
        Class<? extends Object> clazz = model.getClass();
        setUpdateSystemFieldValue(model, clazz, UserUtil.getLoginUserId(), new Date(), 1);
    }

    /**
     * 设置更新的系统字段(setUpdateTime ,setUpdateUserId)
     * 
     * @Title: setUpdateSystemFieldValue
     * @param model
     *
     */
    public static void setUpdateSystemFieldValue(Object model, Long userId) {
        Class<? extends Object> clazz = model.getClass();
        setUpdateSystemFieldValue(model, clazz, userId, new Date(), 1);
    }

    private static void setUpdateSystemFieldValue(Object model, Class<? extends Object> clazz, final Long userId, final Date date, int times) {
        if (times > 4)// max try 4 times
            return;
        try {
            clazz.getDeclaredMethod("setUpdateTime", Date.class).invoke(model, date);
            clazz.getDeclaredMethod("setUpdateUserId", Long.class).invoke(model, userId);
        } catch (Exception e) {
            if ((clazz = clazz.getSuperclass()) != null) {
                setUpdateSystemFieldValue(model, clazz, userId, date, ++times);
            } else {
                LOGGER.info("当前类和父类中不存在：", e);
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
        return sql.replaceAll("\\\\", "\\\\\\\\\\\\\\\\").replaceAll("'", "\\\\\'").replaceAll("\"", "\\\\\"");
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
