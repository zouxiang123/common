/**   
 * @Title: CmDiagnosisHistEntityServiceImpl.java 
 * @Package com.xtt.common.diagnosis.service.impl
 * Copyright: Copyright (c) 2015
 * @author: admin   
 * @date: 2016年12月5日 上午10:21:42 
 *
 */
package com.xtt.common.diagnosis.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.CmDiagnosisEntityMapper;
import com.xtt.common.dao.mapper.CmDiagnosisEntityValueMapper;
import com.xtt.common.dao.model.CmDiagnosisEntity;
import com.xtt.common.dao.po.CmDiagnosisEntityPO;
import com.xtt.common.dao.po.CmDiagnosisEntityValuePO;
import com.xtt.common.dao.po.CmDictDiagnosisPO;
import com.xtt.common.diagnosis.service.ICmDiagnosisHistEntityService;
import com.xtt.common.diagnosis.service.IDictDiagnosisService;
import com.xtt.common.diagnosis.util.DiagnosisUtil;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.BeanUtil;
import com.xtt.platform.util.lang.StringUtil;

@Service
public class CmDiagnosisHistEntityServiceImpl implements ICmDiagnosisHistEntityService {

    @Autowired
    private CmDiagnosisEntityMapper cmDiagnosisEntityMapper;
    @Autowired
    private CmDiagnosisEntityValueMapper cmDiagnosisEntityValueMapper;
    @Autowired
    private IDictDiagnosisService dictDiagnosisService;

    @Override
    public CmDiagnosisEntityPO selectById(Long id) {
        return cmDiagnosisEntityMapper.getById(id);
    }

    @Override
    public List<CmDiagnosisEntityPO> selectEntitiesByPatient(CmDiagnosisEntityPO entity) {
        if (entity.getFkTenantId() == null) {
            entity.setGroupTenant(UserUtil.getGroupTenant());
        }
        return cmDiagnosisEntityMapper.selectEntitiesByPatient(entity);
    }

    @Override
    public String saveItem(CmDiagnosisEntityPO record) {
        if (record.getId() == null) {
            record.setFkTenantId(UserUtil.getTenantId());
            DataUtil.setSystemFieldValue(record);
            cmDiagnosisEntityMapper.insertSelective(record);
            // 添加成功，则批量添加Value集合
            if (record.getId() != null) {
                for (CmDiagnosisEntityValuePO value : record.getValueList()) {
                    value.setFkEntityId(record.getId());
                }
            }
            cmDiagnosisEntityValueMapper.insertValueBatch(record.getValueList());
        } else {
            record.setUpdateUserId(UserUtil.getLoginUserId());
            record.setUpdateTime(new Date());
            cmDiagnosisEntityMapper.updateByPrimaryKeySelective(record);
            // 先删除Value集合
            cmDiagnosisEntityValueMapper.deleteByEntity(record.getId());
            // 后添加Value集合
            cmDiagnosisEntityValueMapper.insertValueBatch(record.getValueList());
        }
        return CommonConstants.SUCCESS;
    }

    @Override
    public String deleteById(Long id) {
        CmDiagnosisEntity item = cmDiagnosisEntityMapper.selectByPrimaryKey(id);
        if (item != null) {
            int count = cmDiagnosisEntityMapper.deleteByPrimaryKey(item.getId());
            // 如果Entity删除成功，则执行删除Value集合
            if (count > 0) {
                cmDiagnosisEntityValueMapper.deleteByEntity(id);
            }
            return CommonConstants.SUCCESS;
        } else {
            return CommonConstants.FAILURE;
        }
    }

