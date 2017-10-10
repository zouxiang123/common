/**   
 * @Title: ICmDictService.java 
 * @Package com.xtt.common.common.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年9月23日 下午4:07:46 
 *
 */
package com.xtt.common.common.service;

import java.util.List;

import com.xtt.common.dao.model.CmDict;
import com.xtt.common.dao.po.CmDictPO;

public interface ICmDictService {
    /**
     * 根据租户id获取租户下所有有效的字典数据
     * 
     * @Title: selectAll
     * @param tenantId
     * @return
     *
     */
    List<CmDictPO> selectAll(Integer tenantId);

    /**
     * 根据自定义条件查询数据
     * 
     * @Title: getByCondition
     * @param record
     * @return
     *
     */
    List<CmDictPO> getByCondition(CmDictPO record);

    // 根据id删除字典数据
    public int deleteByPrimaryKey(Long id);

    /**
     * 
     * @Title: selectByType 根据类型查询字典集合
     * @param itemCode
     *            类型
     * @return 字典集合
     * 
     */
    public List<CmDictPO> selectByType(String itemCode);

    /**
     * 更新字典表数据
     * 
     * @Title: updateDictionary
     * @param record
     *
     */
    public void updateDictionary(CmDict record);

    /**
     * 通过Id获取对象
     * 
     * @Title: getById
     * @param id
     * @return
     *
     */
    public CmDict getById(Long id);

    /**
     * 获取所有的类别列表
     * 
     * @Title: getDictCategory
     * @param record
     * @return
     *
     */
    public List<CmDictPO> getDictCategory(CmDictPO record);

}
