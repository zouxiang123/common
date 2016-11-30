/**   
 * @Title: AssayEnum.java 
 * @Package com.xtt.txgl.api.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年3月21日 上午8:55:45 
 *
 */
package com.xtt.common.constants;

public enum AssayEnum {
	/** 血红蛋白 */
	HB("HB"),
	/** 铁蛋白 */
	SF("SF"),
	/** 铁 */
	FE("FE"),
	/** 总铁结合力 */
	TIBC("TIBC"),
	/** 钙 */
	CA("CA"),
	/** 磷 */
	P("P"),
	/** 甲状旁腺激素 */
	IPTH("IPTH"),
	/** 钾 */
	K("K"),
	/** 尿素 */
	BUN("BUN"),
	/** 透前尿素 */
	preBUN("PRE-BUN"),
	/** 透后尿素 */
	afterBUN("AFTER-BUN"),
	/** 肌酐 */
	CR("CR"), // 用CREA-G还是用CREA
	/** B型钠尿肽 */
	BNP("BNP"),
	/** 前白蛋白 */
	PA("PA"),
	/** 白蛋白 */
	ALB("ALB"),
	/** 微球蛋白 */
	B2("B2"),
	/** 维生素B12 */
	VB12("VB12"),
	/** 叶酸 */
	FOL("FOL"),
	/** 红细胞比容 */
	HCT("HCT"),
	/** 碱性磷酸酶 */
	AKP("AKP"),
	/** 甘油三酯 */
	TG("TG"),
	/** 胆固醇 */
	TCH("TCH"),
	/** 低密度脂蛋白胆固醇 */
	LDL("LDL"),
	/** 高密度脂蛋白胆固醇 */
	HDL("HDL"),
	/** 糖化血红蛋白 */
	HBA1C("HBA1C"),
	/** C肽 */
	CPS("CPS"),
	/** 胰岛素 */
	INS("INS"),
	/** C反应蛋白 */
	CRP("CRP"),
	/** 超敏C-反应蛋白 */
	HSCRP("HSCRP"),
	/** 白细胞 */
	WBC("WBC"),
	/** 中性粒细胞数 */
	NEUT_NO("NEUT_NO"),
	/** 中性粒细胞百分比 */
	NEUT_PCT("NEUT_PCT"),
	/** 嗜酸细胞数 */
	EO_NO("EO_NO"),
	/** 嗜酸细胞% */
	EO_PCT("EO_PCT"),
	/** 空腹血糖 */
	FBG("FBG"),
	/** 透前CO2 */
	CO2("CO2"),
	/** 乙肝E抗原 */
	HBEAG("HBEAG"),
	/** 乙肝表面抗原 */
	HBSAG("HBSAG"),
	/** 丙肝抗体 */
	HCV("HCV"),
	/** HIV抗体 */
	HIV("HIV");

	private String value;

	private AssayEnum(String value) {
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static AssayEnum getEnum(String value) {
		for (AssayEnum e : AssayEnum.values()) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}
}
