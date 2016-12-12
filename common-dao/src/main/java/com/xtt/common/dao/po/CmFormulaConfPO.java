/**   
 * @Title: CmFormulaConfPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年12月9日 下午5:23:57 
 *
 */
package com.xtt.common.dao.po;

import java.util.List;

import com.xtt.common.dao.model.CmFormulaConf;

public class CmFormulaConfPO extends CmFormulaConf {
	private String[] sysOwners;

	private List<CmFormulaConfPO> children;

	public String[] getSysOwners() {
		return sysOwners;
	}

	public void setSysOwners(String[] sysOwners) {
		this.sysOwners = sysOwners;
	}

	public List<CmFormulaConfPO> getChildren() {
		return children;
	}

	public void setChildren(List<CmFormulaConfPO> children) {
		this.children = children;
	}

}
