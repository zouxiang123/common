/**   
 * @Title: BusinessCache.java 
 * @Package com.xtt.txgl.common.cache
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

import com.xtt.common.dto.PatientDto;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.framework.core.redis.RedisCacheUtil;

public class PatientCache {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientCache.class);

    public static String getKey(Integer tenantId, Long id) {
        return tenantId + "patient" + (id == null ? "*" : id);
    }

    public static void cacheAll(List<PatientDto> list) {
        if (list != null && !list.isEmpty()) {
            Map<String, Object> map = new HashMap<String, Object>();
            PatientDto patient;
            String key;
            for (int i = 0; i < list.size(); i++) {
                patient = list.get(i);
                key = getKey(patient.getFkTenantId(), list.get(i).getId());
                map.put(key, patient);
            }
            RedisCacheUtil.batchSetObject(map);
        }
    }

    public static PatientDto getById(Long id) {
        if (id == null)
            return null;
        try {
            return (PatientDto) RedisCacheUtil.getObject(getKey(UserUtil.getTenantId(), id));
        } catch (Exception e) {
            LOGGER.warn("get patient data from redis", e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<Long, PatientDto> getById(Collection<Long> ids) {
        long start = System.currentTimeMillis();
        if (CollectionUtils.isEmpty(ids))
            return new HashMap<Long, PatientDto>();
        List<String> idList = new ArrayList<String>(ids.size());
        Integer tenantId = UserUtil.getTenantId();
        for (Long id : ids) {
            idList.add(getKey(tenantId, id));
        }
        try {
            List<PatientDto> result = (List<PatientDto>) RedisCacheUtil.batchGetObject(idList);
            HashMap<Long, PatientDto> map = new HashMap<Long, PatientDto>(result.size());
            PatientDto obj;
            for (int i = 0; i < result.size(); i++) {
                obj = result.get(i);
                map.put(obj.getId(), obj);
            }
            result.clear();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("get {} count patient data from redis cost {} ms", ids.size(), (System.currentTimeMillis() - start));
            }
            return map;
        } catch (Exception e) {
            LOGGER.warn("get dialysis machine data from redis", e);
            return new HashMap<Long, PatientDto>();
        }
    }

    /**
     * 刷新单个患者缓存
     * 
     * @Title: refresh
     * @param patient
     *
     */
    public static void refresh(PatientDto patient) {
        if (patient.getId() == null)
            return;
        String key = getKey(patient.getFkTenantId(), patient.getId());
        // RedisCacheUtil.delete(key);
        RedisCacheUtil.setObject(key, patient);
    }
}
