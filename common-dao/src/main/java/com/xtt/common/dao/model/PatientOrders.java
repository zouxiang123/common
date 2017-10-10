package com.xtt.common.dao.model;

import java.util.Date;

import com.xtt.platform.framework.core.model.MyBatisSuperModel;

/**
 * patient_orders
 */
public class PatientOrders extends MyBatisSuperModel {
    /**
     * patient_orders.id
     */
    private Long id;

    /**
     * 血透病患ID patient_orders.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 病人ID patient_orders.pt_id
     */
    private String ptId;

    /**
     * 病患类型(2=住院1=门诊） patient_orders.pt_type
     */
    private String ptType;

    /**
     * patient_orders.card_no
     */
    private String cardNo;

    /**
     * 就诊次数 patient_orders.times
     */
    private String times;

    /**
     * 结账次数 patient_orders.receipt_times
     */
    private String receiptTimes;

    /**
     * 本医嘱是否长期医嘱，使用代码，1-长期，0-临时 patient_orders.order_flag
     */
    private String orderFlag;

    /**
     * 医嘱流水号 patient_orders.order_id
     */
    private String orderId;

    /**
     * 医嘱编号 patient_orders.serial_no
     */
    private String serialNo;

    /**
     * 医嘱序号 patient_orders.order_no
     */
    private String orderNo;

    /**
     * 医嘱子序号 patient_orders.order_sub_no
     */
    private String orderSubNo;

    /**
     * 开医嘱时间 patient_orders.enter_time
     */
    private Date enterTime;

    /**
     * 停止时间 patient_orders.end_time
     */
    private Date endTime;

    /**
     * 医嘱类别 patient_orders.order_class
     */
    private String orderClass;

    /**
     * 项目编码 patient_orders.item_code
     */
    private String itemCode;

    /**
     * 项目名称 patient_orders.item_name
     */
    private String itemName;

    /**
     * 剂量 patient_orders.dosage
     */
    private String dosage;

    /**
     * 剂量单位 patient_orders.dosage_unit
     */
    private String dosageUnit;

    /**
     * 规格 patient_orders.specifications
     */
    private String specifications;

    /**
     * 领量 patient_orders.order_num
     */
    private Double orderNum;

    /**
     * 领量单位 patient_orders.num_unit
     */
    private String numUnit;

    /**
     * 单价 patient_orders.price
     */
    private Double price;

    /**
     * 计价金额 patient_orders.costs
     */
    private Double costs;

    /**
     * 单据号 patient_orders.charges_code
     */
    private String chargesCode;

    /**
     * 实收费用 patient_orders.charges_atm
     */
    private Double chargesAtm;

    /**
     * patient_orders.charges_date
     */
    private Date chargesDate;

    /**
     * 用法编码 patient_orders.usage_code
     */
    private String usageCode;

    /**
     * 用法名称 patient_orders.usage_name
     */
    private String usageName;

    /**
     * 执行频率编码 patient_orders.frequency_code
     */
    private String frequencyCode;

    /**
     * 执行频率描述 patient_orders.frequency_desc
     */
    private String frequencyDesc;

    /**
     * 频率次数 patient_orders.frequency_count
     */
    private String frequencyCount;

    /**
     * 频率间隔 patient_orders.frequency_interval
     */
    private String frequencyInterval;

    /**
     * 频率间隔单位 patient_orders.frequency_interval_unit
     */
    private String frequencyIntervalUnit;

    /**
     * 取药标志 patient_orders.getdrug_flag
     */
    private String getdrugFlag;

    /**
     * 自备标记 patient_orders.provided_indicator
     */
    private String providedIndicator;

    /**
     * 执行时间详细描述 patient_orders.exec_time_desc
     */
    private String execTimeDesc;

    /**
     * 开单科室编码 patient_orders.dept_code
     */
    private String deptCode;

    /**
     * 开单科室 patient_orders.dept_name
     */
    private String deptName;

    /**
     * 开医嘱医生编码 patient_orders.doctor_code
     */
    private String doctorCode;

    /**
     * 开医嘱医生 patient_orders.doctor_name
     */
    private String doctorName;

    /**
     * patient_orders.exec_dept_code
     */
    private String execDeptCode;

    /**
     * 执行科室 patient_orders.exec_dept_name
     */
    private String execDeptName;

    /**
     * 校对护士 patient_orders.nurse
     */
    private String nurse;

    /**
     * 医嘱状态。已保存:0,已确认:1,已执行:2,已取消:3,已作废:4,已退费:5, 已停止:6 patient_orders.order_statues
     */
    private String orderStatues;

