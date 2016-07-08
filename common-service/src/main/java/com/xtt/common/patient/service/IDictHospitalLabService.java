/**   
 * @Title: IDictHospitalLabService.java 
 * @Package com.xtt.txgl.patient
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年4月22日 下午12:54:10 
 *
 */
package com.xtt.common.patient.service;

import java.util.List;

import com.xtt.common.dao.model.DictHospitalLab;
import com.xtt.common.dao.po.DictHospitalLabPO;

public interface IDictHospitalLabService {
	/**
	 * 根据itemCode查询医院字典表数据
	 * 
	 * @Title: getByItemCode
	 * @param itemCode
	 * @return
	 * 
	 */
	DictHospitalLabPO getByItemCode(String itemCode);

	/**
	 * 获取所有的分类数据
	 * 
	 * @Title: getAllCategory
	 * @return
	 * 
	 */
	List<DictHospitalLabPO> getAllCategory(DictHospitalLabPO record);

	/**
	 * 根据条件获取检查项
	 * 
	 * @Title: getByCondition
	 * @param record
	 * @return
	 * 
	 */
	List<DictHospitalLabPO> getByCondition(DictHospitalLabPO record);

	/**
	 * 解除关联
	 * 
	 * @Title: deleteAssayMapping
	 * @param id
	 * 
	 */
	void deleteAssayMapping(Long id);

	/**
	 * 更新数据
	 * 
	 * @Title: updateDict
	 * @param record
	 * 
	 */
	String updateDictById(DictHospitalLab record);

	/**
	 * 通过ItemCode来查询isTop，dataType，maxValue，minValue
	 * 
	 * @param itemCode
	 * @return
	 */
	List<DictHospitalLabPO> selectAllByItemCode(String itemCode);

	/**
	 * 查询所有的化验月份
	 * 
	 * @Title: selectAllAssayMonth
	 * @return
	 * 
	 */
	public List<String> selectAllAssayMonth(DictHospitalLab dictHospitalLab);

	/**
	 * 修改检查项规则的PersonalMinValue，isTop,PersonalMaxValue,
	 */
	void updateDictHospitalLabSomeValue(DictHospitalLab record);

}
