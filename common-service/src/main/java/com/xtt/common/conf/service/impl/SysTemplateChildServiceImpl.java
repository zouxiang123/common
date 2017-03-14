/**   
 * @Title: SysTemplateChildServiceImpl.java 
 * @Package com.xtt.common.system.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年5月31日 上午11:31:18 
 *
 */
package com.xtt.common.conf.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.conf.service.ISysTemplateChildService;
import com.xtt.common.dao.mapper.SysTemplateChildMapper;
import com.xtt.common.dao.model.SysTemplateChild;
import com.xtt.common.dao.po.SysTemplateChildPO;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

@Service
public class SysTemplateChildServiceImpl implements ISysTemplateChildService {
    @Autowired
    private SysTemplateChildMapper sysTemplateChildMapper;

    @Override
    public List<SysTemplateChildPO> selectByCondition(SysTemplateChildPO record) {
        return sysTemplateChildMapper.selectByCondition(record);
    }

    @Override
    public int saveSysTemplateChild(Long fkSysTemplateId, List<SysTemplateChild> records) {

        sysTemplateChildMapper.deleteBySysTemplateId(fkSysTemplateId);

        // 插入子表数据
        int total = 0;
        if (records != null) {
            for (SysTemplateChild record : records) {
                if (StringUtils.isBlank(record.getContent()))
                    continue;
                DataUtil.setSystemFieldValue(record);
                record.setFkTenantId(UserUtil.getTenantId());
                total += sysTemplateChildMapper.insert(record);
            }
        }
        return total;
    }

}
