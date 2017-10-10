package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.AssayGroupConfDetail;

@Repository
public interface AssayGroupConfDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AssayGroupConfDetail record);

    int insertSelective(AssayGroupConfDetail record);

    AssayGroupConfDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AssayGroupConfDetail record);

    int updateByPrimaryKey(AssayGroupConfDetail record);

    /**
     * 查询同类分组化验项
     * 
     * @Title: selectByFkAssayGroupConfId
     * @param fkAssayGroupConfId
     * @return
     *
     */
    List<AssayGroupConfDetail> selectByFkAssayGroupConfId(Long fkAssayGroupConfId);

    /**
     * 删除同类分组化验项
     * 
     * @Title: deleteDetail
     * @param fkAssayGroupConfId
     * @return
     *
     */
    int deleteByFkAssayGroupConfId(Long fkAssayGroupConfId);
}