    /**
     * 反映本条医嘱计价方面的信息。0-正常计价 1-自带药 2-需手工计价 3-不计价。由护士录入医嘱时，根据医嘱内容确定。 patient_orders.billing_attr
     */
    private String billingAttr;

    /**
     * 0-无需皮试，1-续皮试，2-皮试 patient_orders.ps_Flag
     */
    private String psFlag;

    /**
     * 0-无需审批，1-需审批 patient_orders.yb_flag
     */
    private String ybFlag;

    /**
     * patient_orders.pay_flag
     */
    private String payFlag;

    /**
     * 0成药，1草药 patient_orders.presc_type
     */
    private String prescType;

    /**
     * 由医保校对接口平台返回 patient_orders.uniqueCode
     */
    private String uniquecode;

    /**
     * 药房 patient_orders.pharmacy
     */
    private String pharmacy;

    /**
     * 由医保校对平台返回（甲类，乙类，丙类） patient_orders.officialLevel
     */
    private String officiallevel;

    /**
     * 表示该医嘱是否已打印医嘱本，0-未打印，1-已打印 patient_orders.order_print_indicator
     */
    private String orderPrintIndicator;

    /**
     * patient_orders.diag_code
     */
    private String diagCode;

    /**
     * patient_orders.diag_name
     */
    private String diagName;

    /**
     * patient_orders.del_flag
     */
    private Boolean delFlag;

    /**
     * 创建时间 patient_orders.create_time
     */
    private Date createTime;

    /**
     * 创建人 patient_orders.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间 patient_orders.update_time
     */
    private Date updateTime;

    /**
     * 更新人 patient_orders.update_user_id
     */
    private Long updateUserId;

    /**
     * 租户ID patient_orders.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     */
    public Long getId() {
        return id;
    }

    /**
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 血透病患ID
     */
    public Long getFkPatientId() {
        return fkPatientId;
    }

    /**
     * 血透病患ID
     */
    public void setFkPatientId(Long fkPatientId) {
        this.fkPatientId = fkPatientId;
    }

    /**
     * 病人ID
     */
    public String getPtId() {
        return ptId;
    }

    /**
     * 病人ID
     */
    public void setPtId(String ptId) {
        this.ptId = ptId;
    }

    /**
     * 病患类型(2=住院1=门诊）
     */
    public String getPtType() {
        return ptType;
    }

    /**
     * 病患类型(2=住院1=门诊）
     */
    public void setPtType(String ptType) {
        this.ptType = ptType;
    }

    /**
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * 就诊次数
     */
    public String getTimes() {
        return times;
    }

    /**
     * 就诊次数
     */
    public void setTimes(String times) {
        this.times = times;
    }

    /**
     * 结账次数
     */
    public String getReceiptTimes() {
        return receiptTimes;
    }

    /**
     * 结账次数
     */
    public void setReceiptTimes(String receiptTimes) {
        this.receiptTimes = receiptTimes;
    }

    /**
     * 本医嘱是否长期医嘱，使用代码，1-长期，0-临时
     */
    public String getOrderFlag() {
        return orderFlag;
    }

    /**
     * 本医嘱是否长期医嘱，使用代码，1-长期，0-临时
     */
    public void setOrderFlag(String orderFlag) {
        this.orderFlag = orderFlag;
    }

    /**
     * 医嘱流水号
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 医嘱流水号
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 医嘱编号
     */
    public String getSerialNo() {
        return serialNo;
    }

    /**
     * 医嘱编号
     */
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    /**
     * 医嘱序号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 医嘱序号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 医嘱子序号
     */
    public String getOrderSubNo() {
        return orderSubNo;
    }

    /**
     * 医嘱子序号
     */
    public void setOrderSubNo(String orderSubNo) {
        this.orderSubNo = orderSubNo;
    }

    /**
     * 开医嘱时间
     */
    public Date getEnterTime() {
        return enterTime;
    }

