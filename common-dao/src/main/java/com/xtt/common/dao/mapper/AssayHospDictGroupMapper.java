package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.AssayGroupConfDetail;
import com.xtt.common.dao.model.AssayHospDictGroup;
import com.xtt.common.dao.po.AssayHospDictPO;

@Repository
public interface AssayHospDictGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AssayHospDictGroup record);

    int insertSelective(AssayHospDictGroup record);

    AssayHospDictGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AssayHospDictGroup record);

    int updateByPrimaryKey(AssayHospDictGroup record);

    /**
     * 批量插入
     * 
     * @Title: insertList
     * @param list
     *
     */
    void insertList(List<AssayHospDictGroup> list);

    /**
     * 查询是否存在
     * 
     * @Title: getCountByGroupId
     * @param groupId
     * @param tenantId
     * @return
     *
     */
    int getCountByGroupId(@Param("groupId") String groupId, @Param("tenantId") Integer tenantId);

    /**
     * 根据租户号删除数据
     * 
     * @Title: deleteByTenant
     * @param tenantId
     *
     */
    void deleteByTenant(@Param("tenantId") Integer tenantId);

    /*--------- 自定义 ------------*/
    /**
     * 根据条件查询数据
     * 
     * @Title: selectByCondition
     * @param query
     * @return
     * 
     */
    List<AssayHospDictPO> selectByCondition(AssayHospDictPO query);

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
    void delAssayMapping(Long id);

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
    public List<AssayHospDictPO> selectAllByItemCode(String itemCode);

    /**
     * 查询所有的GroupName，ID
     */
    public List<AssayHospDictPO> selectGroupName();

    /**
     * 查询所有化验项
     * 
     * @return
     */
    public List<AssayHospDictPO> selectAllGroup();

    /**
     * 维护化验项
     * 
     * @return
     */
    public List<AssayHospDictPO> selectAdminGroup();

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
     * 查询id，group_id，group_name，item_code，Item_name,Unit
     */
    public List<AssayHospDictPO> selectSomeField(AssayHospDictPO dictHospitalLab);

    /**
     * @param list
     *            手动添加化验项字典表
     */
    void insertDictHospital(List<AssayHospDictPO> list);

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