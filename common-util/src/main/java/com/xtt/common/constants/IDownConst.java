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
    String DOWN_INPUT = "10";

    // 下载病患基本资料
    String DOWN_TYPE_PT = "11";

    // 下载病患基本信息资料
    String DOWN_TYPE_PT_TYPE = "A2";

    // 下载检验数据
    String DOWN_TYPE_LIS = "12";

    // 下载影像数据
    String DOWN_TYPE_PACS = "13";

    // 下载医嘱状态
    String DOWN_TYPE_ORDER = "14";

    // 发送医嘱状态
    String SEND_ORDER_STATUS = "24";

    // 生成检验报告单
    String DOWN_TYPE_LIS_REPORT = "21";

    // 检验报告数据同步
    String SHZK_TYPE_LIS = "zk12";

    // 检验报告数据同步
    String SHZK_TYPE_TXCFX = "zk13";

    String SHZK_TYPE_REFRESH = "sx";
}
