package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.AssayGroupConfDetail;
import com.xtt.common.dao.model.DictHospitalLab;
import com.xtt.common.dao.po.DictHospitalLabPO;

@Repository
public interface DictHospitalLabMapper {
    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table dict_hospital_lab
     * 
     * @mbggenerated Wed Apr 20 19:31:08 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table dict_hospital_lab
     * 
     * @mbggenerated Wed Apr 20 19:31:08 CST 2016
     */
    int insert(DictHospitalLab record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table dict_hospital_lab
     * 
     * @mbggenerated Wed Apr 20 19:31:08 CST 2016
     */
    int insertSelective(DictHospitalLab record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table dict_hospital_lab
     * 
     * @mbggenerated Wed Apr 20 19:31:08 CST 2016
     */
    DictHospitalLab selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table dict_hospital_lab
     * 
     * @mbggenerated Wed Apr 20 19:31:08 CST 2016
     */
    int updateByPrimaryKeySelective(DictHospitalLab record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table dict_hospital_lab
     * 
     * @mbggenerated Wed Apr 20 19:31:08 CST 2016
     */
    int updateByPrimaryKey(DictHospitalLab record);

    /*--------- 自定义 ------------*/
    /**
     * 根据条件查询数据
     * 
     * @Title: selectByCondition
     * @param query
     * @return
     * 
     */
    List<DictHospitalLabPO> selectByCondition(DictHospitalLab query);

    /**
     * 查询所有的分类数据
     * 
     * @param query
     * 
     * @Title: selectAllCategory
     * @return
     * 
     */
    List<DictHospitalLabPO> selectAllCategory(DictHospitalLab query);

    /**
     * 解除关联
     * 
     * @Title: deleteAssayMapping
     * @param id
     * 
     */
    void delAssayMapping(Long id);

    /**
     * 根据报表类型查询化验项
     * 
     * @Title: selectAssayByDateType
     * @param query
     * @return
     * 
     */
    public List<DictHospitalLabPO> selectAssayByDateType(DictHospitalLab query);

    /**
     * 通过itemCode来查询所有
     * 
     * @param itemCode
     * @param fkTenantId
     * @return
     */
    public List<DictHospitalLabPO> selectAllByItemCode(@Param("itemCode") String itemCode, @Param("fkTenantId") Integer fkTenantId);

    /**
     * 查询所有的化验月份
     * 
     * @Title: selectAllAssayMonth
     * @return
     * 
     */
    public List<String> selectAllAssayMonth(DictHospitalLab dictHospitalLab);

    /**
     * 查询所有的GroupName，ID
     */
    public List<DictHospitalLabPO> selectGroupName(@Param("fkTenantId") Integer fkTenantId);

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
     * 查询id，group_id，group_name，item_code，Item_name,Unit
     */
    public List<DictHospitalLabPO> selectSomeField(DictHospitalLab dictHospitalLab);

    /**
     * @param list
     *            手动添加化验项字典表
     */
    void insertDictHospital(List<DictHospitalLabPO> list);

    /**
     * 删除手动录入的化验项
     * 
     * @param groupId
     * @return
     */
    int deleteById(Long id);

    /**
     * 获取对应化验单id
     * 
     * @param list
     * @return
     */
    Long getDictId(DictHospitalLabPO list);

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
     * 查询化验项详情
     * 
     * @Title: selectTop
     * @param record
     * @return
     *
     */
    DictHospitalLabPO selectTop(DictHospitalLab record);

}