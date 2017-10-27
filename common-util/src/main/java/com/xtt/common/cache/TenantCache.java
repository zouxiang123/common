/**   
 * @Title: SysTenantCache.java 
 * @Package com.xtt.common.cache
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年10月27日 上午10:27:04 
 *
 */
package com.xtt.common.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xtt.common.dto.TenantDto;
import com.xtt.platform.framework.core.redis.RedisCacheUtil;

public class TenantCache {
    private static final Logger LOGGER = LoggerFactory.getLogger(TenantCache.class);

    public static String getKey(Integer id) {
        return "sysTenant" + (id == null ? "*" : id);
    }

    /**
     * 缓存所有
     * 
     * @Title: cacheAll
     * @param list
     *
     */
    public static void cacheAll(List<TenantDto> list) {
        if (list != null && !list.isEmpty()) {
            Map<String, Object> map = new HashMap<String, Object>();
            TenantDto item;
            String key;
            for (int i = 0; i < list.size(); i++) {
                item = list.get(i);
                key = getKey(item.getId());
                map.put(key, item);
            }
            RedisCacheUtil.batchSetObject(map);
        }
    }

    /**
     * 获取默认租户
     * 
     * @Title: getDefault
     * @return
     *
     */
    @SuppressWarnings("unchecked")
    public static TenantDto getDefault() {
        try {
            List<TenantDto> list = (List<TenantDto>) RedisCacheUtil.getByPattern(getKey(null));
            if (CollectionUtils.isNotEmpty(list)) {
                for (TenantDto tenant : list) {
                    if (tenant.getIsDefault()) {
                        return tenant;
                    }
                }
            }
            return null;
        } catch (Exception e) {
            LOGGER.warn("get default tenant from redis failed ,case by", e);
            return null;
        }
    }

    /**
     * 根据id获取数据
     * 
     * @Title: getById
     * @param id
     * @return
     *
     */
    public static TenantDto getById(Integer id) {
        if (id == null)
            return null;
        try {
            return (TenantDto) RedisCacheUtil.getObject(getKey(id));
        } catch (Exception e) {
            LOGGER.warn("get tenant from redis failed ,case by", e);
            return null;
        }
    }
}
