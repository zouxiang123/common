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
    HB("血红蛋白", "HB"), SF("铁蛋白", "SF"), FE("铁", "FE"), TIBC("总铁结合力", "TIBC"), CA("钙", "CA"), P("磷", "P"),
    // -----
    IPTH("甲状旁腺激素", "IPTH"), K("钾", "K"), BUN("尿素", "BUN"), preBUN("透前尿素", "PRE-BUN"), afterBUN("透后尿素", "AFTER-BUN"),
    // -----
    CR("肌酐", "CR"), BNP("B型钠尿肽", "BNP"), PA("前白蛋白", "PA"), ALB("ALB", "ALB"), B2("微球蛋白", "B2"), VB12("维生素B12", "VB12"), FOL("叶酸", "FOL"),
    // -----
    HCT("红细胞比容", "HCT"), AKP("碱性磷酸酶", "AKP"), TG("甘油三酯", "TG"), TCH("胆固醇", "TCH"), LDL("低密度脂蛋白胆固醇", "LDL"), HDL("高密度脂蛋白胆固醇", "HDL"),
    // -----
    HBA1C("糖化血红蛋白", "HBA1C"), CPS("C肽", "CPS"), INS("胰岛素", "INS"), CRP("C反应蛋白", "CRP"), HSCRP("超敏C-反应蛋白", "HSCRP"), WBC("白细胞",
                    "WBC"), NEUT_NO("中性粒细胞数", "NEUT_NO"),
    // -----
    NEUT_PCT("中性粒细胞百分比", "NEUT_PCT"), EO_NO("嗜酸细胞数", "EO_NO"), EO_PCT("嗜酸细胞%", "EO_PCT"), FBG("空腹血糖", "FBG"), CO2("透前CO2", "CO2"),
    // 传染病指标
    HBEAG("乙肝E抗原", "HBEAG"), HBSAG("乙肝表面抗原", "HBSAG"), HCV("丙肝抗体", "HCV"), HIV("HIV抗体", "HIV"),
    /*------------ PD ----------------*/
    PD_BUN("腹透尿素", "PD_BUN"), PD_CR("腹透肌酐", "PD_CR"),
    // 24小时尿液
    PD_NBUN_24H("24H尿尿素", "PD_NBUN_24H"), PD_NCR_24H("24H尿肌酐", "PD_NCR_24H"),
    // 24小时腹透液
    PD_DBUN_24H("24H腹透液尿素", "PD_DBUN_24H"), PD_DCR_24H("24H腹透液肌酐", "PD_DCR_24H"), PD_DGLUC_24H("24H腹透液葡萄糖浓度", "PD_DGLUC_24H"), PD_DTP_24H("24H腹透液总蛋白",
                    "PD_DTP_24H"),
    // -------------------- pet ----------------
    // 腹透液
    PD_DCR_0H("0H腹透液肌酐", "PD_DCR_0H"), PD_DCR_2H("2H腹透液肌酐", "PD_DCR_2H"), PD_DCR_4H("4H腹透液肌酐", "PD_DCR_4H"), PD_DGLUC_0H("0H腹透液葡萄糖",
                    "PD_DGLUC_0H"), PD_DGLUC_2H("2H腹透液葡萄糖", "PD_DGLUC_2H"), PD_DGLUC_4H("0H腹透液葡萄糖", "PD_DGLUC_4H"),
    // 血清
    PD_CR_2H("2H血清肌酐", "PD_CR_2H"), PD_GLUC_2H("2H血清葡萄糖", "PD_GLUC_2H");

    private String value;
    private String name;

    private AssayEnum(String name, String value) {
        this.setName(name);
        this.setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
