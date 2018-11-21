/**   
 * @Title: AppDictCache.java 
 * @Package com.xtt.common.cache
 * Copyright: Copyright (c) 2015
 * @author: xyw   
 * @date: 2018年11月8日 下午1:48:13 
 *
 */
package com.xtt.common.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import com.xtt.common.dto.AppDictDto;
import com.xtt.platform.framework.core.redis.RedisCacheUtil;
import com.xtt.platform.util.lang.StringUtil;

/**
 * 字典缓存
 * 
 * @ClassName: AppDictCache
 * @date: 2018年11月8日 下午2:20:13
 * @version: V1.0
 *
 */
public class AppDictCache {
    public static String getKey(String type) {
        return "appDictionary" + (StringUtil.isBlank(type) ? "*" : type);
    }

    public static void cacheALL(List<AppDictDto> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            Map<String, List<AppDictDto>> map = new HashMap<String, List<AppDictDto>>();
            AppDictDto obj;
            String key;
            List<AppDictDto> typeList;
            for (int i = 0; i < list.size(); i++) {
                obj = list.get(i);
                key = getKey(obj.getpItemCode());
                if (map.containsKey(key)) {
                    typeList = map.get(key);
                } else {
                    typeList = new ArrayList<AppDictDto>();
                    map.put(key, typeList);
                }
                typeList.add(obj);
            }
            RedisCacheUtil.batchSetObject(map);
        }
    }

    /**
     * 获取itemName
     * 
     * @Title: getItemName
     * @param pItemCode
     * @param itemCode
     * @return
     *
     */
    public static String getItemName(String pItemCode, String itemCode) {
        AppDictDto appDictDto = get(pItemCode, itemCode);
        if (appDictDto != null) {
            return appDictDto.getItemName();
        } else {
            return null;
        }
    }

    /**
     * 获取对象
     * 
     * @Title: get
     * @param pItemCode
     * @param itemCode
     * @return
     *
     */
    @SuppressWarnings("unchecked")
    public static AppDictDto get(String pItemCode, String itemCode) {
        List<AppDictDto> list = (List<AppDictDto>) RedisCacheUtil.getObject(getKey(pItemCode));
        if (CollectionUtils.isNotEmpty(list)) {
            AppDictDto obj;
            for (int i = 0; i < list.size(); i++) {
                obj = list.get(i);
                if (obj.getItemCode().equals(itemCode)) {
                    return obj;
                }
            }
        }
        return null;
    }

    /**
     * 根据pItemCode获取list
     * 
     * @Title: listByPItemCode
     * @param pItemCode
     * @return
     *
     */
    @SuppressWarnings("unchecked")
    public static List<AppDictDto> listByPItemCode(String pItemCode) {
        List<AppDictDto> list = (List<AppDictDto>) RedisCacheUtil.getObject(getKey(pItemCode));
        if (list != null) {
            return list;
        }
        return new ArrayList<AppDictDto>();
    }

    /**
     * 清空缓存
     * 
     * @Title: clear
     *
     */
    public static void clear() {
        RedisCacheUtil.deletePattern(getKey(null));
    }
}
