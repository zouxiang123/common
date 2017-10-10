/**   
 * @Title: ICmFormulaConfService.java 
 * @Package com.xtt.common.conf.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年12月10日 上午9:50:17 
 *
 */
package com.xtt.common.conf.service;

import java.util.List;

import com.xtt.common.dao.po.CmFormulaConfPO;

public interface ICmFormulaConfService {
    /**
     * 根据条件查询数据
     * 
     * @Title: selectByCondition
     * @param record
     * @return
     *
     */
    List<CmFormulaConfPO> selectByCondition(CmFormulaConfPO record);

    /**
     * 根据主键批量更新数据
     * 
     * @Title: updateByPrimaryKeySelective
     * @param list
     *
     */
    void updateByPrimaryKeySelective(List<CmFormulaConfPO> list);
}
