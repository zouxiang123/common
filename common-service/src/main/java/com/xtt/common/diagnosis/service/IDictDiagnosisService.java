/**   
 * @Title: IFollowUpConfSerivce.java 
 * @Package com.xtt.pd.system.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年6月28日 下午4:55:59 
 *
 */
package com.xtt.common.diagnosis.service;

import java.util.List;

import com.xtt.common.dao.model.CmDictDiagnosis;
import com.xtt.common.dao.po.CmDictDiagnosisPO;

public interface IDictDiagnosisService {

    /**
     * 获取所有的诊断所有项目
     * 
     * @Title: selectAll
     * @return
     *
     */
    List<CmDictDiagnosisPO> selectAll();

    /**
     * 根据条件查询数据
     * 
     * @Title: selectByCondition
     * @param record
     * @return
     *
     */
    List<CmDictDiagnosisPO> selectByCondition(CmDictDiagnosisPO record);

    /** 根据item编号查询数据 */
    CmDictDiagnosisPO selectByItemCode(String itemCode);

    /**
     * 保存item数据
     * 
     * @Title: saveItem
     * @param record
     * @return status
     *
     */
    String saveItem(CmDictDiagnosis record);

    /**
     * 根据itemcode删除节点
     * 
     * @Title: deleteByItemCode
     * @param itemCode
     *
     */
    String deleteByItemCode(String itemCode);

    /**
     * 根据指定父节点获取节点下的树结构，如果获取所有pItemCode参数为null即可
     * 
     * @Title: selectTreeList
     * @param pItemCode
     * @return
     *
     */
    List<CmDictDiagnosisPO> selectTreeList(String pItemCode);
}
