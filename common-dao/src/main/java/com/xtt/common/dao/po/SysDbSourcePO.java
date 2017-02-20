package com.xtt.common.dao.po;

import com.xtt.common.dao.model.SysDbSource;

public class SysDbSourcePO extends SysDbSource {

    private String ptId; // 病患ID
    private String startDate; // 开始日期
    private String endDate; // 结束日期

    /**
     * 此构造方法需要存在否则查询实例化这个对象会出错
     * 
     * @param: @return:
     *             SysDbSourcePO @throws
     * 
     */
    public SysDbSourcePO() {

    }

    /**
     * 程序动态加入查询参数
     * 
     * @param: @param
     *             ptId @param: @param startDate @param: @param endDate @return: SysDbSourcePO @throws
     * 
     */
    public SysDbSourcePO(String ptId, String startDate, String endDate) {
        this.ptId = ptId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getPtId() {
        return ptId;
    }

    public void setPtId(String ptId) {
        this.ptId = ptId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}
