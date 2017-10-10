package com.xtt.common.common.service;

import java.util.List;

import com.xtt.common.dao.model.PatientOrders;
import com.xtt.common.dao.model.SysDbSource;
import com.xtt.common.dao.po.CmPatientPO;
import com.xtt.common.dao.po.CmQueryPO;
import com.xtt.common.dao.po.SysDbSourcePO;

/**
 * 
 * @ClassName: IDictionaryService
 * @date: 2015年9月9日 上午9:16:10
 * @version: V1.0
 * 
 */
public interface ISysDbSourceService {

    int deleteByPrimaryKey(Long id);

    int insert(SysDbSource record);

    int insertSelective(SysDbSource record);

    SysDbSource selectByPrimaryKey(Long id);

    SysDbSource selectByDbKey(String parm);

    int updateByPrimaryKeySelective(SysDbSource record);

    int updateByPrimaryKey(SysDbSource record);

    SysDbSourcePO selectByDbKeyPO(String key);

    List<SysDbSourcePO> selectByDbKeyPPO(String key);

    List<SysDbSourcePO> selectBySysDbSourcePO(SysDbSourcePO po);

    /**
     * 发送医嘱状态
     * 
     * @Title: sendOrdersStatus
     * @param list
     * @param sysOwner
     *            所属系统
     * @return
     * 
     */
    String sendOrdersStatus(List<PatientOrders> list, String sysOwner);

    /**
     * 同步费用医嘱
     * 
     * @Title: sendQueryOrderInfo
     * @param query
     *            传入3个必填参数(fkPatientId="",startDate='2016-04-01',endDate='2016-04-02'),tenantId=""可选
     * @return
     * 
     */
    String sendQueryOrderInfo(CmQueryPO query);

    /**
     * 根据选择条件下载指定的数据
     * 
     * @Title: downDB
     * @param query
     * @return
     * 
     */
    public String downDB(CmQueryPO query);

    /**
     * 根据条件获取病患基本资料
     * 
     * @Title: downDB
     * @param query
     * @return
     * 
     */
    public CmPatientPO patientDB(CmQueryPO query) throws Exception;

}
