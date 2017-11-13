/**   
 * @Title: LicenseCache.java 
 * @Package com.xtt.common.cache
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年06月14日 上午10:51:51 
 *
 */
package com.xtt.common.cache;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import com.xtt.common.constants.CommonConstants;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.framework.core.redis.RedisCacheUtil;
import com.xtt.platform.util.security.MD5Util;
import com.xtt.platform.util.time.DateFormatUtil;

public class TenantAuthorityCache {

    /**
     * 缓存所有license
     * 
     * @Title: cacheALL
     * @param map
     *
     */
    public static void cacheALL(Map<String, String> map) {

        long timeout;
        Date sysDate = new Date();
        for (Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().substring(5).equals(CommonConstants.ONLINE_NUM) || entry.getKey().substring(5).equals(CommonConstants.BED_NUM)) {
                RedisCacheUtil.setObject(MD5Util.md5(entry.getKey()), entry.getValue());
            } else {
                Date date = DateFormatUtil.convertStrToDate(entry.getValue(), "yyyyMMdd");
                timeout = date.getTime() - sysDate.getTime();
                if (timeout > 0) {
                    RedisCacheUtil.setObjectWithTimeout(MD5Util.md5(entry.getKey()), entry.getValue(), timeout);
                }
            }
        }
    }

    /**
     * 根据类别获取当前类别值
     * 
     * @Title: getValue
     * @param category
     * @return
     *
     */
    public static String getValue(String category) {
        return (String) RedisCacheUtil.getObject(MD5Util.md5(UserUtil.getTenantId() + category));
    }

    /**
     * 获取透析机数量
     * 
     * @Title: getOnlineNum
     * @param tenantId
     * @return
     *
     */
    public static Integer getBedNum(Integer tenantId) {
        try {
            if (UserUtil.getLoginUser() == null || UserUtil.getTenantId() == null) {
                UserUtil.setThreadTenant(tenantId);
            }
            return Integer.parseInt(getValue(CommonConstants.BED_NUM));
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 获取联机数量
     * 
     * @Title: getOnlineNum
     * @param tenantId
     * @return
     *
     */
    public static Integer getOnlineNum(Integer tenantId) {
        try {
            UserUtil.setThreadTenant(tenantId);
            return Integer.parseInt(getValue(CommonConstants.ONLINE_NUM));
        } catch (Exception e) {
            return 0;
        }
    }

}
