/**   
 * @Title: IFollowUpConfSerivce.java 
 * @Package com.xtt.pd.system.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年6月28日 下午4:55:59 
 *
 */
package com.xtt.common.form.service;

import java.util.List;

import com.xtt.common.dao.po.CmFormBaseItemsPO;

public interface ICmFormBaseItemsService {
    /**
     * 获取所有的随访单配置项
     * 
     * @Title: selectAll
     * @return
     *
     */
    List<CmFormBaseItemsPO> selectAll();

    /**
     * 根据条件查询数据
     * 
     * @Title: selectByCondition
     * @param record
     * @return
     *
     */
    List<CmFormBaseItemsPO> selectByCondition(CmFormBaseItemsPO record);

    /** 根据item编号查询数据 */
    CmFormBaseItemsPO selectByItemCode(String itemCode, String sysOwner);

    /**
     * 保存item数据
     * 
     * @Title: saveItem
     * @param record
     * @return status
     *
     */
    String saveItem(CmFormBaseItemsPO record);

    /**
     * 根据itemcode删除节点
     * 
     * @Title: deleteByItemCode
     * @param itemCode
     * @param sysOwner
     *
     */
    String deleteByItemCode(String itemCode, String sysOwner);
}
