/**   
 * @Title: Constants.java 
 * @Package com.xtt.txgl.common.util
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

	public static final String FAILURE = "0";// 失败
	public static final String SUCCESS = "1";// 成功
	public static final String WARNING = "2";// 警告

	public static final String SYS_LOG_TYPE_1 = "1";// 流程操作
	public static final String SYS_LOG_TYPE_2 = "2";// 登入、登出、个人资料修改
	public static final String SYS_LOG_TYPE_3 = "3";// 诊断
	public static final String SYS_LOG_TYPE_4 = "4";// 其它

	public static final String BASE_PATH = "c:/xtt";
	public static final String ANNOUNCEMENT_FILE_PATH = "announcement";
	public static final String IMAGE_FILE_PATH = "images";
	public static final String IMAGE_FILE_PATH_PATIENT = "patient";
	public static final String IMAGE_FILE_PATH_PATIENT_BARCODE = "patient/barcode";
	public static final String USER_IMAGE_FILE_PATH = "user";
	public static final String QC_FILE_PATH = "quality_control";
	public static final String EXCEL_TEMPLATE_FILE_PATH = "excel_template";
	public static final String COMPLICATION = "complication";
	public static final String IMAGE_FILE_PATH_DIALYSIS_MACHINE = "dialysis_machine";

	public static final String STATUS = "status";
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
	public static final String ROLE_OTHER = "4";
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

	public static final String DATE_TYPE_YEAR = "y";
	public static final String DATE_TYPE_MONTH = "m";
	public static final String DATE_TYPE_WEEK = "w";
	public static final String DATE_TYPE_QUARTER = "q";

	/** 操作 ：add：新增 delete：删除 */
	public static final String OPERATION_ADD = "add";
	public static final String OPERATION_SELECT = "select";

	/** 项目名称 */
	public static final String SYS_NAME = "sysName";
	public static final String SYS_PD = "PD";
	public static final String SYS_HD = "HD";
	/** 公用服务地址 */
	public static final String COMMON_SERVER_ADDR = PropertiesUtil.loadProperties(BASE_PATH.concat("/config/server.properties"))
					.getProperty("common.server.addr");
	// -----------------------------肾病类型--------------------------------------------
	/** 肾病类型：慢性肾功能衰竭 */
	public static final String NEPHROSIS_TYPE_CRF = "1";
	/** 肾病类型：慢性肾功能不全急性加重 */
	public static final String NEPHROSIS_TYPE_SERIOUS_CRF = "2";
	/** 肾病类型：急性肾功能衰竭 */
	public static final String NEPHROSIS_TYPE_ARF = "3";
}