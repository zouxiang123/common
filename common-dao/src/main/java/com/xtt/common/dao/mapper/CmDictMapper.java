package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmDict;
import com.xtt.common.dao.po.CmDictPO;

@Repository
public interface CmDictMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CmDict record);

    int insertSelective(CmDict record);

    CmDict selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmDict record);

    int updateByPrimaryKey(CmDict record);

    /*user define*/
    /**
     * 根据条件查询数据
     * 
     * @Title: selectByCondition
     * @param cmDictPO
     * @return
     *
     */
    List<CmDictPO> selectByCondition(CmDict record);

    /**
     * 查询字典表类别
     * 
     * @Title: selectDictionaryCategory
     * @param record
     * @return
     *
     */
    List<CmDictPO> selectDictCategory(CmDict record);

    /**
     * 根据唯一条件查询字典数据
     * 
     * @Title: getByUniqueCondition
     * @param pItemCode
     *            类别编号
     * @param itemCode
     *            项目编号
     * @param fkTenantId
     *            租户id
     * @return
     *
     */
    CmDict getByUniqueCondition(@Param("pItemCode") String pItemCode, @Param("itemCode") String itemCode, @Param("fkTenantId") Integer fkTenantId);
}