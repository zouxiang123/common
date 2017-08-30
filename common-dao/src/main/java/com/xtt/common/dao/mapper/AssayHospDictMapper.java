package com.xtt.common.dao.mapper;

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

    AssayHospDictPO selectByPrimaryKey(Long id);

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
    int insertSelective(AssayHospDictPO record);

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
     * @param fkTenantId
     * @return
     */
    public List<AssayHospDictPO> selectAllByItemCode(@Param("itemCode") String itemCode, @Param("fkTenantId") Integer fkTenantId);

    /**
     * 查询所有化验项分组
     * 
     * @param fkTenantId
     * 
     * @return
     */
    public List<AssayHospDictPO> selectAllGroup(@Param("fkTenantId") Integer fkTenantId);

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
     * @param list
     *            手动添加化验项字典表
     */
    void insertDictHospital(List<AssayHospDictPO> list);

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
     * 查询化验项详情
     * 
     * @Title: selectTop
     * @param record
     * @return
     *
     */
    AssayHospDictPO selectTop(AssayHospDictPO record);
}