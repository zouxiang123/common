/**   
 * @Title: SYSParamController.java 
 * @Package com.xtt.common.system.controller
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年12月29日 下午2:03:44 
 *
 */
package com.xtt.common.conf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtt.common.common.service.ICommonCacheService;
import com.xtt.common.common.service.ISysParamService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.SysParam;
import com.xtt.common.dao.po.SysParamPO;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.UserUtil;

@Controller
@RequestMapping("/system/param/")
public class SysParamController {

    @Autowired
    private ISysParamService sysParamService;
    @Autowired
    private ICommonCacheService commonCacheService;

    /** 系统参数值定义页面 */
    @RequestMapping("view")
    public String view(Model model) {
        String sysOwner = UserUtil.getSysOwner();
        SysParamPO record = new SysParamPO();
        record.setSysOwners(new String[] { sysOwner, CommonConstants.SYS_CM });
        record.setFkTenantId(UserUtil.getTenantId());
        List<SysParamPO> list = sysParamService.selectByCondition(record);
        model.addAttribute("paramList", initParamList(list));
        return "cm/conf/param_conf";
    }

    /**
     * 初始化参数列表，移除不能让用户修改的参数值
     * 
     * @return
     */
    private List<SysParamPO> initParamList(List<SysParamPO> list) {
        List<SysParamPO> resultList = new ArrayList<SysParamPO>();
        for (SysParamPO sysOwner : list) {
            if ("version".equals(sysOwner.getParamName()) || "jciConform".equals(sysOwner.getParamName()))
                continue;
            if (StringUtils.isNotBlank(sysOwner.getDicType())) {
                sysOwner.setDicUnitList(DictUtil.listByPItemCode(sysOwner.getDicType(), sysOwner.getParamUnit()));
            }
            resultList.add(sysOwner);
        }
        return resultList;
    }

    /** 保存或者更新系统参数 */
    @RequestMapping("saveParam")
    @ResponseBody
    public Map<String, Object> saveParam(SysParam param) {
        Map<String, Object> map = new HashMap<String, Object>();
        String status = sysParamService.saveParam(param);
        if (CommonConstants.SUCCESS.equals(status)) {
            commonCacheService.cacheSysParam(UserUtil.getTenantId());
        }
        map.put("status", status);
        return map;
    }

}