    /**
     * 开医嘱时间
     */
    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }

    /**
     * 停止时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 停止时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 医嘱类别
     */
    public String getOrderClass() {
        return orderClass;
    }

    /**
     * 医嘱类别
     */
    public void setOrderClass(String orderClass) {
        this.orderClass = orderClass;
    }

    /**
     * 项目编码
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 项目编码
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * 项目名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 项目名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 剂量
     */
    public String getDosage() {
        return dosage;
    }

    /**
     * 剂量
     */
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    /**
     * 剂量单位
     */
    public String getDosageUnit() {
        return dosageUnit;
    }

    /**
     * 剂量单位
     */
    public void setDosageUnit(String dosageUnit) {
        this.dosageUnit = dosageUnit;
    }

    /**
     * 规格
     */
    public String getSpecifications() {
        return specifications;
    }

    /**
     * 规格
     */
    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    /**
     * 领量
     */
    public Double getOrderNum() {
        return orderNum;
    }

    /**
     * 领量
     */
    public void setOrderNum(Double orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 领量单位
     */
    public String getNumUnit() {
        return numUnit;
    }

    /**
     * 领量单位
     */
    public void setNumUnit(String numUnit) {
        this.numUnit = numUnit;
    }

    /**
     * 单价
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 单价
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 计价金额
     */
    public Double getCosts() {
        return costs;
    }

    /**
     * 计价金额
     */
    public void setCosts(Double costs) {
        this.costs = costs;
    }

    /**
     * 单据号
     */
    public String getChargesCode() {
        return chargesCode;
    }

    /**
     * 单据号
     */
    public void setChargesCode(String chargesCode) {
        this.chargesCode = chargesCode;
    }

    /**
     * 实收费用
     */
    public Double getChargesAtm() {
        return chargesAtm;
    }

    /**
     * 实收费用
     */
    public void setChargesAtm(Double chargesAtm) {
        this.chargesAtm = chargesAtm;
    }

    /**
     */
    public Date getChargesDate() {
        return chargesDate;
    }

    /**
     */
    public void setChargesDate(Date chargesDate) {
        this.chargesDate = chargesDate;
    }

    /**
     * 用法编码
     */
    public String getUsageCode() {
        return usageCode;
    }

    /**
     * 用法编码
     */
    public void setUsageCode(String usageCode) {
        this.usageCode = usageCode;
    }

    /**
     * 用法名称
     */
    public String getUsageName() {
        return usageName;
    }

    /**
     * 用法名称
     */
    public void setUsageName(String usageName) {
        this.usageName = usageName;
    }

    /**
     * 执行频率编码
     */
    public String getFrequencyCode() {
        return frequencyCode;
    }

    /**
     * 执行频率编码
     */
    public void setFrequencyCode(String frequencyCode) {
        this.frequencyCode = frequencyCode;
    }

    /**
     * 执行频率描述
     */
    public String getFrequencyDesc() {
        return frequencyDesc;
    }

    /**
     * 执行频率描述
     */
    public void setFrequencyDesc(String frequencyDesc) {
        this.frequencyDesc = frequencyDesc;
    }

    /**
     * 频率次数
     */
    public String getFrequencyCount() {
        return frequencyCount;
    }

    /**
     * 频率次数
     */
    public void setFrequencyCount(String frequencyCount) {
        this.frequencyCount = frequencyCount;
    }

    /**
     * 频率间隔
     */
    public String getFrequencyInterval() {
        return frequencyInterval;
    }

    /**
     * 频率间隔
     */
    public void setFrequencyInterval(String frequencyInterval) {
        this.frequencyInterval = frequencyInterval;
    }

    /**
     * 频率间隔单位
     */
    public String getFrequencyIntervalUnit() {
        return frequencyIntervalUnit;
    }

    /**
     * 频率间隔单位
     */
    public void setFrequencyIntervalUnit(String frequencyIntervalUnit) {
        this.frequencyIntervalUnit = frequencyIntervalUnit;
    }

    /**
     * 取药标志
     */
    public String getGetdrugFlag() {
        return getdrugFlag;
    }

    /**
     * 取药标志
     */
    public void setGetdrugFlag(String getdrugFlag) {
        this.getdrugFlag = getdrugFlag;
    }

    /**
     * 自备标记
     */
    public String getProvidedIndicator() {
        return providedIndicator;
    }

    /**
     * 自备标记
     */
    public void setProvidedIndicator(String providedIndicator) {
        this.providedIndicator = providedIndicator;
    }

    /**
     * 执行时间详细描述
     */
    public String getExecTimeDesc() {
        return execTimeDesc;
    }

    /**
     * 执行时间详细描述
     */
    public void setExecTimeDesc(String execTimeDesc) {
        this.execTimeDesc = execTimeDesc;
    }

    /**
     * 开单科室编码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 开单科室编码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    /**
     * 开单科室
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * 开单科室
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * 开医嘱医生编码
     */
    public String getDoctorCode() {
        return doctorCode;
    }

    /**
     * 开医嘱医生编码
     */
    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    /**
     * 开医嘱医生
     */
    public String getDoctorName() {
        return doctorName;
    }

    /**
     * 开医嘱医生
     */
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    /**
     */
    public String getExecDeptCode() {
        return execDeptCode;
    }

    /**
     */
    public void setExecDeptCode(String execDeptCode) {
        this.execDeptCode = execDeptCode;
    }

    /**
     * 执行科室
     */
    public String getExecDeptName() {
        return execDeptName;
    }

    /**
     * 执行科室
     */
    public void setExecDeptName(String execDeptName) {
        this.execDeptName = execDeptName;
    }

    /**
     * 校对护士
     */
    public String getNurse() {
        return nurse;
    }

    /**
     * 校对护士
     */
    public void setNurse(String nurse) {
        this.nurse = nurse;
    }

    /**
     * 医嘱状态。已保存:0,已确认:1,已执行:2,已取消:3,已作废:4,已退费:5, 已停止:6
     */
    public String getOrderStatues() {
        return orderStatues;
    }

    /**
     * 医嘱状态。已保存:0,已确认:1,已执行:2,已取消:3,已作废:4,已退费:5, 已停止:6
     */
    public void setOrderStatues(String orderStatues) {
        this.orderStatues = orderStatues;
    }

    /**
     * 反映本条医嘱计价方面的信息。0-正常计价 1-自带药 2-需手工计价 3-不计价。由护士录入医嘱时，根据医嘱内容确定。
     */
    public String getBillingAttr() {
        return billingAttr;
    }

    /**
     * 反映本条医嘱计价方面的信息。0-正常计价 1-自带药 2-需手工计价 3-不计价。由护士录入医嘱时，根据医嘱内容确定。
     */
    public void setBillingAttr(String billingAttr) {
        this.billingAttr = billingAttr;
    }

    /**
     * 0-无需皮试，1-续皮试，2-皮试
     */
    public String getPsFlag() {
        return psFlag;
    }

    /**
     * 0-无需皮试，1-续皮试，2-皮试
     */
    public void setPsFlag(String psFlag) {
        this.psFlag = psFlag;
    }

    /**
     * 0-无需审批，1-需审批
     */
    public String getYbFlag() {
        return ybFlag;
    }

    /**
     * 0-无需审批，1-需审批
     */
    public void setYbFlag(String ybFlag) {
        this.ybFlag = ybFlag;
    }

    /**
     */
    public String getPayFlag() {
        return payFlag;
    }

    /**
     */
    public void setPayFlag(String payFlag) {
        this.payFlag = payFlag;
    }

    /**
     * 0成药，1草药
     */
    public String getPrescType() {
        return prescType;
    }

    /**
     * 0成药，1草药
     */
    public void setPrescType(String prescType) {
        this.prescType = prescType;
    }

    /**
     * 由医保校对接口平台返回
     */
    public String getUniquecode() {
        return uniquecode;
    }

    /**
     * 由医保校对接口平台返回
     */
    public void setUniquecode(String uniquecode) {
        this.uniquecode = uniquecode;
    }

    /**
     * 药房
     */
    public String getPharmacy() {
        return pharmacy;
    }

    /**
     * 药房
     */
    public void setPharmacy(String pharmacy) {
        this.pharmacy = pharmacy;
    }

    /**
     * 由医保校对平台返回（甲类，乙类，丙类）
     */
    public String getOfficiallevel() {
        return officiallevel;
    }

    /**
     * 由医保校对平台返回（甲类，乙类，丙类）
     */
    public void setOfficiallevel(String officiallevel) {
        this.officiallevel = officiallevel;
    }

    /**
     * 表示该医嘱是否已打印医嘱本，0-未打印，1-已打印
     */
    public String getOrderPrintIndicator() {
        return orderPrintIndicator;
    }

    /**
     * 表示该医嘱是否已打印医嘱本，0-未打印，1-已打印
     */
    public void setOrderPrintIndicator(String orderPrintIndicator) {
        this.orderPrintIndicator = orderPrintIndicator;
    }

    /**
     */
    public String getDiagCode() {
        return diagCode;
    }

    /**
     */
    public void setDiagCode(String diagCode) {
        this.diagCode = diagCode;
    }

    /**
     */
    public String getDiagName() {
        return diagName;
    }

    /**
     */
    public void setDiagName(String diagName) {
        this.diagName = diagName;
    }

    /**
     */
    public Boolean getDelFlag() {
        return delFlag;
    }

    /**
     */
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建人
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 创建人
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 更新人
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 更新人
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 租户ID
     */
    public Integer getFkTenantId() {
        return fkTenantId;
    }

    /**
     * 租户ID
     */
    public void setFkTenantId(Integer fkTenantId) {
        this.fkTenantId = fkTenantId;
    }
}