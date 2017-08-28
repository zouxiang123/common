/**   
 * @Title: PrintParam.java 
 * @Package com.xtt.print.model
 * Copyright: Copyright (c) 2015
 * @author: WT   
 * @date: 2017年7月19日 下午2:06:54 
 *
 */
package com.xtt.common.dto;

public class PrintParamDto {
    // 日期
    private String date;
    // 姓名
    private String name;
    // 性别
    private String sex;
    // 年龄
    private String age;
    // 病案号
    private String diseasenum;
    // 执行科室
    private String department;
    // 医嘱内容
    private String content;
    // 身份证
    private String idNumber;
    // 布局类型
    private String layoutType;
    // 医院名称
    private String tenantName;
    // 治疗地点
    private String hdAddress;
    // 科室电话
    private String officeNum;
    // 科室电话2
    private String officeNum2;
    // 患者联系方式
    private String patientContact;

    public PrintParamDto() {
    }

    public PrintParamDto(String date, String name, String sex, String age, String diseasenum, String department, String content, String idNumber,
                    String layoutType, String tenantName, String hdAddress, String officeNum, String officeNum2, String patientContact) {
        super();
        this.date = date;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.diseasenum = diseasenum;
        this.department = department;
        this.content = content;
        this.idNumber = idNumber;
        this.layoutType = layoutType;
        this.tenantName = tenantName;
        this.hdAddress = hdAddress;
        this.officeNum = officeNum;
        this.officeNum2 = officeNum2;
        this.patientContact = patientContact;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getHdAddress() {
        return hdAddress;
    }

    public void setHdAddress(String hdAddress) {
        this.hdAddress = hdAddress;
    }

    public String getOfficeNum() {
        return officeNum;
    }

    public void setOfficeNum(String officeNum) {
        this.officeNum = officeNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiseasenum() {
        return diseasenum;
    }

    public void setDiseasenum(String diseasenum) {
        this.diseasenum = diseasenum;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(String layoutType) {
        this.layoutType = layoutType;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getOfficeNum2() {
        return officeNum2;
    }

    public void setOfficeNum2(String officeNum2) {
        this.officeNum2 = officeNum2;
    }

    public String getPatientContact() {
        return patientContact;
    }

    public void setPatientContact(String patientContact) {
        this.patientContact = patientContact;
    }

    @Override
    public String toString() {
        return "PrintParam [date=" + date + ", name=" + name + ", sex=" + sex + ", age=" + age + ", diseasenum=" + diseasenum + ", department="
                        + department + ", content=" + content + ", idNumber=" + idNumber + ", layoutType=" + layoutType + "]";
    }

}
