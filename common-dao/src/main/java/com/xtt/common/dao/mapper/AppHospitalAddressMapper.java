package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.AppHospitalAddress;

@Repository
public interface AppHospitalAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppHospitalAddress record);

    int insertSelective(AppHospitalAddress record);

    AppHospitalAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppHospitalAddress record);

    int updateByPrimaryKey(AppHospitalAddress record);

    /**
     * 根据条件查询
     * 
     * @Title: listByCond
     * @param record
     * @return
     *
     */
    List<AppHospitalAddress> listByCond(AppHospitalAddress record);
}