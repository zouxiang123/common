/**   
 * @Title: FamilyInitialCache.java 
 * @Package com.xtt.txgl.common.cache
 * Copyright: Copyright (c) 2015
 * @author: abc   
 * @date: 2017年4月18日 上午10:28:08 
 *
 */
package com.xtt.common.cache;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xtt.common.dto.FamilyInitialDto;
import com.xtt.platform.framework.core.redis.RedisCacheUtil;
import com.xtt.platform.util.lang.StringUtil;

/**
 * @ClassName: FamilyInitialCache
 * @date: 2017年4月18日 上午10:28:08
 * @version: V1.0
 *
 */
public class FamilyInitialCache {
    private static final Logger LOGGER = LoggerFactory.getLogger(FamilyInitialCache.class);

    public static String getKey(String family) {
        return "familyInitialCache" + (StringUtil.isNotEmpty(family) ? family : "*");
    }

    /**
     * 初始化数据
     * 
     * @Title: init
     * @param tenantId
     *
     */
    public static void init(Map<String, FamilyInitialDto> map) {
        RedisCacheUtil.batchSetObject(map);
    }

    /**
     * 根据拼音刷新单条数据数据
     * 
     * @Title: refreshByFamily
     * @param family
     *
     */
    public static void refreshByFamily(FamilyInitialDto family) {
        if (family == null) {
            LOGGER.error("FamilyInitialCache.refreshByFamily family must be not null");
            return;
        }
        RedisCacheUtil.setObject(getKey(family.getName()), family);
    }

    /**
     * 获取单个对象根据 姓
     * 
     * @Title: getByFamily
     * @param family
     *
     */
    public static FamilyInitialDto getByFamily(String familyName) {
        if (StringUtil.isBlank(familyName)) {
            LOGGER.error("FamilyInitialCache.refreshByFamily family must be not null");
            return null;
        }
        return (FamilyInitialDto) RedisCacheUtil.getObject(getKey(familyName));
    }

    /**
     * 根据姓获取首字母
     * 
     * @Title: getInitialByFamily
     * @param familyName
     * @return
     *
     */
    public static String getInitialByFamily(String familyName) {
        FamilyInitialDto record = getByFamily(familyName);
        return record == null ? null : record.getInitial();
    }
}
