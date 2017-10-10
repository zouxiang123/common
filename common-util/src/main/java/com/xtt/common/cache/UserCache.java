/**   
 * @Title: BusinessCache.java 
 * @Package com.xtt.common.common.cache
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年8月17日 上午9:50:42 
 *
 */
package com.xtt.common.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xtt.common.dto.SysUserDto;
import com.xtt.platform.framework.core.redis.RedisCacheUtil;

public class UserCache {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserCache.class);

    public static String getKey(Long id) {
        return "sysUser" + (id == null ? "*" : String.valueOf(id));
    }

    public static void cacheAll(List<SysUserDto> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            Map<String, Object> map = new HashMap<String, Object>();
            SysUserDto obj;
            for (int i = 0; i < list.size(); i++) {
                obj = list.get(i);
                map.put(getKey(obj.getId()), obj);
            }
            RedisCacheUtil.batchSetObject(map);
        }
    }

    public static SysUserDto getById(Long id) {
        if (id == null)
            return null;
        try {
            return (SysUserDto) RedisCacheUtil.getObject(getKey(id));
        } catch (Exception e) {
            LOGGER.warn("get user data from redis", e);
            return null;
        }
    }

    /**
     * 根据用户id获取用户名称
     * 
     * @Title: getNameById
     * @param id
     * @return if user not exists then "" else user.name
     *
     */
    public static String getNameById(Long id) {
        SysUserDto user = getById(id);
        if (user != null) {
            return user.getName();
        }
        return "";
    }

    @SuppressWarnings("unchecked")
    public static Map<Long, SysUserDto> getById(Collection<Long> ids) {
        long start = System.currentTimeMillis();
        if (CollectionUtils.isEmpty(ids))
            return new HashMap<Long, SysUserDto>();
        List<String> idList = new ArrayList<String>(ids.size());
        for (Long id : ids) {
            idList.add(getKey(id));
        }
        try {
            List<SysUserDto> result = (List<SysUserDto>) RedisCacheUtil.batchGetObject(idList);
            HashMap<Long, SysUserDto> map = new HashMap<Long, SysUserDto>(result.size());
            SysUserDto obj;
            for (int i = 0; i < result.size(); i++) {
                obj = result.get(i);
                map.put(obj.getId(), obj);
            }
            result.clear();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("get {} count sysUser data from redis cost {} ms", ids.size(), (System.currentTimeMillis() - start));
            }
            return map;
        } catch (Exception e) {
            LOGGER.warn("get dialysis machine data from redis", e);
            return new HashMap<Long, SysUserDto>();
        }
    }

    /**
     * 刷新单个用户缓存
     * 
     * @Title: refresh
     * @param patient
     *
     */
    public static void refresh(SysUserDto obj) {
        if (obj.getId() == null)
            return;
        String key = getKey(obj.getId());
        RedisCacheUtil.setObject(key, obj);
    }

    /**
     * 根据id删除缓存数据
     * 
     * @Title: deleteById
     * @param id
     *            用户id
     *
     */
    public static void deleteById(Long id) {
        RedisCacheUtil.delete(getKey(id));
    }

    /**
     * 清空缓存
     * 
     * @Title: clear
     *
     */
    public static void clear() {
        RedisCacheUtil.deletePattern(UserCache.getKey(null));
    }
}
