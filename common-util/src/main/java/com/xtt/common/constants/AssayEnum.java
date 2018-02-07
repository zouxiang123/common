/**   
 * @Title: AssayEnum.java 
 * @Package com.xtt.common.api.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年3月21日 上午8:55:45 
 *
 */
package com.xtt.common.constants;

public enum AssayEnum {
    // 血常规
    WBC("白细胞", "WBC"), HB("血红蛋白", "HB"), PLT("血小板", "PLT"), HCT("红细胞比容", "HCT"), CRP("C反应蛋白", "CRP"),
    // 骨矿物质代谢
    CA("钙", "CA"), P("磷", "P"), IPTH("甲状旁腺激素", "IPTH"), VITD("25羟基维生素D", "VITD"),
    // 铁代谢
    FE("铁", "FE"), TIBC("总铁结合力", "TIBC"), SF("铁蛋白", "SF"),
    // 生化检查
    K("钾", "K"), NA("钠", "NA"), MG("镁", "MG"), CL("氯", "CL"), BUN("尿素", "BUN"), BUN_POST("尿素_透后", "BUN_POST")
    //
    , CR("肌酐", "CR"), CR_POST("肌酐_透后", "CR_POST"), UA("尿酸", "UA"), CB("半胱氨酸蛋白酶", "CB"),
    //
    TBIL("总胆红素", "TBIL"), TP("总蛋白", "TP"), ALB("白蛋白", "ALB"), PA("前白蛋白", "PA"), AST("谷草转氨酶", "AST"), ALT("谷丙转氨酶", "ALT"), TCH("胆固醇", "TCH"),
    //
    TG("甘油三酯", "TG"), LDL("低密度脂蛋白胆固醇", "LDL"), HDL("高密度脂蛋白胆固醇", "HDL"), APOA("载脂蛋白A", "APOA"), APOB("载脂蛋白B", "APOB"),
    //
    FBG("空腹血糖", "FBG"), HBA1C("糖化血红蛋白", "HBA1C"), GA("糖化白蛋白", "GA"), HCO3("HCO3", "HCO3"), CO2CP("二氧化碳结合力", "CO2CP"), AKP("碱性磷酸酶", "AKP"),
    //
    HCY("同型半胱氨酸", "HCY"), UIBC("不饱和铁结合力", "UIBC"),
    // 乙肝病毒
    HBEAG("乙肝E抗原", "HBEAG"), HBSAG("乙肝表面抗原", "HBSAG"), ANTIHBC("AntiHBc", "ANTIHBC"), ANTIHBS("AntiHBs", "ANTIHBS"), ANTIHBE("AntiHBe",
                    "ANTIHBE"), HBVDNA("HBV-DNA", "HBVDNA"),
    // 传染病
    HCV("丙肝抗体", "HCV"), HCVRNA("HCV-RNA", "HCVRNA"), HIV("HIV抗体", "HIV"), HSV("梅毒", "HSV"),
    // 痰结核菌
    TBIGM("结核分枝杆菌IgM", "TBIGM"), TBIGG("结核分枝杆菌IgG", "TBIGG"),
    // 耐甲氧西林金黄色葡萄球菌
    MRSA("耐甲氧西林金黄色葡萄球菌", "MRSA"),
    // 其他
    CTNT("心肌肌钙蛋白T", "CTNT"), NTPROBNP("脑钠肽", "NTPROBNP"),
    // 检验科免疫
    BNP("B型钠尿肽", "BNP"), B2("微球蛋白", "B2"), VB12("维生素B12", "VB12"), FOL("叶酸", "FOL"), TPPA("梅毒确诊试验", "TPPA"),
    // -----
    CPS("C肽", "CPS"), INS("胰岛素", "INS"), HSCRP("超敏C反应蛋白", "HSCRP"), NEUT_NO("中性粒细胞数", "NEUT_NO"),
    // -----
    NEUT_PCT("中性粒细胞百分比", "NEUT_PCT"), EO_NO("嗜酸细胞数", "EO_NO"), EO_PCT("嗜酸细胞百分比", "EO_PCT"), CO2("二氧化碳", "CO2"),
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
