/**   
 * @Title: SYSParamController.java 
 * @Package com.xtt.txgl.system.controller
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年12月29日 下午2:03:44 
 *
 */
package com.xtt.common.conf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.common.service.ISysDbSourceService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.SysDbSource;
import com.xtt.common.dao.po.QueryPO;
import com.xtt.common.dao.po.SysDbSourcePO;
import com.xtt.platform.util.Constants;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateFormatUtil;
import com.xtt.platform.util.time.IDateConst;

@Controller
@RequestMapping("/system/sysDbSource/")
public class SysDbSourceController {
    @Autowired
    private ISysDbSourceService sysDbSourceService;

    @RequestMapping("dbDown")
    public ModelAndView dbDown() {
        ModelAndView model = new ModelAndView("system/db_down");
        return model;
    }

    /**
     * 跳转到数据源维护页面
     * 
     * @return
     */
    @RequestMapping("sysDbSource")
    public ModelAndView dictTreeList() {
        ModelAndView model = new ModelAndView("system/sys_db_source");
        return model;
    }

    @RequestMapping("buildSysDbSource")
    @ResponseBody
    public List<SysDbSourcePO> buildDictTreeList(SysDbSourcePO po) {
        // record.setPid(1L);
        // po.setIsOrder(true);
        // po.setIsOrderBy(false);
        List<SysDbSourcePO> dictionaryCategory = sysDbSourceService.selectBySysDbSourcePO(po);

        return dictionaryCategory;
    }

    /**
     * 更新字典表
     * 
     * @Title: updateDict
     * @return
     */
    @RequestMapping("updateSysDbSource")
    @ResponseBody
    public Map<String, Object> updateDict(SysDbSource db) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(CommonConstants.STATUS, sysDbSourceService.updateByPrimaryKey(db));
        return map;
    }

    /**
     * 手动下载第三方数据
     * 
     * @Title: updateDict
     * @return
     */
    @RequestMapping("downDB")
    @ResponseBody
    public Map<String, Object> downDB(QueryPO query) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 如果日期为空，默认同步7天的数据
        String startDate = query.getStartDate();
        String endDate = query.getEndDate();
        if (StringUtil.isEmpty(startDate)) {
            String startDateStr = DateFormatUtil.beforeMonthDate(0, 30, 0, IDateConst.DATE_FORMAT);
            query.setStartDate(startDateStr);
        }
        if (StringUtil.isEmpty(endDate)) {
            String currentDateStr = DateFormatUtil.getCurrentDateStr(IDateConst.DATE_FORMAT);
            query.setEndDate(currentDateStr);
        }
        String retMsg = sysDbSourceService.downDB(query);

        map.put("retMsg", retMsg);
        map.put(CommonConstants.STATUS, Constants.SUCCESS);
        return map;
    }

}
