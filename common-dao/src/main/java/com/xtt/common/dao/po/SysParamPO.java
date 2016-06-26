/**   
 * @Title: SysParamPO.java 
 * @Package com.lt.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年1月4日 下午5:38:52 
 *
 */
package com.xtt.common.dao.po;

import java.util.List;

import com.xtt.common.dao.model.SysParam;

public class SysParamPO extends SysParam {
	private List<Object> dicUnitList;// 单位对应的字典表数据

	public List<Object> getDicUnitList() {
		return dicUnitList;
	}

	public void setDicUnitList(List<Object> dicUnitList) {
		this.dicUnitList = dicUnitList;
	}

}
