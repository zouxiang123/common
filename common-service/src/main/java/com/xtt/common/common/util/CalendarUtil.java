/**   
 * @Title: CalendarUtil.java 按ISO_8601标准，即一周的第一天是周一，最后一天是周日
 * @Package com.xtt.txgl.common.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年12月8日 上午9:33:20 
 *
 */
package com.xtt.common.common.util;

import java.util.Calendar;
import java.util.Date;

import com.xtt.platform.util.time.DateFormatUtil;

public class CalendarUtil {

	/**
	 * 根据当前日期获取周第一天
	 * 
	 * @Title: getFirstDay
	 * @return
	 * 
	 */
	public static Date getWeekFirstDay() {
		Calendar cal = Calendar.getInstance();

		return getWeekFirstDay(cal);
	}

	/**
	 * 根据当前日期获取周最后一天
	 * 
	 * @Title: getFirstDay
	 * @return
	 * 
	 */
	public static Date getWeekLastDay() {
		Calendar cal = Calendar.getInstance();

		return getWeekFirstDay(cal);
	}

	/**
	 * 查询差值周对应的周最后一天
	 * 
	 * @Title: getWeekFirstDay
	 * @param weekNo
	 *            针对当前周的差值。-1：上周，0：当前周，1：下周
	 * @return
	 * 
	 */
	public static Date getWeekLastDay(int weekNo) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_YEAR, weekNo);
		return getWeekLastDay(cal);
	}

	/**
	 * 查询差值周对应的周第一天
	 * 
	 * @Title: getWeekFirstDay
	 * @param weekNo
	 *            针对当前周的差值。-1：上周，0：当前周，1：下周
	 * @return
	 * 
	 */
	public static Date getWeekFirstDay(int weekNo) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_YEAR, weekNo);
		return getWeekFirstDay(cal);
	}

	/**
	 * 根据日历获取第一天
	 * 
	 * @Title: getFirstDay
	 * @param cal
	 * @return
	 * 
	 */
	public static Date getWeekFirstDay(Calendar cal) {
		if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
			cal.add(Calendar.DATE, -7);
		}
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}

	/**
	 * 根据当前日期获取周最后一天
	 * 
	 * @Title: getFirstDay
	 * @return
	 * 
	 */
	public static Date getWeekLastDay(Calendar cal) {
		if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
			cal.add(Calendar.DATE, -7);
		}
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.add(Calendar.DATE, 6);
		return cal.getTime();
	}

	/**
	 * 获取当前日期对应一年中的第几周
	 * 
	 * @Title: getWeekOfYear
	 * @return
	 * 
	 */
	public static int getWeekOfYear() {
		Calendar cal = Calendar.getInstance();
		return getWeekOfYear(cal);
	}

	/**
	 * 获取日期对应一年中的第几周
	 * 
	 * @Title: getWeekOfYear
	 * @param cal
	 * @return
	 * 
	 */
	public static int getWeekOfYear(Calendar cal) {
		int weekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
		if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
			weekOfYear--;
		}
		return weekOfYear;
	}

	/**
	 * 获取当前是周几
	 * 
	 * @Title: getDayOfWeek
	 * @return
	 * 
	 */
	public static int getDayOfWeek() {

		return getDayOfWeek(Calendar.getInstance());
	}

	/**
	 * 根据日历获取当前周几
	 * 
	 * @Title: getDayOfWeek
	 * @param cal
	 * @return
	 * 
	 */
	public static int getDayOfWeek(Calendar cal) {

		return convertDayOfWeek(cal.get(Calendar.DAY_OF_WEEK));
	}

	/**
	 * 转换一周中的第几天显示，从周日开始，修改成周一开始
	 * 
	 * @Title: convertDayOfWeek
	 * @param dayOfWeek
	 * @return
	 * 
	 */
	public static Integer convertDayOfWeek(Integer dayOfWeek) {
		dayOfWeek -= 1;
		return dayOfWeek == 0 ? 7 : dayOfWeek;
	}

	/**
	 * 反转一周中的第几天显示，从周一开始，修改成周日开始
	 * 
	 * @Title: convertDayOfWeek
	 * @param dayOfWeek
	 * @return
	 * 
	 */
	public static Integer reverseDayOfWeek(Integer dayOfWeek) {
		dayOfWeek = (dayOfWeek + 1) % 7;
		return dayOfWeek;
	}

	public static Integer covertWeekNumber(Integer weekNumber, Integer dayOfWeek) {
		if (dayOfWeek == 1) {
			weekNumber--;
		}
		return weekNumber;
	}

	/**
	 * 获取两个日期直接相差几天
	 * 
	 * @Title: getIntervalDays
	 * @param d1
	 * @param d2
	 * @return
	 * 
	 */
	public static Integer getIntervalDays(Date d1, Date d2) {
		long intervalMilli = d1.getTime() - d2.getTime();

		return (int) (intervalMilli / (24 * 60 * 60 * 1000));
	}

	/**
	 * 获取月份，如6月份，返回值6
	 * 
	 * @Title: getMonth
	 * @return
	 * 
	 */
	public static int getMonth() {
		return getMonth(Calendar.getInstance());
	}

	public static int getMonth(Calendar cal) {
		int month = cal.get(Calendar.MONTH);
		month = (month + 1) % 12;
		return month;
	}

	public static void setMonth(Calendar cal, int month) {
		month = (month - 1) % 12;
		cal.set(Calendar.MONTH, month);
	}

	public static void main(String[] args) {
		System.out.println(getMonth());
		Calendar cal = Calendar.getInstance();
		setMonth(cal, 1);
		System.out.println(DateFormatUtil.convertDateToStr(cal.getTime()));
	}
}
