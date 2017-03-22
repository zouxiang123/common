/**   
 * @Title: Constants.java 
 * @Package com.xtt.common.common.util
 * Copyright: Copyright (c) 2015
 * @author: Tik   
 * @date: 2015年9月15日 下午3:14:57 
 *
 */
package com.xtt.common.constants;

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

    public static final String DEFAULT_PASSWORD = MD5Util.md5("123456");// 默认密码

    /*--------------------状态提示相关 start --------------------*/
    /** 状态 */
    public static final String STATUS = "status";
    /** 失败 */
    public static final String FAILURE = "0";
    /** 成功 */
    public static final String SUCCESS = "1";
    /** 警告 */
    public static final String WARNING = "2";
    /** 无效的token */
    public static final String INVALIDATE_TOKEN = "3";
    /** 重复 */
    public static final String REPETITION = "4";
    /*--------------------状态提示相关 end --------------------*/

    /*--------------------系统日志相关 start --------------------*/
    public static final String SYS_LOG_TYPE_1 = "1";// 流程操作
    public static final String SYS_LOG_TYPE_2 = "2";// 登入、登出、个人资料修改
    public static final String SYS_LOG_TYPE_3 = "3";// 诊断
    public static final String SYS_LOG_TYPE_4 = "4";// 其它
    /*--------------------系统日志相关 end --------------------*/

    /*--------------------存储路径相关 start --------------------*/
    public static final String BASE_PATH = System.getProperty("os.name").toLowerCase().startsWith("win") ? "c:/xtt" : "/home/publish/xtt";
    public static final String ANNOUNCEMENT_FILE_PATH = "announcement";
    public static final String IMAGE_FILE_PATH = "images";
    public static final String IMAGE_FILE_PATH_PATIENT = "patient";
    public static final String IMAGE_FILE_PATH_PATIENT_BARCODE = "patient/barcode";
    public static final String USER_IMAGE_FILE_PATH = "user";
    public static final String QC_FILE_PATH = "quality_control";
    public static final String EXCEL_TEMPLATE_FILE_PATH = "excel_template";
    public static final String COMPLICATION = "complication";
    public static final String IMAGE_FILE_PATH_DIALYSIS_MACHINE = "dialysis_machine";
    /*--------------------存储路径相关 end --------------------*/

    /*--------------------api接口状态key start --------------------*/
    public static final String API_STATUS = "status";
    public static final String API_TOKEN = "token";
    public static final String API_ERROR_CODE = "errcode";
    public static final String API_ERROR_MESSAGE = "errmsg";
    public static final String API_SUCCESS = "1";
    /*--------------------api接口状态key end --------------------*/

    /*--------------------cache 相关常量 start --------------------*/
    public static final String COOKIE_TOKEN = "cookie_token";
    /** 缓存登陆用户key */
    public static final String LOGIN_USER = "login_user";
    /** 有权限的菜单集合 */
    public static final String USER_PERMISSION = "user_permission";
    /** 没有权限的菜单集合 */
    public static final String USER_NON_PERMISSION = "user_non_permission";
    /** api有权限集合 */
    public static final String API_PERMISSION = "api_permission";
    /*--------------------cache 相关常量 end --------------------*/
    /*--------------------角色相关常量 start --------------------*/
    /** 用户类型-普通用户 */
    public static final String USER_TYPE_NORMAL = "normal";
    /** 用户类型-集团管理员 */
    public static final String USER_TYPE_GROUP_ADMIN = "group_admin";
    /** 用户类型-集团用户 */
    public static final String USER_TYPE_GROUP = "group";
    /** 角色-管理员 */
    public static final String ROLE_ADMIN = "1";
    /** 角色-医生 */
    public static final String ROLE_DOCTOR = "2";
    /** 角色-护士 */
    public static final String ROLE_NURSE = "3";
    /** 角色-工程师 */
    public static final String ROLE_ENGINEER = "4";
    /** 角色-其它 */
    public static final String ROLE_OTHER = "10";
    /** 管理员 */
    public static final int ROLE_CONSTANT_ADMIN = 11;
    /** 主任 */
    public static final int ROLE_CONSTANT_DOCTOR_DIRECTOR = 21;
    /** 医生 */
    public static final int ROLE_CONSTANT_DOCTOR = 22;
    /** 护士长 */
    public static final int ROLE_CONSTANT_HEAD_NURSE = 31;
    /** 护士 */
    public static final int ROLE_CONSTANT_NURSE = 32;
    /*--------------------角色相关常量 end --------------------*/

    /*--------------------报表日期类别 start--------------------*/
    public static final String DATE_TYPE_YEAR = "y";
    public static final String DATE_TYPE_MONTH = "m";
    public static final String DATE_TYPE_WEEK = "w";
    public static final String DATE_TYPE_QUARTER = "q";
    /*--------------------报表日期类别 end--------------------*/

    /*---------------------- 项目名称 start ----------------------*/
    /** 系统名称 */
    public static final String SYS_NAME = "sysName";
    /** 系统key */
    public static final String SYS_OWNER = "sysOwner";
    /** 系统 - common */
    public static final String SYS_CM = "CM";
    /** 系统 - 腹透 */
    public static final String SYS_PD = "PD";
    /** 系统 - 血透 */
    public static final String SYS_HD = "HD";
    /*---------------------- 项目名称 end ----------------------*/

    /*--------------- 服务地址 start ---------------*/
    /** 请求的基础路径 */
    public static final String BASE_URL = PropertiesUtil.loadProperties(BASE_PATH.concat("/config/server.properties")).getProperty("base.url");
    /** 公用服务地址 */
    public static final String COMMON_SERVER_ADDR = BASE_URL + (BASE_URL.endsWith("/") ? "" : "/") + "common/";
    /** 随访服务地址 */
    public static final String FU_URL_KEY = "fu_addr";
    public static final String FU_URL = BASE_URL + (BASE_URL.endsWith("/") ? "" : "/") + "fu/";
    /** 腹透服务地址 */
    public static final String PD_URL_KEY = "pd_addr";
    public static final String PD_URL = BASE_URL + (BASE_URL.endsWith("/") ? "" : "/") + "pd/";
    /*--------------- 服务地址 end ---------------*/
}
