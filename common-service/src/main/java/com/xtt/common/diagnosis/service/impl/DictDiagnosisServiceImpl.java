/**   
 * @Title: DictDiagnosisService.java 
 * @Package com.xtt.pd.system.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年7月3日 下午2:44:54 
 *
 */
package com.xtt.common.diagnosis.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.CmDictDiagnosisMapper;
import com.xtt.common.dao.model.CmDictDiagnosis;
import com.xtt.common.dao.po.CmDictDiagnosisPO;
import com.xtt.common.diagnosis.service.IDictDiagnosisService;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

/**
 * @ClassName: DictDiagnosisService
 * @date: 2016年7月3日 下午2:44:54
 * @version: V1.0
 *
 */
@Service
public class DictDiagnosisServiceImpl implements IDictDiagnosisService {

    @Autowired
    private CmDictDiagnosisMapper cmDictDiagnosisMapper;

    @Override
    public List<CmDictDiagnosisPO> selectAll() {
        return selectByCondition(null);
    }

    @Override
    public List<CmDictDiagnosisPO> selectByCondition(CmDictDiagnosisPO record) {
        if (record == null) {
            record = new CmDictDiagnosisPO();
        }
        return cmDictDiagnosisMapper.selectByCondition(record);
    }

    @Override
    public CmDictDiagnosisPO selectByItemCode(String itemCode) {
        CmDictDiagnosisPO record = new CmDictDiagnosisPO();
        record.setItemCode(itemCode);
        List<CmDictDiagnosisPO> list = selectByCondition(record);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public String saveItem(CmDictDiagnosis record) {
        if (record.getId() == null) {
            if (selectByItemCode(record.getItemCode()) != null) {// 检查编号是否已存在
                return CommonConstants.WARNING;
            }
            DataUtil.setSystemFieldValue(record);
            cmDictDiagnosisMapper.insert(record);
        } else {
            CmDictDiagnosisPO parent = selectByItemCode(record.getpItemCode());
            if (parent.getIsLeaf() == null || parent.getIsLeaf()) {// 如果父节点是叶子节点，更新为非叶子节点
                parent.setIsLeaf(false);
                DataUtil.setSystemFieldValue(parent);
                cmDictDiagnosisMapper.updateByPrimaryKey(parent);
            }
            CmDictDiagnosis old = cmDictDiagnosisMapper.selectByPrimaryKey(record.getId());
            record.setCreateTime(old.getCreateTime());
            record.setCreateUserId(old.getCreateUserId());
            record.setUpdateTime(new Date());
            record.setUpdateUserId(UserUtil.getLoginUserId());
            cmDictDiagnosisMapper.updateByPrimaryKey(record);
        }
        return CommonConstants.SUCCESS;
    }

    @Override
    public String deleteByItemCode(String itemCode) {
        CmDictDiagnosisPO item = selectByItemCode(itemCode);
        if (item != null) {
            cmDictDiagnosisMapper.deleteByPrimaryKey(item.getId());
            return CommonConstants.SUCCESS;
        } else {
            return CommonConstants.FAILURE;
        }
    }

    /**
     * 根据指定父节点获取节点下的树结构，如果获取所有pItemCode参数为null即可
     * 
     * @Title: selectTreeList
     * @param pItemCode
     * @return
     *
     */
    @Override
    public List<CmDictDiagnosisPO> selectTreeList(String pItemCode) {
        // 获取所有tree数据
        CmDictDiagnosisPO dictDiagnosis = new CmDictDiagnosisPO();
        // dictDiagnosis.setIsLeaf(false); // 获取所有有效树字典数据
        List<CmDictDiagnosisPO> trees = this.selectByCondition(dictDiagnosis);
        // 定义一个空的rootTrees集合，存储树结构
        List<CmDictDiagnosisPO> rootTrees = Lists.newArrayList();
        for (CmDictDiagnosisPO tree : trees) {
            if (StringUtils.isNotBlank(pItemCode)) {
                if (StringUtils.isNotBlank(tree.getpItemCode()) && pItemCode.equals(tree.getpItemCode())) {
                    rootTrees.add(tree);
                }
            } else {
                if (StringUtils.isBlank(tree.getpItemCode())) {
                    rootTrees.add(tree);
                }
            }
            for (CmDictDiagnosisPO t : trees) {
                if (StringUtils.isNoneBlank(t.getpItemCode()) && t.getpItemCode().equals(tree.getItemCode())) {
                    if (tree.getChildrens() == null) {
                        List<CmDictDiagnosisPO> myChildrens = new ArrayList<CmDictDiagnosisPO>();
                        myChildrens.add(t);
                        tree.setChildrens(myChildrens);
                        tree.setChildren(myChildrens);
                    } else {
                        tree.getChildrens().add(t);
                    }
                }
            }
        }
        return rootTrees;
    }

    @Override
    public CmDictDiagnosisPO selectPInfo(String itemCode) {
        return cmDictDiagnosisMapper.selectPInfo(itemCode);
    }

}