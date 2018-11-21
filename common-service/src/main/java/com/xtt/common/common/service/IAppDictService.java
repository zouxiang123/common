/**   
 * @Title: IAppDictService.java 
 * @Package com.xtt.common.common.service
 * Copyright: Copyright (c) 2015
 * @author: xyw   
 * @date: 2018年11月8日 下午1:32:54 
 *
 */
package com.xtt.common.common.service;

import java.util.List;

import com.xtt.common.dao.po.AppDictPO;

public interface IAppDictService {
    /**
     * 根据条件查询数据
     * 
     * @Title: selectByCondition
     * @param cmDictPO
     * @return
     *
     */
    List<AppDictPO> listByCond(AppDictPO record);

    /**
     * 根据唯一条件查询字典数据
     * 
     * @Title: getByUniqueCondition
     * @param pItemCode
     * @param itemCode
     * @return
     *
     */
    AppDictPO getByUniqueCondition(String pItemCode, String itemCode);
}
