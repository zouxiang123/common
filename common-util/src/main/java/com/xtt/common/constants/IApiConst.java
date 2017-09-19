package com.xtt.common.constants;

/**
 * @ClassName com.lt.constant.IApiConst.java
 * @Description:公用的供下载数据服务调用血透服务的入口定义
 * @author zz
 * @version V1.0
 * @date 2017年4月28日 下午4:05:42
 */
public interface IApiConst {
    int TIME_DAY_MILLISECOND = 86400000;

    // 1.刷新病患缓存
    String REFRESH_PT_CACHE = "refresh_pt_cache";

    // 2.更新病患类型
    String UPDATE_PT_TYPE = "update_pt_type";

    // 3.调用生成检验报表
    String REPORT_LIS = "report_lis";

    // 4.调用生成医嘱信息
    String GENERATE_ORDER = "generate_order";
    /**
     * lis删除所有
     */
    String LIS_SUB_TYPE_RM_ALL = "lis_rm_all";
    /**
     * list删除单个患者
     */
    String LIS_SUB_TYPE_RM_ONE = "lis_rm_one";

}