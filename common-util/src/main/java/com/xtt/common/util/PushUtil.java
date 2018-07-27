/**   
 * @Title: PushUtil.java 
 * @Package com.xtt.common.util
 * Copyright: Copyright (c) 2015
 * @author: zx   
 * @date: 2018年7月27日 上午8:57:35 
 *
 */
package com.xtt.common.util;

import java.util.Map;
import java.util.Objects;

import com.xtt.common.constants.CmSysParamConsts;
import com.xtt.platform.framework.core.redis.RedisCacheUtil;

public class PushUtil {
    /**
     * 推送数据处理
     * 
     * @Title: pushAppData
     * @param m
     * @param type
     *
     */
    public static void pushAppData(Map<String, String> m, String type) {
        String domain = SysParamUtil.getValueByName(CmSysParamConsts.HD_APP_ADDR);
        if (Objects.equals(domain, "0")) {// 地址为0视为不启用
            return;
        }
        // 发布
        RedisCacheUtil.publish(type, m);
    }

}
