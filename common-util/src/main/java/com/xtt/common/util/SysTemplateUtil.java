/**   
 * @Title: SysTemplateUtil.java 
 * @Package com.xtt.common.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年1月6日 下午3:12:45 
 *
 */
package com.xtt.common.util;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.CookieStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dto.SysTemplateDto;
import com.xtt.platform.util.http.HttpClientResultUtil;
import com.xtt.platform.util.http.HttpClientUtil;
import com.xtt.platform.util.io.JsonUtil;
import com.xtt.platform.util.lang.StringUtil;

public class SysTemplateUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysTemplateUtil.class);

    public static List<SysTemplateDto> getTemplateByType(String type, String sysOwner) {
        LOGGER.info("begin to get template data,{type:{},sysOwner{}}", type, sysOwner);
        List<SysTemplateDto> list = new ArrayList<>();
        try {
            // 创建http请求token
            CookieStore cookie = HttpClientUtil.createCookieStore(CommonConstants.COOKIE_TOKEN,
                            HttpServletUtil.getCookieValueByName(CommonConstants.COOKIE_TOKEN), new URL(CommonConstants.COMMON_SERVER_ADDR).getHost(),
                            "/");
            // 获取化验数据
            Map<String, String> params = new HashMap<String, String>();
            params.put("type", type);
            params.put("sysOwner", sysOwner);
            HttpClientResultUtil result = HttpClientUtil.post(CommonConstants.COMMON_SERVER_ADDR + "/system/template/selectByType.shtml", params,
                            cookie);
            if (StringUtil.isNotBlank(result.getContext())) {
                JsonUtil util = JsonUtil.AllJsonUtil();
                JsonNode jsonNodes = util.treeFromJson(result.getContext());
                JsonNode datas = jsonNodes.get("list");
                if (datas != null) {
                    list = util.fromJson(datas.toString(), new TypeReference<ArrayList<SysTemplateDto>>() {
                    });
                }
            }
        } catch (Exception e) {
            LOGGER.error("get template data failed,case by：", e);
        }
        return list;
    }
}
