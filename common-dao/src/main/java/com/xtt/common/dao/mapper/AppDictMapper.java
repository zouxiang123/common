package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.AppDict;
import com.xtt.common.dao.po.AppDictPO;

@Repository
public interface AppDictMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppDict record);

    int insertSelective(AppDict record);

    AppDict selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppDict record);

    int updateByPrimaryKey(AppDict record);

    /**
     * 根据条件查询数据
     * 
     * @Title: selectByCondition
     * @param cmDictPO
     * @return
     *
     */
    List<AppDictPO> listByCond(AppDictPO record);

    /**
     * 根据唯一条件查询字典数据
     * 
     * @Title: getByUniqueCondition
     * @param pItemCode
     * @param itemCode
     * @return
     *
     */
    AppDictPO getByUniqueCondition(@Param("pItemCode") String pItemCode, @Param("itemCode") String itemCode);

}