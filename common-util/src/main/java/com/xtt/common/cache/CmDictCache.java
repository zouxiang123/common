/**   
 * @Title: DictionaryCache.java 
 * @Package com.xtt.txgl.common.cache
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年8月18日 上午11:41:36 
 *
 */
package com.xtt.common.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import com.xtt.common.dto.DictDto;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.framework.core.redis.RedisCacheUtil;
import com.xtt.platform.util.lang.StringUtil;

@SuppressWarnings("unchecked")
public class CmDictCache implements ICmDictFactory {

    public static String getKey(Integer tenantId, String type) {
        return tenantId + "dictionary" + (StringUtil.isBlank(type) ? "*" : type);
    }

    public static void cacheALL(List<DictDto> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            Map<String, List<DictDto>> map = new HashMap<String, List<DictDto>>();
            DictDto obj;
            String key;
            List<DictDto> typeList;
            for (int i = 0; i < list.size(); i++) {
                obj = list.get(i);
                key = getKey(obj.getFkTenantId(), obj.getpItemCode());
                if (map.containsKey(key)) {
                    typeList = map.get(key);
                } else {
                    typeList = new ArrayList<DictDto>();
                    map.put(key, typeList);
                }
                typeList.add(obj);
            }
            RedisCacheUtil.batchSetObject(map);
        }
    }

    @Override
    public String getItemName(String type, String value) {
        List<DictDto> list = (List<DictDto>) RedisCacheUtil.getObject(getKey(UserUtil.getTenantId(), type));
        if (CollectionUtils.isNotEmpty(list)) {
            DictDto obj;
            for (int i = 0; i < list.size(); i++) {
                obj = list.get(i);
                if (obj.getItemCode().equals(value)) {
                    return obj.getItemName();
                }
            }
        }
        return null;
    }

    @Override
    public List<DictDto> listByPItemCode(String type) {
        List<DictDto> list = (List<DictDto>) RedisCacheUtil.getObject(getKey(UserUtil.getTenantId(), type));
        if (list != null) {
            return list;
        }
        return new ArrayList<DictDto>();
    }

    @Override
    public String getItemCode(String type, String name) {
        List<DictDto> list = (List<DictDto>) RedisCacheUtil.getObject(getKey(UserUtil.getTenantId(), type));
        if (CollectionUtils.isNotEmpty(list)) {
            DictDto obj;
            for (int i = 0; i < list.size(); i++) {
                obj = list.get(i);
                if (obj.getItemName().equals(name)) {
                    return obj.getItemCode();
                }
            }
        }
        return null;
    }

}
