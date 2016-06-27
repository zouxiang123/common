package com.xtt.common.dao.po;

import com.xtt.common.dao.model.PatientCard;

/**
 * 病患多卡号维护
 * 
 * @author zz
 */
public class PatientCardPO extends PatientCard {
	private String cardTypeDesc;

	public String getCardTypeDesc() {
		return cardTypeDesc;
	}

	public void setCardTypeDesc(String cardTypeDesc) {
		this.cardTypeDesc = cardTypeDesc;
	}

}
