/**   
 * @Title: ReportWorkbookEntity.java 
 * @Package com.xtt.txgl.report.util.entity
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年6月14日 下午3:41:10 
 *
 */
package com.xtt.common.util.report.entity;

public class ReportWorkbookEntity {
    private ReportSheetEntity[] sheetEntitys;// sheet集合

    public ReportWorkbookEntity() {

    }

    public ReportWorkbookEntity(ReportSheetEntity[] sheetEntitys) {
        this.sheetEntitys = sheetEntitys;
    }

    public ReportSheetEntity[] getSheetEntitys() {
        return sheetEntitys;
    }

    public void setSheetEntitys(ReportSheetEntity[] sheetEntitys) {
        this.sheetEntitys = sheetEntitys;
    }

}
