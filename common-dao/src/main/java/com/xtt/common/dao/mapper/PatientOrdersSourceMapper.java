package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.PatientOrdersSource;
import com.xtt.common.dao.po.PatientOrdersPO;
import com.xtt.common.dao.po.PatientOrdersSourcePO;

@Repository
public interface PatientOrdersSourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PatientOrdersSource record);

    int insertSelective(PatientOrdersSource record);

    PatientOrdersSource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PatientOrdersSource record);

    int updateByPrimaryKey(PatientOrdersSource record);

    List<PatientOrdersSource> selectByPatientOrdersSourcePO(PatientOrdersSource pto);

    List<PatientOrdersPO> selectByOrderDict(@Param("createTime") java.lang.String createTime);

    // 下载医嘱信息
    List<PatientOrdersSourcePO> selectDownOrdersSourcePO(PatientOrdersSourcePO orders);
}