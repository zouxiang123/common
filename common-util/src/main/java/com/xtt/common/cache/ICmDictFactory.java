/**   
 * @Title: ICmDictFactory.java 
 * @Package com.xtt.common.common.cache
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年9月23日 下午3:59:40 
 *
 */
package com.xtt.common.cache;

import java.util.List;

import com.xtt.common.dto.DictDto;

public interface ICmDictFactory {
    /**
     * 根据itemCode获取名称
     * 
     * @Title: getItemName
     * @param pItemCode
     *            类别编号
     * @param itemCode
     *            编号
     * @param tenantId
     * @return
     *
     */
    public String getItemName(String pItemCode, String itemCode, Integer tenantId);

    /**
     * 根据类别编号获取字典集合
     * 
     * @Title: getListByPItemCode
     * @param pItemCode
     *            类别编号
     * @param tenantId
     *            租户id
     * @return
     *
     */
    public List<DictDto> listByPItemCode(String pItemCode, Integer tenantId);

    /**
     * 根据名称获取对应的值
     * 
     * @Title: getItemCode
     * @param pItemCode
     *            类别编号
     * @param itemName
     *            名称
     * @return
     *
     */
    public String getItemCode(String pItemCode, String itemName);

}
