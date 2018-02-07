/**   
 * @Title: IAssayHospDictService.java 
 * @Package com.xtt.common.assay.service
 * Copyright: Copyright (c) 2015
 * @author: Administrator   
 * @date: 2017年8月21日 上午11:50:15 
 *
 */
package com.xtt.common.assay.service;

import java.util.Collection;
import java.util.List;

import com.xtt.common.dao.model.AssayHospDict;
import com.xtt.common.dao.po.AssayHospDictPO;

public interface IAssayHospDictService {

    /**
     * 插入数据
     * 
     * @Title: insert
     * @param record
     * @return
     *
     */
    int insert(AssayHospDict record);

    /**
     * 根据itemCode 查询是否存在
     * 
     * @Title: getCountByItemCode
     * @param ItemCode
     * @return
     *
     */
    int getCountByItemCode(String itemCode);

    /**
     * 根据租户删除数据
     * 
     * @Title: deleteByTenant
     * @param tenantId
     *
     */
    void deleteByTenant(Integer tenantId);

    /**
     * 手动录入化验项
     * 
     * @param record
     * @return
     */
    void insertManual(AssayHospDictPO record);

    /**
     * 根据itemCode查询医院字典表数据
     * 
     * @Title: getByItemCode
     * @param itemCode
     * @return
     * 
     */
    AssayHospDictPO getByItemCode(String itemCode);

    /**
     * 根据分组id和itemCode查询数据
     * 
     * @Title: getByGroupIdAndItemCode
     * @param record
     * @return
     *
     */
    AssayHospDictPO getByGroupIdAndItemCode(AssayHospDictPO record);

    /**
     * 获取所有的分类数据
     * 
     * @Title: getAllCategory
     * @return
     * 
     */
    List<AssayHospDictPO> getAllCategory(AssayHospDictPO record);

    /**
     * 根据条件获取检查项
     * 
     * @Title: getByCondition
     * @param record
     * @return
     * 
     */
    List<AssayHospDictPO> getByCondition(AssayHospDictPO record);

    /**
     * 解除关联
     * 
     * @Title: deleteAssayMapping
     * @param id
     * 
     */
    void deleteMapping(Long id);

    /**
     * 根据ids建立映射关系
     * 
     * @Title: saveMappingDictByIds
     * @param record
     * 
     */
    void saveMappingDictByIds(AssayHospDictPO assayHospDict);

    /**
     * 修改检查项规则的PersonalMinValue，isTop,PersonalMaxValue,
     */
    void updateSomeValue(AssayHospDictPO assayHospDictPO);

    /**
     * 删除手动录入的化验单
     * 
     * @param groupId
     * @return
     */
    int deleteById(Long groupId);

    /**
     * 获取对应化验单的id
     * 
     * @param list
     * @return
     */
    Long getDictId(AssayHospDictPO list);

    /**
     * 校验化验项名称是否与his系统重复
     * 
     * @param record
     * @return
     */
    int queryByGroupName(AssayHospDictPO record);

    /**
     * 检测项目编号是否重复
     * 
     * @param record
     * @return
     */
    int queryByItemCode(AssayHospDictPO record);

    /**
     * 查询所有手动添加数据
     * 
     * @Title: listAllManualAdd
     * @return
     *
     */
    List<AssayHospDictPO> listAllManualAdd();

    /**
     * 查询化验单是否有相同的化验项
     * 
     * @Title: queryByItemCodeandGroupId
     * @param assayHospDict
     * @return
     *
     */
    Integer queryByItemCodeandGroupId(AssayHospDictPO assayHospDictPO);

    /**
     * 更新化验单
     * 
     * @Title: updateAssay
     * @param record
     * @return
     *
     */
    void updateAssay(AssayHospDictPO record);

    /**
     * 根据是否为常用化验项查询字典表
     * 
     * @Title: listIteCodeByIsTop
     * @param isTop
     *
     */
    List<AssayHospDictPO> listByIsTop(Boolean isTop);

    /**
     * 根据自定义条件查询不包含groupId的数据
     * 
     * @Title: listBasicByCondition
     * @param record
     * @return
     *
     */
    List<AssayHospDictPO> listBasicByCondition(AssayHospDictPO record);

    /**
     * 查询置顶项for常用化验项统计
     * 
     * @Title: listTopForCommonReport
     * @param tenantId
     * @param itemCodes
     * @return
     *
     */
    List<AssayHospDictPO> listTopForCommonReport(Integer tenantId, Collection<String> itemCodes);

    /**
     * 获取id获取数据
     * 
     * @Title: getById
     * @param id
     * @return
     *
     */
    AssayHospDictPO getById(Long id);

    /**
     * 通过患者化验单字典表外键 查询项目编码
     * 
     * @Title: listItemCodeBydictCcode
     * @param dictCode
     * @return List<String>
     *
     */
    List<String> listItemCodeBydictCcode(String dictCode);

    /**
     * 自动插入化验字典数据
     * 
     * @Title: insertAuto
     * @param tenantId
     * @param dateStr
     *
     */
    void insertAuto(Integer tenantId, String dateStr);

    /**
     * 插入字典数据，包含分组数据和mapping数据
     * 
     * @Title: insertDictData
     * @param items
     *
     */
    void insertList(List<AssayHospDictPO> items);

    /**
     * 根据itemCode获取对应的dictCode
     * 
     * @Title: getFkDictCodeByItemCode
     * @param itemCode
     * @param tenantId
     * @return
     *
     */
    String getFkDictCodeByItemCode(String itemCode, Integer tenantId);

    /**
     * 根据itemCode和租户id获取同类itemCode(包含自己本身)
     * 
     * @Title: listSimilarItemCode
     * @param itemCode
     * @param tenantId
     * @return
     *
     */
    List<String> listSimilarItemCode(String itemCode, Integer tenantId);

    /**
     * 查询处理过itemCode的记录（如果存在fk_dict_code，则itemCode为fk_dict_uk,否则为item_code）
     * 
     * @Title: listProcessedItemCodeRec
     * @return
     *
     */
    List<AssayHospDictPO> listProcessedItemCodeRec(AssayHospDictPO record);

    /**
     * 自动建立和fkDictCode之间的关联 <note>如果itemCode或者itemName和血透的字典表数据一致，则自动映射过去</note>
     * 
     * @Title: autoMappingDict
     * @param tenantId
     *
     */
    public void autoMappingDict(Integer tenantId);
}
