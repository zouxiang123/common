/**   
 * @Title: DiagnosisUtil.java 
 * @Package com.xtt.common.diagnosis.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年9月27日 上午10:39:44 
 *
 */
package com.xtt.common.diagnosis.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.xtt.common.dao.po.CmDictDiagnosisPO;

public class DiagnosisUtil {

    /**
     * 根据子节点获取子节点所有的父节点，不包含根节点 <br>
     * 返回结果根据深度倒序 （level3->level2->level1）
     * 
     * @Title: getTreeListByLeafCode
     * @param dictMap
     * @param leafCode
     * @param rootCode
     *            根节点
     * @param list
     * @return
     *
     */
    public static List<CmDictDiagnosisPO> getTreeListByLeafCode(Map<String, CmDictDiagnosisPO> dictMap, String leafCode, String rootCode) {
        return getTreeListByLeafCode(dictMap, leafCode, rootCode, null);
    }

    /**
     * 根据子节点获取子节点所有的父节点，不包含根节点 <br>
     * 返回结果根据深度倒序 （level3->level2->level1）
     * 
     * @Title: getTreeListByLeafCode
     * @param dictMap
     * @param leafCode
     * @param rootCode
     *            根节点
     * @param list
     * @return
     *
     */
    private static List<CmDictDiagnosisPO> getTreeListByLeafCode(Map<String, CmDictDiagnosisPO> dictMap, String leafCode, String rootCode,
                    List<CmDictDiagnosisPO> list) {
        CmDictDiagnosisPO item = dictMap.get(leafCode);
        if (item == null) {
            return list;
        }
        list = list == null ? new ArrayList<>(item.getItemLevel()) : list;
        if (Objects.equals(leafCode, rootCode)) {// 当子节点和根节点是同一个时，返回
            return list;
        }
        list.add(item);
        return getTreeListByLeafCode(dictMap, item.getpItemCode(), rootCode, list);
    }

    /**
     * 将诊断字典封装成tree的方式
     * 
     * @Title: initDictDiagnosisAsTree
     * @param total
     * @param root
     *
     */
    public static void initDictAsTree(Collection<CmDictDiagnosisPO> total, CmDictDiagnosisPO root) {
        initDictAsTree(total, root, null);
    }

    /**
     * 将诊断字典封装成tree的方式
     * 
     * @Title: initDictDiagnosisAsTree
     * @param total
     * @param root
     * @param children
     *
     */
    private static void initDictAsTree(Collection<CmDictDiagnosisPO> total, CmDictDiagnosisPO root, List<CmDictDiagnosisPO> children) {
        if (children == null) {
            children = new ArrayList<>();
        }
        for (CmDictDiagnosisPO item : total) {
            if (Objects.equals(root.getItemCode(), item.getpItemCode())) {
                children.add(item);
                initDictAsTree(total, item, item.getChildrens());
            }
        }
        root.setChildrens(children);
    }
}
