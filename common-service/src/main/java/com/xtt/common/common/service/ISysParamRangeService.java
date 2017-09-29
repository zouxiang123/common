/**   
 * @Title: SysParamService.java 
 * @Package com.xtt.common.common.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年11月3日 下午3:19:29 
 *
 */
package com.xtt.common.common.service;

import java.util.List;

import com.xtt.common.dao.model.SysParamRange;

public interface ISysParamRangeService {

    /**
     * 保存系统参数配置
     * 
     * @Title: saveParam
     * @param param
     * @return
     *
     */
    void save(SysParamRange record);

    /**
     * 根据条件查询数据
     * 
     * @Title: selectByCondition
     * @param record
     * @return
     *
     */
    List<SysParamRange> listByCondition(SysParamRange record);

    /**
     * 根据项目编码获取参数范围配置
     * 
     * @param itemCode
     * @return
     */
    SysParamRange getByItemCode(String itemCode);

}
