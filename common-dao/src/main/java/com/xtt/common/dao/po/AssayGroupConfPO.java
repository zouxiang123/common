/**   
 * @Title: AssayGroupConfPO.java 
 * @Package com.xtt.hd.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年7月18日 上午9:46:43 
 *
 */
package com.xtt.common.dao.po;

import java.util.List;

import com.xtt.common.dao.model.AssayGroupConf;
import com.xtt.common.dao.model.AssayGroupConfDetail;

/**
 * @ClassName: AssayGroupConfPO
 * @date: 2016年7月18日 上午9:46:43
 * @version: V1.0
 *
 */
public class AssayGroupConfPO extends AssayGroupConf {

    private List<AssayGroupConfDetail> details;

    public List<AssayGroupConfDetail> getDetails() {
        return details;
    }

    public void setDetails(List<AssayGroupConfDetail> details) {
        this.details = details;
    }
}
