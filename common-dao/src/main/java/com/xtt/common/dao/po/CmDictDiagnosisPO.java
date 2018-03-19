/**   
 * @Title: CmDictDiagnosisPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年7月6日 下午8:04:36 
 *
 */
package com.xtt.common.dao.po;

import java.util.List;

import com.xtt.common.dao.model.CmDictDiagnosis;

public class CmDictDiagnosisPO extends CmDictDiagnosis {

    /**
     * 用于前端展示诊断树结构建立 ，在后台将CmDictDiagnosisPO构建成树的机构返回给前端直接遍历使用。
     */
    private List<CmDictDiagnosisPO> childrens;
    private List<CmDictDiagnosisPO> children;

    /**
     * 父节点的itemCode
     */
    private String pItemCode;
    /**
     * 父节点的itemName
     */
    private String pItemName;

    public List<CmDictDiagnosisPO> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<CmDictDiagnosisPO> childrens) {
        this.childrens = childrens;
    }

    public List<CmDictDiagnosisPO> getChildren() {
        return children;
    }

    public void setChildren(List<CmDictDiagnosisPO> children) {
        this.children = children;
    }

    public String getpItemCode() {
        return pItemCode;
    }

    public void setpItemCode(String pItemCode) {
        this.pItemCode = pItemCode;
    }

    public String getpItemName() {
        return pItemName;
    }

    public void setpItemName(String pItemName) {
        this.pItemName = pItemName;
    }

}
