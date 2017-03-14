/**   
 * @Title: ICommonService.java 
 * @Package com.xtt.common.common.service
 * Copyright: Copyright (c) 2015
 * @author: Tik   
 * @date: 2015年9月16日 上午11:50:43 
 *
 */
package com.xtt.common.common.service;

import java.util.List;

import com.xtt.common.dao.model.County;
import com.xtt.common.dao.model.Feedback;
import com.xtt.common.dao.model.Province;

/**
 * @ClassName: ICommonService
 * @date: 2015年9月16日 上午11:50:43
 * @version: V1.0
 * 
 */
public interface ICommonService {

    /**
     * 保存意见反馈
     * 
     * @Title: saveFeedback
     * @param feedback
     * @return
     *
     */
    public int saveFeedback(Feedback feedback);

    /**
     * 获取所有省份
     * 
     * @Title: getProvinceList
     * @return
     * 
     */
    public List<Province> getProvinceList();

    /**
     * 获取指定省份下所有的县/区
     * 
     * @Title: getCountyList
     * @param provinceId
     * @return
     * 
     */
    public List<County> getCountyList(Integer provinceId);
}
