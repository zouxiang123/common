/**   
 * @Title: ReportSheetEntity.java 
 * @Package com.xtt.txgl.report.util.entity
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年6月14日 下午3:44:05 
 *
 */
package com.xtt.common.util.report.entity;

import java.util.List;
import java.util.Set;

public class ReportSheetEntity {

    private String sheetName;// sheet名称
    private String title;// 标题
    private String[] headNames;// 表单头数组
    private String[] columnNames;// 标题属性名称
    private List<?> contents;// 内容集合
    private Class clazz;// 当前操作类
    private String[] totals;// 求和
    private int rowNum;// 行号

    private boolean hasRowNo;// 是否需要序号，默认不需要
    private Set<String> mergeColumnNames;// 合并列
    private String[] footValues;// 表底内容

    public ReportSheetEntity() {

    }

    public ReportSheetEntity(String sheetName, String title, String[] headNames, String[] columnNames, List<?> contents, Class clazz) {
        this.sheetName = sheetName;
        this.title = title;
        this.headNames = headNames;
        this.columnNames = columnNames;
        this.contents = contents;
        this.clazz = clazz;
    }

    // 行号自增
    public int incrementRow() {
        return rowNum++;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getHeadNames() {
        return headNames;
    }

    public void setHeadNames(String[] headNames) {
        this.headNames = headNames;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    public List<?> getContents() {
        return contents;
    }

    public void setContents(List<?> contents) {
        this.contents = contents;
    }

    public String[] getTotals() {
        return totals;
    }

    public void setTotals(String[] totals) {
        this.totals = totals;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public boolean isHasRowNo() {
        return hasRowNo;
    }

    public void setHasRowNo(boolean hasRowNo) {
        this.hasRowNo = hasRowNo;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Set<String> getMergeColumnNames() {
        return mergeColumnNames;
    }

    public void setMergeColumnNames(Set<String> mergeColumnNames) {
        this.mergeColumnNames = mergeColumnNames;
    }

    public String[] getFootValues() {
        return footValues;
    }

    public void setFootValues(String[] footValues) {
        this.footValues = footValues;
    }

}
