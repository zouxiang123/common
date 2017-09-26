package com.xtt.common.constants;

/**
 * @ClassName: IDownConst
 * @date: 216年7月28日 上午9:19:26
 * @version: V1.
 * 
 */
public interface IDownConst {

    /**
     * 下载该病患第三方所有数据
     */
    String DOWN_TYPE_PT_ALL_INFO = "all";
    // 下载入口downDB
    String DOWN_INPUT = "DD";

    // 删除下载日志信息
    String DEL_LOG_DOWN = "DLOG";

    // 下载病患图片
    String DOWN_TYPE_PT_IMAGE = "D2";

    // 下载病患基本资料
    String DOWN_TYPE_PT = "11";

    // 下载病患住院历史或门诊就诊历史
    String DOWN_TYPE_PT_VISITS = "A2";

    // 下载所有卡号信息
    String DOWN_TYPE_PT_CARDS = "A3";

    // 下载检验数据
    String DOWN_TYPE_LIS = "12";

    // 下载影像数据
    String DOWN_TYPE_PACS = "13";

    // 下载医嘱状态
    String DOWN_TYPE_ORDER = "14";

    // 下载病患手术信息（正大一附院使用到10108）
    String DONW_TYPE_OPERATION = "E1";

    // 发送医嘱状态
    String SEND_ORDER_STATUS = "24";

    // 发送医嘱费用（正大一附院使用到10108）
    String SEND_ORDER_CHARGES = "O1";

    // =======================================================
    // 生成检验报告单
    String DOWN_TYPE_LIS_REPORT = "R1";

    // 生成血透管理系统所需要的医嘱
    String DOWN_TYPE_ORDERS_REPORT = "R2";

    // 异常记录
    String DOWN_EXCEPTION = "1";

    // =======================================================
    // 下载入口downDB
    String BUILD_INPUT = "BB";

    // 生成全国质控上传所需要的数据（检验...）
    String BUILD_ZK_LAB_ITEMS = "Z1";

    // 构建检验字典数据
    String DICT_LAB_ITEMS = "D1";

    // 构建医嘱数据字典
    String DICT_ORDERS_ITEMS = "D2";
    // ========================================================
    // 租户
    String FK_TENANT_ID = "fk_tenant_id";
    // 病患基本信息
    String THIRD_PT = "third_pt";
    // 病患所有的卡号信息
    String THIRD_PT_CARDS = "third_pt_cards";
    // 病患所有的住院信息和门诊就诊信息
    String THIRD_PT_VISITS = "third_pt_visits";
    // 病患检验信息
    String THIRD_LIS = "third_lis";
    // 病患影像报告信息
    String THIRD_PACS = "third_pacs";
    // 手术信息
    String THIRD_OPERATION = "third_operation";
    // 病患医嘱信息
    String THIRD_ORDERS = "third_orders";
    // 医嘱项目字典
    String THIRD_ORDERS_DICT = "third_orders_dict";
    // ========================================================
    // 费用项目同步
    String WS_URL_CHARGE_SYNC = "ws_url_charge_sync";

    // 检验报告数据同步
    String SHZK_TYPE_LIS = "zk12";

    // 检验报告数据同步
    String SHZK_TYPE_TXCFX = "zk13";

    String SHZK_TYPE_REFRESH = "sx";

    int TENANT_XMZS = 10102;
}
