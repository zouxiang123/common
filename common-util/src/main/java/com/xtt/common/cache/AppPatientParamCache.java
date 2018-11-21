/**   
 * @Title: AppPatientParamCache.java 
 * @Package com.xtt.common.cache
 * Copyright: Copyright (c) 2015
 * @author: xyw   
 * @date: 2018年11月9日 下午5:30:50 
 *
 */
package com.xtt.common.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import com.xtt.common.dto.AppPatientParamDto;
import com.xtt.platform.framework.core.redis.RedisCacheUtil;

public class AppPatientParamCache {
    public static String getKey(Long fkUserId) {
        return "appPatientParam" + (fkUserId == null ? "*" : String.valueOf(fkUserId));
    }

    public static void cacheALL(List<AppPatientParamDto> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            Map<String, List<AppPatientParamDto>> map = new HashMap<String, List<AppPatientParamDto>>();
            AppPatientParamDto obj;
            String key;
            List<AppPatientParamDto> typeList;
            for (int i = 0; i < list.size(); i++) {
                obj = list.get(i);
                key = getKey(obj.getFkUserId());
                if (map.containsKey(key)) {
                    typeList = map.get(key);
                } else {
                    typeList = new ArrayList<AppPatientParamDto>();
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
    public static String getItemName(Long fkUserId, String itemCode) {
        AppPatientParamDto appDictDto = get(fkUserId, itemCode);
        if (appDictDto != null) {
            return appDictDto.getItemName();
        } else {
            return null;
        }
    }

    /**
     * 获取itemValue
     * 
     * @Title: getItemValue
     * @param fkUserId
     * @param itemCode
     * @return
     *
     */
    public static String getItemValue(Long fkUserId, String itemCode) {
        AppPatientParamDto appDictDto = get(fkUserId, itemCode);
        if (appDictDto != null) {
            return appDictDto.getItemValue();
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
    public static AppPatientParamDto get(Long fkUserId, String itemCode) {
        List<AppPatientParamDto> list = (List<AppPatientParamDto>) RedisCacheUtil.getObject(getKey(fkUserId));
        if (CollectionUtils.isNotEmpty(list)) {
            AppPatientParamDto obj;
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
    public static List<AppPatientParamDto> listByFkUserId(Long fkUserId) {
        List<AppPatientParamDto> list = (List<AppPatientParamDto>) RedisCacheUtil.getObject(getKey(fkUserId));
        if (list != null) {
            return list;
        }
        return new ArrayList<AppPatientParamDto>();
    }

    /**
     * 清空缓存
     * 
     * @Title: clear
     *
     */
    public static void clear(Long fkUserId) {
        RedisCacheUtil.deletePattern(getKey(fkUserId));
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
