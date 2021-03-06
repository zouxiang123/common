/**   
 * @Title: Constants.java 
 * @Package com.xtt.common.common.util
 * Copyright: Copyright (c) 2015
 * @author: Tik   
 * @date: 2015年9月15日 下午3:14:57 
 *
 */
package com.xtt.common.constants;

import java.util.Properties;

import com.xtt.platform.util.config.PropertiesUtil;
import com.xtt.platform.util.security.MD5Util;

/**
 * @ClassName: Constants
 * @date: 2015年9月15日 下午3:14:57
 * @version: V1.0
 * 
 */
public class CommonConstants {
    public static final int MAX_FILE_SIZE = 6291456;// 最大文件大小6M

    /** 系统用户ID（JOB、硬件接口、API接口用） */
    public static final Long SYSTEM_USER_ID = 1L;
    public static final String SYSTEM_USER_NAME = "系统用户";

    public static final String DEFAULT_PASSWORD = MD5Util.md5("123456");// 默认密码

    public static final String FAILURE = "0";// 失败
    public static final String SUCCESS = "1";// 成功
    public static final String WARNING = "2";// 警告
    public static final String INVALIDATE_TOKEN = "3";// 无效的token
    public static final String REPETITION = "4";// 重复

    public static final String SYS_LOG_TYPE_1 = "1";// 流程操作
    public static final String SYS_LOG_TYPE_2 = "2";// 登入、登出、个人资料修改
    public static final String SYS_LOG_TYPE_3 = "3";// 诊断
    public static final String SYS_LOG_TYPE_4 = "4";// 其它

    //public static final String BASE_PATH = System.getProperty("os.name").toLowerCase().startsWith("win") ? "c:/xtt" : "/home/publish/xtt";
    
    public static final String BASE_PATH = System.getProperty("os.name").toLowerCase().startsWith("win") ? "c:/cdaims" : "/home/publish/cdaims";
    public static final String ANNOUNCEMENT_FILE_PATH = "announcement";
    public static final String IMAGE_FILE_PATH = "images";
    public static final String IMAGE_FILE_PATH_PATIENT = "patient";
    public static final String IMAGE_FILE_PATH_PATIENT_BARCODE = "patient/barcode";
    public static final String USER_IMAGE_FILE_PATH = "user";
    public static final String QC_FILE_PATH = "quality_control";
    public static final String EXCEL_TEMPLATE_FILE_PATH = "excel_template";
    public static final String COMPLICATION = "complication";
    public static final String IMAGE_FILE_PATH_DIALYSIS_MACHINE = "dialysis_machine";
    public static final String USER_AUTOGRAPH_FILE_PATH = "autograph"; // 用户签名

    public static final String STATUS = "status";
    public static final String ERROR_MESSAGE = "errmsg";// 血透错误提示信息
    public static final String COOKIE_TOKEN = "cookie_token";

    public static final String API_STATUS = "status";
    public static final String API_TOKEN = "token";
    public static final String API_ERROR_CODE = "errcode";
    public static final String API_ERROR_MESSAGE = "errmsg";
    public static final String API_SUCCESS = "1";

    public static final String LOGIN_USER = "login_user";
    public static final String USER_PERMISSION = "user_permission";// 有权限的菜单集合
    public static final String USER_NON_PERMISSION = "user_non_permission";// 没有权限的菜单集合
    public static final String API_PERMISSION = "api_permission";// api有权限集合

    public static final String ROLE_ADMIN = "1";
    public static final String ROLE_DOCTOR = "2";
    public static final String ROLE_NURSE = "3";
    public static final String ROLE_ENGINEER = "4";
    public static final String ROLE_OTHER = "10";
    public static final int ROLE_CONSTANT_ADMIN = 11;
    /** 主任 */
    public static final int ROLE_CONSTANT_DOCTOR_DIRECTOR = 21;
    /** 医生 */
    public static final int ROLE_CONSTANT_DOCTOR = 22;
    /** 护士长 */
    public static final int ROLE_CONSTANT_HEAD_NURSE = 31;
    /** 护士 */
    public static final int ROLE_CONSTANT_NURSE = 32;

    public static final String DATE_TYPE_YEAR = "y";
    public static final String DATE_TYPE_MONTH = "m";
    public static final String DATE_TYPE_WEEK = "w";
    public static final String DATE_TYPE_QUARTER = "q";

    /** 操作 ：add：新增 delete：删除 */
    public static final String OPERATION_ADD = "add";
    public static final String OPERATION_SELECT = "select";

