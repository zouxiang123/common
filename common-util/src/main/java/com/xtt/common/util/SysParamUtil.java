/**   
 * @Title: SysParamUtil.java 
 * @Package com.xtt.txgl.common.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年11月18日 上午9:59:51 
 *
 */
package com.xtt.common.util;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xtt.common.dto.SysParamDto;
import com.xtt.platform.framework.core.redis.RedisCacheUtil;
import com.xtt.platform.util.lang.StringUtil;

public class SysParamUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysParamUtil.class);

    public static String getKey(Integer tenantId, String key) {
        return tenantId + "sysParam" + (StringUtil.isBlank(key) ? "*" : key);
    }

    /** 加载数据到内存 */
    public static void cacheAll(List<SysParamDto> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            HashMap<String, SysParamDto> map = new HashMap<String, SysParamDto>();
            SysParamDto obj;
            String key;
            for (SysParamDto s : list) {
                obj = new SysParamDto();
                key = getKey(s.getFkTenantId(), s.getParamName());
                obj.setParamName(s.getParamName());
                obj.setParamValue(s.getParamValue());
                obj.setParamUnit(s.getParamUnit());
                map.put(key, obj);
            }
            // clear list
            list.clear();
            RedisCacheUtil.batchSetObject(map);
        }
    }

    /**
     * 根据参数名称获取参数值
     * 
     * @Title: getByName
     * @param name
     * @return
     * 
     */
    public static String getValueByName(String name) {
        SysParamDto result = getByName(UserUtil.getTenantId(), name);
        if (result != null) {
            return result.getParamValue();
        }
        return "";
    }

    /**
     * 根据参数名称获取参数对象
     * 
     * @Title: getByName
     * @param name
     * @return
     * 
     */
    public static SysParamDto getByName(String name) {
        return getByName(UserUtil.getTenantId(), name);
    }

    /**
     * 根据租户和参数名称获取参数对象
     * 
     * @Title: getByName
     * @param tenantId
     * @param name
     * @return
     * 
     */
    public static SysParamDto getByName(Integer tenantId, String name) {
        SysParamDto obj = (SysParamDto) RedisCacheUtil.getObject(getKey(tenantId, name));
        if (obj == null) {
            LOGGER.warn("param {} is not exists", name);
            obj = new SysParamDto();
        }
        return obj;
    }

    public static String getValueByName(Integer tenantId, String name) {
        return getByName(tenantId, name).getParamValue();
    }
}
