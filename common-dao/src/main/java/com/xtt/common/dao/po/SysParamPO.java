/**   
 * @Title: SysParamPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年1月4日 下午5:38:52 
 *
 */
package com.xtt.common.dao.po;

import java.util.List;

import com.xtt.common.dao.model.SysParam;
import com.xtt.common.dto.DictDto;

public class SysParamPO extends SysParam {
    private List<DictDto> dicUnitList;// 单位对应的字典表数据

    private String[] sysOwners;

    public List<DictDto> getDicUnitList() {
        return dicUnitList;
    }

    public void setDicUnitList(List<DictDto> dicUnitList) {
        this.dicUnitList = dicUnitList;
    }

    public String[] getSysOwners() {
        return sysOwners;
    }

    public void setSysOwners(String[] sysOwners) {
        this.sysOwners = sysOwners;
    }

}
