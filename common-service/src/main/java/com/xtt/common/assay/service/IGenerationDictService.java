package com.xtt.common.assay.service;

import com.xtt.common.dao.po.CmQueryPO;

public interface IGenerationDictService {

    /**
     * 自动生成检验字典
     * 
     * @Title: downLis
     * @param ptId
     * @param startDate
     * @param endDate
     * 
     */
    public String lisDictService(CmQueryPO po);

    /**
     * 自动生成医嘱项目字典
     * 
     * @Title: downLis
     * @param ptId
     * @param startDate
     * @param endDate
     * 
     */
    public String ordersDictService(CmQueryPO po);

    /**
     * 构建（全国质控）所需要的检验数据
     * 
     * @Title: downLis
     * @param ptId
     * @param startDate
     * @param endDate
     * 
     */
    public void buildZkLabItems(CmQueryPO po);
}