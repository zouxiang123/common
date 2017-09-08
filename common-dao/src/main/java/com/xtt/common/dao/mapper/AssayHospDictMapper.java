package com.xtt.common.dao.mapper;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.AssayGroupConfDetail;
import com.xtt.common.dao.model.AssayHospDict;
import com.xtt.common.dao.po.AssayHospDictPO;

@Repository
public interface AssayHospDictMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AssayHospDict record);

    int insertSelective(AssayHospDict record);

    AssayHospDict selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AssayHospDict record);

    int updateByPrimaryKeyWithBLOBs(AssayHospDict record);

    int updateByPrimaryKey(AssayHospDict record);

    /**
     * 查询是否重复
     * 
     * @Title: getCountByItemCode
     * @param itemCode
     * @param tenantId
     * @return
     *
     */
    int getCountByItemCode(@Param("itemCode") String itemCode, @Param("tenantId") Integer tenantId);

    /**
     * 根据租户id删除数据
     * 
     * @Title: deleteByTenant
     * @param tenantId
     *
     */
    void deleteByTenant(@Param("tenantId") Integer tenantId);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table dict_hospital_lab
     * 
     * @mbggenerated Wed Apr 20 19:31:08 CST 2016
     */
    int insert(AssayHospDictPO record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table dict_hospital_lab
     * 
     * @mbggenerated Wed Apr 20 19:31:08 CST 2016
     */
    int updateByPrimaryKeySelective(AssayHospDictPO record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table dict_hospital_lab
     * 
     * @mbggenerated Wed Apr 20 19:31:08 CST 2016
     */
    int updateByPrimaryKey(AssayHospDictPO record);

    /*--------- 自定义 ------------*/
    /**
     * 根据条件查询数据
     * 
     * @Title: listByCondition
     * @param query
     * @return
     * 
     */
    List<AssayHospDictPO> listByCondition(AssayHospDictPO query);

    /**
     * 查询所有的分类数据
     * 
     * @param query
     * 
     * @Title: selectAllCategory
     * @return
     * 
     */
    List<AssayHospDictPO> selectAllCategory(AssayHospDictPO query);

    /**
     * 解除关联
     * 
     * @Title: deleteAssayMapping
     * @param id
     * 
     */
    void deleteAssayMapping(Long id);

    /**
     * 根据报表类型查询化验项
     * 
     * @Title: selectAssayByDateType
     * @param query
     * @return
     * 
     */
    public List<AssayHospDictPO> selectAssayByDateType(AssayHospDictPO query);

    /**
     * 通过itemCode来查询所有
     * 
     * @param itemCode
     * @return
     */
    AssayHospDictPO getByItemCode(@Param("itemCode") String itemCode, @Param("fkTenantId") Integer fkTenantId);

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
     * 获取对应化验单id
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
     * 根据分组id和itemCode查询数据
     * 
     * @Title: getByGroupIdAndItemCode
     * @param record
     * @return
     *
     */
    AssayHospDictPO getByGroupIdAndItemCode(AssayHospDictPO record);

    /**
     * 查询化验单是否有相同的化验项
     * 
     * @Title: queryByItemCodeandGroupId
     * @param assayHospDict
     * @return
     *
     */
    Integer queryByItemCodeandGroupId(AssayHospDictPO assayHospDict);

    /**
     * 根据是否为常用化验项查询字典表
     * 
     * @Title: listByIsTop
     * @param isTop
     * @param fkTenandId
     * @return
     *
     */
    List<AssayHospDictPO> listByIsTop(@Param("isTop") Boolean isTop, @Param("fkTenantId") Integer fkTenandId);

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
     * @param fkTenandId
     * @param itemCodes
     * @return
     *
     */
    List<AssayHospDictPO> listTopForCommonReport(@Param("fkTenantId") Integer fkTenandId, @Param("itemCodes") Collection<String> itemCodes);

    /**
     * 根据id获取数据
     * 
     * @Title: getById
     * @param id
     * @return
     *
     */
    AssayHospDictPO getById(Long id);
}