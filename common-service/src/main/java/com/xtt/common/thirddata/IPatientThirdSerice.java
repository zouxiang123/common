/**   
 * @Title: IPatientThirdSerice.java 
 * @Package com.xtt.txgl.third
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年1月12日 下午4:27:58 
 *
 */
package com.xtt.common.thirddata;

public interface IPatientThirdSerice {
    /**
     * 调用接口服务
     * 
     * @Title: updatePatientType
     * @param patientId
     *            患者id
     * @param sysOwner
     *
     */
    void callInterfacePro(Long patientId, String downType, String sysOwner);
}
