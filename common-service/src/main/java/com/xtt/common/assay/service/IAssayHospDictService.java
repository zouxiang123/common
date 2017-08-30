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

import com.xtt.common.dao.model.AssayGroupConfDetail;
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
     * 批量插入
     * 
     * @Title: insertList
     * @param list
     *
     */
    void insertList(List<AssayHospDict> list);

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
    int insertSelective(AssayHospDictPO record);

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
     * 查询化验项详情
     * 
     * @Title: selectTop
     * @param record
     * @return
     *
     */
    AssayHospDictPO selectTop(AssayHospDictPO record);

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
    void deleteAssayMapping(Long id);

    /**
     * 更新数据
     * 
     * @Title: updateDict
     * @param record
     * 
     */
    String updateDictById(AssayHospDictPO assayHospDict);

    /**
     * 通过ItemCode来查询isTop，dataType，maxValue，minValue
     * 
     * @param itemCode
     * @return
     */
    List<AssayHospDictPO> selectAllByItemCode(String itemCode);

    /**
     * 修改检查项规则的PersonalMinValue，isTop,PersonalMaxValue,
     */
    void updateDictHospitalLabSomeValue(AssayHospDictPO assayHospDictPO);

    /**
     * 查询所有化验项
     * 
     * @return
     */
    public List<AssayHospDictPO> selectAllGroup();

    /**
     * 根据大中小血类查询所有化验单及选中情况
     * 
     * @Title: selectAllCategoryByClass
     * @param dictHospitalLab
     * @return
     * 
     */
    public List<AssayHospDictPO> selectAllCategoryByClass(AssayHospDictPO dictHospitalLab);

    /**
     * 根据大中小血类查询所有化验项及选中情况
     * 
     * @Title: selectAllItemByClass
     * @param dictHospitalLab
     * @return
     * 
     */
    public List<AssayHospDictPO> selectAllItemByClass(AssayHospDictPO dictHospitalLab);

    /**
     * 查询化验项及选中的分组项
     * 
     * @Title: selectAllItemByGroupDetail
     * @param record
     * @return
     *
     */
    public List<AssayHospDictPO> selectAllItemByGroupDetail(AssayGroupConfDetail record);

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
     * 更新化验单
     * 
     * @param list
     * @return
     */
    int updateDictHospital(AssayHospDictPO record);

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
     * 根据关联code查询数据
     * 
     * @Title: selectByFkCodes
     * @param dictCodes
     * @return
     *
     */
    List<AssayHospDictPO> selectByFkCodes(Collection<String> dictCodes);

    /**
     * 查询所有手动添加数据
     * 
     * @Title: listAllManualAdd
     * @return
     *
     */
    List<AssayHospDictPO> listAllManualAdd();

    /**
     * 查询不重复的item_code
     * 
     * @Title: seleteItemCodeByCondition
     * @param assayHospDict
     * @return
     *
     */
    List<AssayHospDictPO> seleteItemCodeByCondition(AssayHospDictPO AssayHospDictPO);

}
