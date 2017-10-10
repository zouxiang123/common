package com.xtt.common.assay.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.service.IPatientAssayConfService;
import com.xtt.common.dao.mapper.PatientAssayConfMapper;
import com.xtt.common.dao.model.PatientAssayConf;
import com.xtt.common.dao.po.PatientAssayConfPO;
import com.xtt.common.util.CalendarUtil;
import com.xtt.platform.util.time.DateFormatUtil;

@Service
public class PatientAssayConfServiceImpl implements IPatientAssayConfService {

    @Autowired
    private PatientAssayConfMapper patientAssayConfMapper;

    /**
     * 查询化验配置
     */
    @Override
    public PatientAssayConf selectByTenantId(Integer tenantId) {
        PatientAssayConf patientAssayConf = patientAssayConfMapper.selectByTenantId(tenantId);
        if (patientAssayConf == null) {
            return new PatientAssayConf();
        }
        return patientAssayConf;

    }

    /**
     * 修改时间配置
     */
    @Override
    public void update(PatientAssayConf patientAssayConf) {
        patientAssayConfMapper.updateByPrimaryKeySelective(patientAssayConf);

    }

    /**
     * 新增
     */
    @Override
    public void insert(PatientAssayConf patientAssayConf) {

        patientAssayConfMapper.insert(patientAssayConf);
    }

    @Override
    public PatientAssayConfPO selectDateScopeByMonth(String monthAndYear, Integer tenantId) {
        PatientAssayConf patientAssayConf = patientAssayConfMapper.selectByTenantId(tenantId);
        Date month = DateFormatUtil.convertStrToDate(monthAndYear, DateFormatUtil.FORMAT_YYYY_MM);
        Date endDate = getEndDate(month, Integer.parseInt(patientAssayConf.getEndDate()));
        Date startDate = getStartDate(endDate);

        PatientAssayConfPO po = new PatientAssayConfPO();
        po.setStartDate(startDate);
        po.setEndDate(endDate);

        return po;
    }

    private Date getStartDate(Date endDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(endDate);
        if (cal.get(Calendar.DAY_OF_MONTH) == cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {// 如果结束如期是月底
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));// set start to zhe first day of month
        } else {
            cal.add(Calendar.MONTH, -1);
            cal.add(Calendar.DATE, 1);
        }
        return cal.getTime();
    }

    private Date getEndDate(Date month, int endDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(month);

        if (endDate >= 28) {
            endDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        cal.set(Calendar.DAY_OF_MONTH, endDate);
        return cal.getTime();
    }

    @Override
    public PatientAssayConfPO selectDateScopeByDate(Date date, Integer tenantId) {
        String mongthAndYear = selectMonthAndYearByDate(date, tenantId, null);
        return selectDateScopeByMonth(mongthAndYear, tenantId);
    }

    @Override
    public String selectMonthAndYearByDate(Date date, Integer tenantId, PatientAssayConf conf) {
        if (conf == null) {
            conf = patientAssayConfMapper.selectByTenantId(tenantId);
        }
        if (conf == null) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int selectDate = cal.get(Calendar.DAY_OF_MONTH);// 选择的日期
        int endDate = Integer.parseInt(conf.getEndDate());// 化验报表截止日

        // 如果当前日期大于化验报表截止日期，则当前日期属于下个月的统计
        int month = CalendarUtil.getMonth(cal);
        if (selectDate > endDate) {
            month += 1;
        }
        CalendarUtil.setMonth(cal, month);
        String mongthAndYear = DateFormatUtil.convertDateToStr(cal.getTime(), DateFormatUtil.FORMAT_YYYY_MM);
        return mongthAndYear;
    }

    public static void main(String[] args) {
        String monthAndYear = "2016-02";
        int endDateInt = 31;
        Date month = DateFormatUtil.convertStrToDate(monthAndYear, DateFormatUtil.FORMAT_YYYY_MM);
        Calendar cal = Calendar.getInstance();
        cal.setTime(month);

        int maxDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (maxDate < endDateInt) {
            endDateInt = maxDate;
        }

        cal.set(Calendar.DAY_OF_MONTH, endDateInt);

        Date endDate = DateFormatUtil.getEndTime(cal.getTime());

        cal.add(Calendar.MONTH, -1);
        cal.add(Calendar.DATE, 1);
        Date startDate = DateFormatUtil.getStartTime(cal.getTime());
        System.out.println(DateFormatUtil.convertDateToStr(startDate));
        System.out.println(DateFormatUtil.convertDateToStr(endDate));
    }
}
