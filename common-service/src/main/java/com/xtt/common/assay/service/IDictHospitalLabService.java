/**   
 * @Title: IDictHospitalLabService.java 
 * @Package com.xtt.common.patient
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年4月22日 下午12:54:10 
 *
 */
package com.xtt.common.assay.service;

import java.util.Collection;
import java.util.List;

import com.xtt.common.dao.model.AssayGroupConfDetail;
import com.xtt.common.dao.model.DictHospitalLab;
import com.xtt.common.dao.po.DictHospitalLabPO;

public interface IDictHospitalLabService {
    /**
     * 手动录入化验项
     * 
     * @param record
     * @return
     */
    int insertSelective(DictHospitalLabPO record);

    /**
     * 根据itemCode查询医院字典表数据
     * 
     * @Title: getByItemCode
     * @param itemCode
     * @return
     * 
     */
    DictHospitalLabPO getByItemCode(String itemCode);

    /**
     * 查询化验项详情
     * 
     * @Title: selectTop
     * @param record
     * @return
     *
     */
    DictHospitalLabPO selectTop(DictHospitalLab record);

    /**
     * 获取所有的分类数据
     * 
     * @Title: getAllCategory
     * @return
     * 
     */
    List<DictHospitalLabPO> getAllCategory(DictHospitalLabPO record);

    /**
     * 根据条件获取检查项
     * 
     * @Title: getByCondition
     * @param record
     * @return
     * 
     */
    List<DictHospitalLabPO> getByCondition(DictHospitalLabPO record);

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
    String updateDictById(DictHospitalLab record);

    /**
     * 通过ItemCode来查询isTop，dataType，maxValue，minValue
     * 
     * @param itemCode
     * @return
     */
    List<DictHospitalLabPO> selectAllByItemCode(String itemCode);

    /**
     * 查询所有的化验月份
     * 
     * @Title: selectAllAssayMonth
     * @return
     * 
     */
    public List<String> selectAllAssayMonth(DictHospitalLab dictHospitalLab);

    /**
     * 修改检查项规则的PersonalMinValue，isTop,PersonalMaxValue,
     */
    void updateDictHospitalLabSomeValue(DictHospitalLab record);

    /**
     * 查询父节点GroupName，ID
     */
    public List<DictHospitalLabPO> selectGroupName();

    /**
     * 查询所有化验项
     * 
     * @return
     */
    public List<DictHospitalLabPO> selectAllGroup(Integer tenantId);

    /**
     * 维护化验项
     * 
     * @return
     */
    public List<DictHospitalLabPO> selectAdminGroup(Integer fkTenantId);

    /**
     * 查询所有
     */
    public List<DictHospitalLabPO> selectAll();

    /**
     * 根据大中小血类查询所有化验单及选中情况
     * 
     * @Title: selectAllCategoryByClass
     * @param dictHospitalLab
     * @return
     * 
     */
    public List<DictHospitalLabPO> selectAllCategoryByClass(DictHospitalLab dictHospitalLab);

    /**
     * 根据大中小血类查询所有化验项及选中情况
     * 
     * @Title: selectAllItemByClass
     * @param dictHospitalLab
     * @return
     * 
     */
    public List<DictHospitalLabPO> selectAllItemByClass(DictHospitalLab dictHospitalLab);

    /**
     * 查询化验项及选中的分组项
     * 
     * @Title: selectAllItemByGroupDetail
     * @param record
     * @return
     *
     */
    public List<DictHospitalLabPO> selectAllItemByGroupDetail(AssayGroupConfDetail record);

    /**
     * 添加化验单
     * 
     * @param list
     */
    void insertDictHospital(List<DictHospitalLabPO> list);

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
    Long getDictId(DictHospitalLabPO list);

    /**
     * 更新化验单
     * 
     * @param list
     * @return
     */
    int updateDictHospital(DictHospitalLabPO record);

    /**
     * 校验化验项名称是否与his系统重复
     * 
     * @param record
     * @return
     */
    int queryByGroupName(DictHospitalLabPO record);

    /**
     * 检测项目编号是否重复
     * 
     * @param record
     * @return
     */
    int queryByItemCode(DictHospitalLabPO record);

    /**
     * 根据关联code查询数据
     * 
     * @Title: selectByFkCodes
     * @param dictCodes
     * @return
     *
     */
    List<DictHospitalLabPO> selectByFkCodes(Collection<String> dictCodes);
}
