/**   
 * @Title: CmDictPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年9月23日 下午4:23:06 
 *
 */
package com.xtt.common.dao.po;

import com.xtt.common.dao.model.CmDict;

public class CmDictPO extends CmDict {
	private Boolean isChecked = false;// 是否选中

	public Boolean getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}

}