    /** 项目名称 */
    public static final String SYS_OWNER = "sysOwner";
    /** 公用系统 */
    public static final String SYS_CM = "CM";
    /** 腹透系统 */
    public static final String SYS_PD = "PD";
    /** 血透系统 */
    public static final String SYS_HD = "HD";
    /** 通路手术系统 */
    public static final String SYS_POPS = "POPS";
    /** 慢病系统 */
    public static final String SYS_CKD = "CKD";
    // -----------------------------肾病类型--------------------------------------------
    /** 肾病类型：慢性肾功能衰竭 */
    public static final String NEPHROSIS_TYPE_CRF = "1";
    /** 肾病类型：慢性肾功能不全急性加重 */
    public static final String NEPHROSIS_TYPE_SERIOUS_CRF = "2";
    /** 肾病类型：急性肾功能衰竭 */
    public static final String NEPHROSIS_TYPE_ARF = "3";
    /** 门诊 */
    public static final String PATIENT_OUTPATIENT = "1";
    /** 住院 */
    public static final String PATIENT_HOSPITALIZATION = "2";

    /*-------------- common kafka topic list start -------------- */
    public static final String TOPIC_SYS_LOG = "sysLog";
    /*-------------- common kafka topic list end -------------- */
    /*-------------- common patient kpi type start -------------- */
    /** 患者关键指标--干体重 */
    public static final String PATIENT_KPI_TYPE_DRY_WEIGHT = "dryWeight";
    /*-------------- common patient kpi type end -------------- */
    /*-------------- common patient medicare card type start -------------- */
    /** 患者卡号--住院号 */
    public static final String PATIENT_MEDICARE_CARD_TYPE_HOSPITAL = "01";
    /** 患者卡号--门诊号 */
    public static final String PATIENT_MEDICARE_CARD_TYPE_OUTPATIENT = "02";
    /** 患者卡号--HISId */
    public static final String PATIENT_MEDICARE_CARD_TYPE_HISID = "03";
    /** 患者卡号--城镇居民医保卡 */
    public static final String PATIENT_MEDICARE_CARD_TYPE_TOWNER_MEDICARE_CARD = "04";
    /** 患者卡号--城镇职工医保卡 */
    public static final String PATIENT_MEDICARE_CARD_TYPE_URBAN_WORKERS_MEDICARE_CARD = "05";
    /** 患者卡号--农村医保卡 */
    public static final String PATIENT_MEDICARE_CARD_TYPE_COUNTRY_MEDICARE_CARD = "06";
    /*-------------- common patient medicare card type end -------------- */

    /** dialysis machine number */
    public static final String BED_NUM = "bed_num";
    /** dialysis machine online_num */
    public static final String ONLINE_NUM = "online_num";
    /**
     * 用户默认皮肤
     */
    public static final String USER_SKIN_DEFAULT = "black";

    /** --------------- 路径相关信息 ------------- */
    public static String BASE_URL = "";
    /** 公用服务地址 */
    public static String COMMON_SERVER_ADDR = "";
    /** 随访服务地址 */
    public static final String FU_URL_KEY = "fu_addr";
    public static String FU_URL = "";
    /** 腹透服务地址 */
    public static final String PD_URL_KEY = "pd_addr";
    public static String PD_URL = "";
    /** 通路手术系统地址 */
    public static final String POPS_URL_KEY = "pops_addr";
    public static String POPS_URL = "";

    /**
     * 电子病历插件url地址
     */
    public static String ASP_PATH = "";

    /**
     * 推送模块患者信息推送标识
     */
    public static String APP_PUSH_PATIENT = "app_push_patient";
    /**
     * 推送模块患者信息推送身份证不足6位默认为：123456
     */
    public static String APP_PUSH_PATIENT_PWD = "123456";

    /**
     * 初始化患者信息
     */
    public static String APP_PUSH_PATIENT_INIT = "1";
    /**
     * 转归类型：3死亡
     */
    public static String PATIENT_OUTCOME_TYPE_DEAD = "3";

    /** 字典唯一后缀常量 */
    public static final String DICT_UK_SUFFIX = "_xttsuffix";
    public static final Integer REPORT_EX_STATUS_INIT = 0;
    /**
     * 异常提醒_异常_检验结果
     */
    public static final Integer REPORT_EX_ASSAY = 2;

    /**
     * 初始化请求路径相关参数
     * 
     * @param configPath
     * 
     * @Title: initUrlParam
     *
     */
    public static void initUrlParam(String configPath) {
        Properties config = PropertiesUtil.loadProperties(configPath, "UTF-8");
        String BASE_URL_SOURCE = config.getProperty("base.url");
        BASE_URL = BASE_URL_SOURCE.concat(BASE_URL_SOURCE.endsWith("/") ? "" : "/");
        COMMON_SERVER_ADDR = BASE_URL.concat("common/");
        FU_URL = BASE_URL.concat("fu/");
        PD_URL = BASE_URL.concat("pd/");
        POPS_URL = BASE_URL.concat("pops/");
        // 电子病历插件url地址
        ASP_PATH = config.getProperty("asp.url");
    }
}
