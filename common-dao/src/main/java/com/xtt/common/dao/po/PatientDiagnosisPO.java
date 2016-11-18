/**   
 * @Title: Snippet.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年10月13日 下午12:59:50 
 *
 */
package com.xtt.common.dao.po;

import com.xtt.common.dao.model.PatientDiagnosis;
import com.xtt.platform.util.time.DateFormatUtil;

public class PatientDiagnosisPO extends PatientDiagnosis {

	private String name;
	private String imagePath;
	private String sex;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getUpdateTimeShow() {
		return DateFormatUtil.convertDateToStr(super.getUpdateTime(), "yyyy-MM-dd");
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