    @Override
    public Map<Long, Map<String, String>> getLatestStrByPatientIds(Collection<Long> patientIds, Collection<String> types, Integer tenantId) {
        Map<Long, Map<String, String>> map = null;
        if (CollectionUtils.isEmpty(patientIds) || CollectionUtils.isEmpty(types)) {
            return map;
        }
        CmDiagnosisEntityPO query = new CmDiagnosisEntityPO();
        query.setPatientIds(patientIds);
        query.setFkTenantId(tenantId);
        query.setItemCodes(types);
        List<CmDiagnosisEntityPO> list = selectEntitiesByPatient(query);
        if (CollectionUtils.isNotEmpty(list)) {// 查询结果是根据创建时间倒序排列，患者同类别的第一条即为最新的
            map = new HashMap<>();
            List<CmDictDiagnosisPO> dicts = dictDiagnosisService.selectAll();
            // 转换为map
            Map<String, CmDictDiagnosisPO> dictMap = new HashMap<>(dicts.size());
            dicts.forEach(dict -> {
                dictMap.put(dict.getItemCode(), dict);
            });
            for (CmDiagnosisEntityPO entity : list) {
                if (CollectionUtils.isNotEmpty(entity.getValueList())) {
                    Map<String, String> typesMap = map.get(entity.getFkPatientId());
                    if (typesMap == null) {
                        typesMap = new HashMap<>();
                        map.put(entity.getFkPatientId(), typesMap);
                    }
                    if (typesMap.containsKey(entity.getItemCode())) {// 同一个患者同一种诊断只需要一条
                        continue;
                    }
                    // 需要显示的字典
                    List<CmDictDiagnosisPO> usedDictList = new ArrayList<>();
                    // 去除重复数据用
                    Set<String> existsCode = new HashSet<>();
                    // 有输入内容itemCode
                    Map<String, String> hasContentValueMap = new HashMap<>(entity.getValueList().size());
                    // 顶级显示字典
                    List<CmDictDiagnosisPO> topList = new ArrayList<>();
                    for (CmDiagnosisEntityValuePO val : entity.getValueList()) {
                        List<CmDictDiagnosisPO> treeList = DiagnosisUtil.getTreeListByLeafCode(dictMap, val.getItemCode(), entity.getItemCode());
                        if (CollectionUtils.isNotEmpty(treeList)) {
                            CmDictDiagnosisPO useItem;
                            for (int i = 0; i < treeList.size(); i++) {
                                CmDictDiagnosisPO dictItem = treeList.get(i);
                                // 去除重复数据
                                if (!existsCode.contains(dictItem.getItemCode())) {
                                    // 避免后续循环影响原始字典数据，操作的数据使用新的对象
                                    useItem = new CmDictDiagnosisPO();
                                    BeanUtil.copyProperties(dictItem, useItem);
                                    usedDictList.add(useItem);
                                    // 添加顶级节点
                                    if (i == treeList.size() - 1) {
                                        topList.add(useItem);
                                    }
                                    existsCode.add(dictItem.getItemCode());
                                }
                            }
                        }
                        if (StringUtil.isNotBlank(val.getContent())) {
                            hasContentValueMap.put(val.getItemCode(), val.getContent());
                        }
                    }
                    if (topList.size() > 0) {
                        StringBuilder sb = new StringBuilder();
                        for (CmDictDiagnosisPO dict : topList) {
                            DiagnosisUtil.initDictAsTree(usedDictList, dict);
                            sb.append(StringUtil.stripToEmpty(dict.getItemName()).replaceAll("？", ""));
                            if (CollectionUtils.isNotEmpty(dict.getChildrens())) {
                                sb.append("：");
                                appendDiagnosis(sb, dict, hasContentValueMap);
                                sb.deleteCharAt(sb.length() - 1);
                                sb.append("；");
                            }
                        }
                        typesMap.put(entity.getItemCode(), sb.toString());
                    }
                }
            }
        }
        return map;
    }

    /**
     * 拼接诊断数据
     * 
     * @Title: appendDiagnosis
     * @param sb
     * @param dict
     * @param contentMap
     *
     */
    private void appendDiagnosis(StringBuilder sb, CmDictDiagnosisPO dict, Map<String, String> contentMap) {
        if (CollectionUtils.isNotEmpty(dict.getChildrens())) {
            int i = 0;
            for (CmDictDiagnosisPO item : dict.getChildrens()) {
                sb.append(item.getItemName());
                if (CollectionUtils.isNotEmpty(item.getChildrens())) {
                    sb.append(">");
                    appendDiagnosis(sb, item, contentMap);
                } else {
                    if (contentMap.get(item.getItemCode()) != null) {
                        sb.append(" ").append(contentMap.get(item.getItemCode()));
                    }
                    if (++i == dict.getChildrens().size()) {
                        sb.append("；");
                    } else {
                        sb.append(",");
                    }
                }
            }
        }
    }

}