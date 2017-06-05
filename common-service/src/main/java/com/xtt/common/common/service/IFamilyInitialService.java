/**   
 * @Title: IFamilyInitialService.java 
 * @Package com.xtt.txgl.system.service
 * Copyright: Copyright (c) 2015
 * @author: abc   
 * @date: 2017年4月18日 上午10:29:13 
 *
 */
package com.xtt.common.common.service;

import java.util.List;

import com.xtt.common.dao.model.FamilyInitial;

/**
 * @ClassName: IFamilyInitialService
 * @date: 2017年4月18日 上午10:29:13
 * @version: V1.0
 *
 */
public interface IFamilyInitialService {
    /**
     * @Title: 根据条件查询
     * @param record
     * @return
     *
     */
    List<FamilyInitial> listByCondition(FamilyInitial record);

    /**
     * 插入数据
     * 
     * @Title: insert
     * @param record
     *
     */
    void insert(FamilyInitial record);

    /**
     * 根据姓获取首字母
     * 
     * @Title: getInitial
     * @param familyName
     * @return
     *
     */
    String getInitial(String familyName);

}
