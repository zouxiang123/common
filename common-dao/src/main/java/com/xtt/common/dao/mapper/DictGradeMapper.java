package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.DictGrade;
import com.xtt.common.dao.po.DictGradePO;

@Repository
public interface DictGradeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DictGrade record);

    int insertSelective(DictGrade record);

    DictGrade selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DictGrade record);

    int updateByPrimaryKey(DictGrade record);

    /*user define*/
    /**
     * 根据自定义条件查询数据
     * <p>
     * fkTenantId 为必需参数
     * </p>
     * 
     * @Title: listByCondition
     * @param record
     * @return
     *
     */
    List<DictGradePO> listByCondition(DictGrade record);

    /**
     * 根据租户id查询所有的类别
     * 
     * @Title: listCategory
     * @param fkTenantId
     * @return
     *
     */
    List<DictGradePO> listCategory(Integer fkTenantId);
}