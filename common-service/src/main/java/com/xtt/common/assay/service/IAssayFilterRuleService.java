/**   
 * @Title: IAssayFilterRuleService.java 
 * @Package com.xtt.common.assay.service
 * Copyright: Copyright (c) 2015
 * @author: Administrator   
 * @date: 2017年8月30日 下午5:00:08 
 *
 */
package com.xtt.common.assay.service;

import com.xtt.common.dao.model.AssayFilterRule;

public interface IAssayFilterRuleService {

    /**
     * 根据租户查询清洗数据逻辑
     * 
     * @Title: getAssayFilterRuleByTenantId
     * @param fkTenantId
     * @return
     *
     */
    AssayFilterRule getByTenantId(Integer fkTenantId);

    /**
     * 根据租户id更新清洗数据逻辑
     * 
     * @Title: updateByfkTenantId
     * @param assayFilterRule
     *
     */
    void updateByfkTenantId(AssayFilterRule assayFilterRule);

}
