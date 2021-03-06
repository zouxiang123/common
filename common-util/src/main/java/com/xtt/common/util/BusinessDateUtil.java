/**   
 * @Title: BusinessDateUtil.java 
 * @Package com.xtt.txgl.common.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年12月1日 下午8:22:06 
 *
 */
package com.xtt.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.xtt.platform.util.time.DateUtil;

public class BusinessDateUtil {

    /**
     * 获取time最近一个月的时间
     * 
     * @Title: getNearestMonth
     * @param time
     * @return 前一个月的时间的后一天零点 例如 time 为 2015-02-01 返回 2015-01-02 00:00:00
     *
     */
    public static Date getNearestMonth(Date time) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.add(Calendar.MONTH, -1);
        c.add(Calendar.DAY_OF_MONTH, 1);
        return getStartOrEnd(c, true);
    }

    /**
     * 获取time最近一年的时间
     * 
     * @Title: getNearestYear
     * @param time
     * @return 前一年的时间的后一天零点 例如 time 为 2015-02-01 返回 2014-02-02 00:00:00
     *
     */
    public static Date getNearestYear(Date time) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.add(Calendar.YEAR, -1);
        c.add(Calendar.DAY_OF_YEAR, 1);
        return getStartOrEnd(c, true);
    }

    /**
     * 获取time最近七天的时间
     * 
     * @Title: getNearestWeek
     * @param time
     * @return 上周的时间的后一天零点 例如 time 为 周二 返回 上周三的 00:00:00
     *
     */
    public static Date getNearestWeek(Date time) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.add(Calendar.DAY_OF_WEEK, -6);
        // calendar.add(Calendar.DAY_OF_WEEK, 1);
        return getStartOrEnd(c, true);
    }

    /**
     * 获取最近一周、最近两周....的开始时间
     * 
     * @Title: getNearestWeek
     * @param time
     * @param week
     * @return 第n周的时间的后一天零点 例如 time 为 周二 返回 上周三的 00:00:00
     *
     */
    public static Date getNearesOthersfWeek(Date time, Integer week) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        Integer day = 6 * week;
        c.add(Calendar.DAY_OF_WEEK, -day);
        return getStartOrEnd(c, true);
    }

    /**
     * 获取time零点/23:59:59
     * 
     * @Title: getNearestMonth
     * @param time
     * @return 零点/或者23:59:59
     *
     */
    public static Date getDayStartOrEnd(Date time, boolean isDayStart) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        return getStartOrEnd(c, isDayStart);
    }

    /**
     * 获取date的上个季度的开始时间/结束时间
     * 
     * @Title: getPreQuarterStartOrEnd
     * @param isQuarterStart是否是季度开始时间
     * @return
     *
     */
    public static Date getPreQuarterStartOrEnd(Date date, boolean isQuarterStart) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MONTH, BusinessDateUtil.getQuarterInMonth(c.get(Calendar.MONTH), true));
        c.add(Calendar.MONTH, -1);
        if (isQuarterStart) {
            c.set(Calendar.MONTH, BusinessDateUtil.getQuarterInMonth(c.get(Calendar.MONTH), isQuarterStart));
            c.set(Calendar.DAY_OF_MONTH, 1);
        } else {
            c.set(Calendar.MONTH, BusinessDateUtil.getQuarterInMonth(c.get(Calendar.MONTH), isQuarterStart) + 1);
            c.set(Calendar.DAY_OF_MONTH, 0);
        }
        return getStartOrEnd(c, isQuarterStart);
    }

    /**
     * 获取date的上个月份开始时间/结束时间
     * 
     * @Title: getPreMonthStartOrEnd
     * @param isMonthStart是否是月份开始时间
     * @return
     *
     */
    public static Date getPreMonthStartOrEnd(Date date, boolean isMonthStart) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        if (isMonthStart) {
            c.set(Calendar.DAY_OF_MONTH, 1);
        } else {
            c.add(Calendar.MONTH, 1);
            c.set(Calendar.DAY_OF_MONTH, 0);
        }
        return getStartOrEnd(c, isMonthStart);
    }

    /**
     * 获取date的上周周一/周末
     * 
     * @Title: getPreWeekStartOrEnd
     * @param isWeekStart是否是周一
     * @return
     *
     */
    public static Date getPreWeekStartOrEnd(Date date, boolean isWeekStart) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.setFirstDayOfWeek(Calendar.MONDAY);// 设置每周开始时间为周一
        c.add(Calendar.WEEK_OF_MONTH, -1);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        if (!isWeekStart) {
            c.add(Calendar.DAY_OF_WEEK, 6);
        }
        return getStartOrEnd(c, isWeekStart);
    }

    /**
     * 获取date的去年开始时间/结束时间
     * 
     * @Title: getPreYearStartOrEnd
     * @param isYearStart是否是月份开始时间
     * @return
     *
     */
    public static Date getPreYearStartOrEnd(Date date, boolean isYearStart) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, -1);
        if (isYearStart) {
            c.set(Calendar.MONTH, Calendar.JANUARY);
            c.set(Calendar.DAY_OF_MONTH, 1);
        } else {
            c.set(Calendar.MONTH, Calendar.DECEMBER);
            c.add(Calendar.MONTH, 1);
            c.set(Calendar.DAY_OF_MONTH, 0);
        }
        return getStartOrEnd(c, isYearStart);
    }

    /**
     * 获取月份的季度开始月份和结束月份
     * 
     * @Title: getQuarterInMonth
     * @param month月份
     * @param isQuarterStart是否是季度开始月份
     * @return几月
     *
     */
    public static int getQuarterInMonth(int month, boolean isQuarterStart) {
        int months[] = { Calendar.JANUARY, Calendar.APRIL, Calendar.JULY, Calendar.OCTOBER };
        if (!isQuarterStart) {
            months = new int[] { Calendar.MARCH, Calendar.JUNE, Calendar.SEPTEMBER, Calendar.DECEMBER };
        }
        if (month >= Calendar.JANUARY && month <= Calendar.MARCH)
            return months[0];
        else if (month >= Calendar.APRIL && month <= Calendar.JUNE)
            return months[1];
        else if (month >= Calendar.JULY && month <= Calendar.SEPTEMBER)
            return months[2];
        else
            return months[3];
    }

    /**
     * 获取当前时间的季度的开始时间/结束时间
     * 
     * @Title: getQuarterStartOrEnd
     * @param isQuarterStart是否是季度开始时间
     * @return
     *
     */
    public static Date getQuarterStartOrEnd(Date date, boolean isQuarterStart) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (isQuarterStart) {
            c.set(Calendar.MONTH, BusinessDateUtil.getQuarterInMonth(c.get(Calendar.MONTH), isQuarterStart));
            c.set(Calendar.DAY_OF_MONTH, 1);
        } else {
            c.set(Calendar.MONTH, BusinessDateUtil.getQuarterInMonth(c.get(Calendar.MONTH), isQuarterStart) + 1);
            c.set(Calendar.DAY_OF_MONTH, 0);
        }
        return getStartOrEnd(c, isQuarterStart);
    }

    /**
     * 获取当前时间的月份开始时间/结束时间
     * 
     * @Title: getMonthStartOrEnd
     * @param isMonthStart是否是月份开始时间
     * @return
     *
     */
    public static Date getMonthStartOrEnd(Date date, boolean isMonthStart) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (isMonthStart) {
            c.set(Calendar.DAY_OF_MONTH, 1);
        } else {
            c.add(Calendar.MONTH, 1);
            c.set(Calendar.DAY_OF_MONTH, 0);
        }
        return getStartOrEnd(c, isMonthStart);
    }

    /**
     * 获取本周周一/周末
     * 
     * @Title: getWeekStartOrEnd
     * @param isWeekStart是否是周一
     * @return
     *
     */
    public static Date getWeekStartOrEnd(Date date, boolean isWeekStart) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.setFirstDayOfWeek(Calendar.MONDAY);// 设置每周开始时间为周一
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        if (!isWeekStart) {
            c.add(Calendar.DAY_OF_WEEK, 6);
        }
        return getStartOrEnd(c, isWeekStart);
    }

    /**
     * 获取今年开始时间/结束时间
     * 
     * @Title: getYearStartOrEnd
     * @param isYearStart是否是月份开始时间
     * @return
     *
     */
    public static Date getYearStartOrEnd(Date date, boolean isYearStart) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (isYearStart) {
            c.set(Calendar.MONTH, Calendar.JANUARY);
            c.set(Calendar.DAY_OF_MONTH, 1);
        } else {
            c.set(Calendar.MONTH, Calendar.DECEMBER);
            c.add(Calendar.MONTH, 1);
            c.set(Calendar.DAY_OF_MONTH, 0);
        }
        return getStartOrEnd(c, isYearStart);
    }

    private static Date getStartOrEnd(Calendar c, boolean isStart) {
        if (isStart) {
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
        } else {
            c.set(Calendar.HOUR_OF_DAY, 23);
            c.set(Calendar.MINUTE, 59);
            c.set(Calendar.SECOND, 59);
            c.set(Calendar.MILLISECOND, 999);
        }
        return c.getTime();
    }

    /**
     * 获取具体年，具体月的每一天的日期
     * 
     * @param year
     *            年份
     * @param month
     *            月份
     * @return
     */
    public static String[] getDaysByYearAndMonth(int year, int month) {
        // 先判断year是否是闰年
        boolean Renyear;
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            Renyear = true;
        } else {
            Renyear = false;
        }
        // 记录当月的天数
        int day = 0;
        switch (month) {
        case 1:
            day = 31;
            break;
        case 3:
            day = 31;
            break;
        case 4:
            day = 30;
            break;
        case 5:
            day = 31;
            break;
        case 6:
            day = 30;
            break;
        case 7:
            day = 31;
            break;
        case 8:
            day = 31;
            break;
        case 9:
            day = 30;
            break;
        case 10:
            day = 31;
            break;
        case 11:
            day = 30;
            break;
        case 12:
            day = 31;
            break;
        default:
            if (Renyear) {
                day = 29;
            } else {
                day = 28;
            }
            break;
        }
        // 组装具体年，月，天的数据
        String[] days = null;
        if (month >= 1 && month <= 12) {
            String strMonth = String.valueOf(month);
            strMonth = (strMonth.length() == 1 ? ("0" + strMonth) : strMonth);
            String strYear = String.valueOf(year);
            days = new String[day];
            int d = 0;
            for (int i = 0; i < day; i++) {
                d = i + 1;
                days[i] = strYear + "-" + strMonth + "-" + (d < 10 ? ("0" + d) : (d + ""));
            }
            return days;
        }
        return days;
    }

    /**
     * 判断一个字符串是否为时间格式yyyy-mm-dd,yyyy,mm
     * 
     * @param date
     *            字符串
     * @return
     */
    public static boolean IsTime(String date) {
        String yearRegis = "\\d{4}";
        String monthRegis = "\\d{4}-\\d{1,2}";
        String dayRegis = "\\d{4}-\\d{1,2}-\\d{1,2}";
        if (date.matches(yearRegis)) {
            return true;
        } else if (date.matches(monthRegis)) {
            return true;
        } else if (date.matches(dayRegis)) {
            return true;
        }
        return false;
    }

    // 获取一年中的12个月的日期
    public static String[] getMonths(int year) {
        String[] month = { year + "-" + "01", year + "-" + "02", year + "-" + "03", year + "-" + "04", year + "-" + "05", year + "-" + "06",
                year + "-" + "07", year + "-" + "08", year + "-" + "09", year + "-" + "10", year + "-" + "11", year + "-" + "12" };
        return month;
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sDate = sdf.format(getNearesOthersfWeek(new Date(), 1));
        System.out.println("----" + DateUtil.format(getNearesOthersfWeek(new Date(), 1), "yyyy-MM-dd 00:00:00"));
    }

}
