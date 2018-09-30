/**   
 * @Title: ICmDictIcd.java 
 * @Package com.xtt.common.conf.service
 * Copyright: Copyright (c) 2015
 * @author: zx   
 * @date: 2018年9月29日 上午10:00:34 
 *
 */
package com.xtt.common.conf.service;

import java.util.HashMap;
import java.util.List;

import com.xtt.common.dao.model.CmDictIcd;
import com.xtt.common.dao.po.CmDiagnosisEntityPO;
import com.xtt.common.dao.po.CmDiagnosisIcdPO;

public interface ICmDictIcdService {

    /**
     * 根据icd名称查询
     */
    public List<CmDictIcd> listByItemName(CmDictIcd cmDictIcd);

    /**
     * 保存
     */
    public void save(CmDiagnosisEntityPO diagnosisEntity, HashMap<String, Object> map);

    /**
     * 根据患者id查询icd
     * 
     * @Title: listByfkPatientId
     * @param fkPatientId
     * @return
     *
     */
    public List<CmDiagnosisIcdPO> listByfkPatientId(Long fkPatientId);

    /**
     * 根据id查询icd字典
     */

    public List<CmDictIcd> getById(CmDiagnosisIcdPO record);

    /**
     * 根据id删除
     * 
     * @Title: deleteByPrimaryKey
     * @param record
     * @return
     *
     */
    public int deleteByPrimaryKey(CmDiagnosisIcdPO record);
}
