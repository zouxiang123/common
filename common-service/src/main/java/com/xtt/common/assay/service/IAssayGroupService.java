/**   
 * @Title: IFavoriteService.java 
 * @Package com.xtt.common.system.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年12月26日 下午2:39:44 
 *
 */
package com.xtt.common.assay.service;

import java.util.List;
import java.util.Set;

import com.xtt.common.dao.model.AssayGroupConf;
import com.xtt.common.dao.model.AssayGroupConfDetail;
import com.xtt.common.dao.po.AssayGroupConfPO;

public interface IAssayGroupService {

    /**
     * 查询同类分组列表
     * 
     * @Title: selectByCondition
     * @param record
     * @return
     *
     */
    List<AssayGroupConf> selectByCondition(AssayGroupConf record);

    /**
     * 
     * @Title: selectByPrimaryKey
     * @param id
     * @return
     *
     */
    AssayGroupConf selectByPrimaryKey(Long id);

    /**
     * 保存分组
     * 
     * @Title: save
     * @param record
     *
     */
    void save(AssayGroupConfPO record);

    /**
     * 删除分组
     * 
     * @Title: deleteByPrimaryKey
     * @param id
     *
     */
    void deleteByPrimaryKey(Long id);

    /**
     * 查询同类分组化验项
     * 
     * @Title: selectDetail
     * @param fkAssayGroupConfId
     * @return
     *
     */
    List<AssayGroupConfDetail> selectDetail(Long fkAssayGroupConfId);

    /**
     * 根据itemCode获取对应的组
     * 
     * @Title: getByItemCode
     * @param itemCode
     * @param tenantId
     * @return
     *
     */
    AssayGroupConfDetail getByItemCode(String itemCode, Integer tenantId);

    /**
     * 根据itemCode获取所属同类组下所有itemCode
     * 
     * @Title: listGroupItemCodes
     * @param itemCode
     * @param tenantId
     * @return
     *
     */
    Set<String> listGroupItemCodes(String itemCode, Integer tenantId);
}
