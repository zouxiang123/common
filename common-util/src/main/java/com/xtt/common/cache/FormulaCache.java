/**   
 * @Title: FormulaCache.java 
 * @Package com.xtt.common.cache
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年12月10日 上午11:51:51 
 *
 */
package com.xtt.common.cache;

import java.util.Map;

import com.xtt.common.util.UserUtil;
import com.xtt.platform.framework.core.redis.RedisCacheUtil;
import com.xtt.platform.util.lang.StringUtil;

public class FormulaCache {
    public static String getKey(Integer tenantId, String type) {
        return tenantId + "formula" + (StringUtil.isBlank(type) ? "*" : type);
    }

    public static void cacheALL(Map<String, String> map) {
        RedisCacheUtil.batchSetObject(map);
    }

    /**
     * 根据类别获取当前类别使用的公式
     * 
     * @Title: getValue
     * @param category
     * @return
     *
     */
    public static String getValue(String category) {
        return (String) RedisCacheUtil.getObject(getKey(UserUtil.getTenantId(), category));
    }
}
