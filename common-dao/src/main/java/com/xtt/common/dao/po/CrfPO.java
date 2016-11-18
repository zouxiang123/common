package com.xtt.common.dao.po;

import com.xtt.common.dao.model.Crf;

public class CrfPO extends Crf {

	private Long fkDialysisCampaignId;// 透析活动ID
	// 主表临床诊断的版本号
	private Integer parentVersion;

	private String userName;
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getFkDialysisCampaignId() {
		return fkDialysisCampaignId;
	}

	public void setFkDialysisCampaignId(Long fkDialysisCampaignId) {
		this.fkDialysisCampaignId = fkDialysisCampaignId;
	}

	public Integer getParentVersion() {
		return parentVersion;
	}

	public void setParentVersion(Integer parentVersion) {
		this.parentVersion = parentVersion;
	}

}
