/**   
 * @Title: IFormValueService.java 
 * @Package com.xtt.common.form
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年9月23日 下午1:25:08 
 *
 */
package com.xtt.common.form.service;

import java.util.List;

public interface IFormValueService {
    /**
     * 根据itemCode和版本号查询数据
     * 
     * @Title: selectByItemCode
     * @param itemCode
     * @param version
     * @return
     *
     */
    List<?> selectByItemCode(String itemCode, Integer version);

    /**
     * 根据类别和版本号查询数据
     * 
     * @Title: selectByCategory
     * @param category
     * @param version
     * @return
     *
     */
    List<?> selectByCategory(String category, Integer version);

}
