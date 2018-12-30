/**   
 * @Title: CmDictIcdServiceImpl.java 
 * @Package com.xtt.common.conf.service.impl
 * Copyright: Copyright (c) 2015
 * @author: zx   
 * @date: 2018年9月29日 上午10:01:35 
 *
 */
package com.xtt.common.conf.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.conf.service.ICmDictIcdService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.CmDiagnosisIcdMapper;
import com.xtt.common.dao.mapper.CmDictIcdMapper;
import com.xtt.common.dao.model.CmDiagnosisIcd;
import com.xtt.common.dao.model.CmDictIcd;
import com.xtt.common.dao.po.CmDiagnosisEntityPO;
import com.xtt.common.dao.po.CmDiagnosisIcdPO;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

@Service
public class CmDictIcdServiceImpl implements ICmDictIcdService {
    @Autowired
    private CmDictIcdMapper cmDictIcdMapper;
    @Autowired
    private CmDiagnosisIcdMapper cmDiagnosisIcdMapper;

    /**
     * 根据icd名称查询
     */
    public List<CmDictIcd> listByItemName(CmDictIcd cmDictIcd) {
        cmDictIcd.setFkTenantId(UserUtil.getTenantId());
        return cmDictIcdMapper.listByItemName(cmDictIcd);
    }

    /**
     * 保存患者的icd
     */
    public void save(CmDiagnosisEntityPO diagnosisEntity, HashMap<String, Object> map) {
        CmDiagnosisIcd record = new CmDiagnosisIcd();
        if (diagnosisEntity.getIcdId() != null) {
            record = cmDiagnosisIcdMapper.selectByPrimaryKey(diagnosisEntity.getIcdId());
            if (record != null) {
                record.setFkDictIcdId(StringUtils.join(diagnosisEntity.getFkDictIcdId(), ","));
                DataUtil.setSystemFieldValue(record);
                cmDiagnosisIcdMapper.updateByPrimaryKeySelective(record);
            }
        } else {
            record.setFkPatientId(diagnosisEntity.getFkPatientId());
            record.setFkTenantId(UserUtil.getTenantId());
            record.setFkDictIcdId(StringUtils.join(diagnosisEntity.getFkDictIcdId(), ","));
            DataUtil.setSystemFieldValue(record);
            cmDiagnosisIcdMapper.insert(record);
        }

        map.put("message", "保存成功！");
        map.put("status", CommonConstants.SUCCESS);
    }

    /**
     * 根据患者id查询icd
     */
    public List<CmDiagnosisIcdPO> listByfkPatientId(Long fkPatientId) {
        return init(cmDiagnosisIcdMapper.listByfkPatientId(fkPatientId));
    }

    /**
     * 初始化数据
     * 
     * @Title: init
     * @param list
     * @return
     *
     */
    public List<CmDiagnosisIcdPO> init(List<CmDiagnosisIcdPO> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(p -> {
                String icdIds = p.getFkDictIcdId();
                if (StringUtils.isNotBlank(icdIds)) {
                    String itemNames = "";
                    String[] ids = icdIds.split(",");
                    for (int i = 0; i < ids.length; i++) {
                        CmDictIcd icd = cmDictIcdMapper.selectByPrimaryKey(Long.valueOf(ids[i]));
                        itemNames += icd.getItemName() + ';';
                    }
                    p.setItemName(itemNames);
                }

            });
        }
        return list;

    }

    /**
     * 根据id查询icd字典
     */
    public List<CmDictIcd> getById(CmDiagnosisIcdPO record) {
        List<CmDictIcd> list = new ArrayList<CmDictIcd>();
        CmDiagnosisIcd cmDiagnosisIcd = cmDiagnosisIcdMapper.selectByPrimaryKey(record.getId());
        if (cmDiagnosisIcd != null) {
            String dictIcdId = cmDiagnosisIcd.getFkDictIcdId();
            if (StringUtils.isNotBlank(dictIcdId)) {
                String[] icdIds = dictIcdId.split(",");
                for (int i = 0; i < icdIds.length; i++) {
                    CmDictIcd icd = cmDictIcdMapper.selectByPrimaryKey(Long.valueOf(icdIds[i]));
                    list.add(icd);
                }
            }
        }

        return list;
    }

    /**
     * 根据id删除
     */
    public int deleteByPrimaryKey(CmDiagnosisIcdPO record) {
        return cmDiagnosisIcdMapper.deleteByPrimaryKey(record.getId());
    }

}
