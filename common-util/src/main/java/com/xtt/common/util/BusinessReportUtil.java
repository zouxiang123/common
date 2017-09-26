/**   
 * @Title: BusinessDateUtil.java 
 * @Package com.xtt.common.common.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年11月4日 上午10:58:06 
 *
 */
package com.xtt.common.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;

import com.xtt.common.constants.CommonConstants;
import com.xtt.platform.util.NumberTransitionUtil;
import com.xtt.platform.util.time.DateFormatUtil;

public class BusinessReportUtil {
    /**
     * 根据String日期获取当天最早时间和最晚时间(仅支持yyyy-mm-dd格式日期)
     * 
     * @Title: getStartOrEndDate
     * @param dateStr
     * @param isStart
     * @return
     *
     */
    public static Date getStartOrEndDate(String dateStr, boolean isStart) {
        if (StringUtils.isBlank(dateStr))
            return null;
        if (isStart) {
            return DateFormatUtil.convertStrToDate(DateFormatUtil.getStartTimeStr(dateStr), DateFormatUtil.FORMAT_TIME1);
        } else {
            return DateFormatUtil.convertStrToDate(DateFormatUtil.getEndTimeStr(dateStr), DateFormatUtil.FORMAT_TIME1);
        }
    }

    /**
     * 获取最近一周/一个月/一年的开始时间
     * 
     * @Title: getNearestByDateType
     * @param dateType（0：周；1：月；2:年）
     * @return
     *
     */
    public static Date getNearestByDateType(int dateType) {
        Date startDate = new Date();
        if (dateType == 0) {
            startDate = BusinessDateUtil.getNearestWeek(startDate);
        } else if (dateType == 1) {
            startDate = BusinessDateUtil.getNearestMonth(startDate);
        } else if (dateType == 2) {
            startDate = BusinessDateUtil.getNearestYear(startDate);
        }
        return startDate;
    }

    /**
     * 获取最近一周/一个月/一年的开始时间
     * 
     * @Title: getNearestByDateType
     * @param dateType（w：周；m：月；y:年）
     * @return
     *
     */
    public static Date getNearestByDateType(String dateType) {
        Date startDate = new Date();
        if (dateType == null) {
            return startDate;
        }
        if (CommonConstants.DATE_TYPE_WEEK.equals(dateType)) {
            startDate = BusinessDateUtil.getNearestWeek(startDate);
        } else if (CommonConstants.DATE_TYPE_MONTH.equals(dateType)) {
            startDate = BusinessDateUtil.getNearestMonth(startDate);
        } else if (CommonConstants.DATE_TYPE_YEAR.equals(dateType)) {
            startDate = BusinessDateUtil.getNearestYear(startDate);
        }
        return startDate;
    }

    /**
     * 转换月份和星期为中文
     * 
     * @Title: translateNumber
     * @param list
     *            List<Map<String, Object>> key只能为（value,value1，value2...）以及time
     * @param type
     *            星期还是月份
     * @param valueSize
     *            time 以外value 个数
     * @return
     *
     */
    public static List<Map<String, Object>> translateNumber(List<Map<String, Object>> list, String type, int valueSize) {
        if (StringUtils.isEmpty(type) || list == null || list.isEmpty()) {
            return list;
        }
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        Calendar calendar = Calendar.getInstance();
        int weekDay;
        boolean isFirstSunday = (calendar.getFirstDayOfWeek() == Calendar.SUNDAY); // 一周第一天是否为星期天
        Map<String, Object> temp;
        if (type.equals(CommonConstants.DATE_TYPE_WEEK)) {
            for (Map<String, Object> map : list) {
                temp = new HashMap<String, Object>();
                for (int t = 0; t < valueSize; t++) {
                    if (t == 0) {
                        temp.put("value", map.get("value"));
                    } else {
                        temp.put("value" + t, map.get("value" + t));
                    }
                }
                calendar.setTime((Date) map.get("time"));
                weekDay = calendar.get(Calendar.DAY_OF_WEEK);
                if (isFirstSunday) {
                    weekDay = weekDay - 1;
                }
                temp.put("time", NumberTransitionUtil.transitionToWeek(weekDay));
                resultList.add(temp);
            }
            resultList = addEmptyTimeCount(resultList, type, valueSize);
        } else if (type.equals(CommonConstants.DATE_TYPE_MONTH)) {
            for (Map<String, Object> map : list) {
                temp = new HashMap<String, Object>();
                for (int t = 0; t < valueSize; t++) {
                    if (t == 0) {
                        temp.put("value", map.get("value"));
                    } else {
                        temp.put("value" + t, map.get("value" + t));
                    }
                }
                temp.put("time", DateFormatUtil.convertDateToStr((Date) map.get("time"), "MM/dd"));
                resultList.add(temp);
            }
            resultList = addEmptyTimeCount(resultList, type, valueSize);
        } else if (type.equals(CommonConstants.DATE_TYPE_YEAR)) {
            for (Map<String, Object> map : list) {
                temp = new HashMap<String, Object>();
                for (int t = 0; t < valueSize; t++) {
                    if (t == 0) {
                        temp.put("value", map.get("value"));
                    } else {
                        temp.put("value" + t, map.get("value" + t));
                    }
                }
                temp.put("time", DateFormatUtil.convertDateToStr((Date) map.get("time"), "yyyy/MM"));
                resultList.add(temp);
            }
            resultList = addEmptyTimeCount(resultList, type, valueSize);
        } else {
            return list;
        }
        return resultList;
    }

    private static List<Map<String, Object>> addEmptyTimeCount(List<Map<String, Object>> list, String type, int valueSize) {
        Map<String, Object> temp;
        List<Map<String, Object>> all = new ArrayList<Map<String, Object>>();
        if (type.equals(CommonConstants.DATE_TYPE_WEEK)) {
            String[] ch = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(BusinessDateUtil.getNearestWeek(new Date()));
            int startWeek = calendar.get(Calendar.DAY_OF_WEEK);
            calendar.setTime(new Date());
            int endWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (calendar.getFirstDayOfWeek() == Calendar.SUNDAY) {
                startWeek = startWeek - 1;
                endWeek = endWeek - 1;
            }
            String[] newCh = new String[ch.length];
            int s = 0;
            for (int i = startWeek; i < ch.length; i++) {
                newCh[s] = ch[i];
                s++;
            }
            for (int i = 0; i <= endWeek; i++) {
                newCh[s] = ch[i];
                s++;
            }
            for (int i = 0; i < newCh.length; i++) {
                boolean isExists = false;
                for (Map<String, Object> map : list) {
                    if (newCh[i].equals(map.get("time"))) {
                        all.add(map);
                        isExists = true;
                    }
                }
                if (!isExists) {
                    temp = new HashMap<String, Object>();
                    temp.put("time", newCh[i]);
                    for (int t = 0; t < valueSize; t++) {
                        if (t == 0) {
                            temp.put("value", 0);
                        } else {
                            temp.put("value" + t, 0);
                        }
                    }
                    all.add(temp);
                }
            }
        } else if (type.equals(CommonConstants.DATE_TYPE_MONTH)) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = BusinessDateUtil.getNearestMonth(new Date());
            DateTime end = new DateTime(sdf.format(new Date()));
            DateTime start = new DateTime(sdf.format(startDate));
            int count = Days.daysBetween(start, end).getDays();
            calendar.setTime(startDate);
            for (int i = 0; i <= count; i++) {
                String day = DateFormatUtil.convertDateToStr(calendar.getTime(), "MM/dd");
                boolean isExists = false;
                for (Map<String, Object> map : list) {
                    if (day.equals(map.get("time"))) {
                        all.add(map);
                        isExists = true;
                    }
                }
                if (!isExists) {
                    temp = new HashMap<String, Object>();
                    temp.put("time", day);
                    for (int t = 0; t < valueSize; t++) {
                        if (t == 0) {
                            temp.put("value", 0);
                        } else {
                            temp.put("value" + t, 0);
                        }
                    }
                    all.add(temp);
                }
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }

        } else if (type.equals(CommonConstants.DATE_TYPE_YEAR)) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = BusinessDateUtil.getNearestYear(new Date());
            DateTime end = new DateTime(sdf.format(new Date()));
            DateTime start = new DateTime(sdf.format(startDate));
            int count = Days.daysBetween(start, end).getDays();
            calendar.setTime(startDate);
            for (int i = 0; i <= count; i++) {
                String day = DateFormatUtil.convertDateToStr(calendar.getTime(), "yyyy/MM");
                boolean isExists = false;
                for (Map<String, Object> map : list) {
                    if (day.equals(map.get("time"))) {
                        all.add(map);
                        isExists = true;
                    }
                }
                if (!isExists) {
                    temp = new HashMap<String, Object>();
                    temp.put("time", day);
                    for (int t = 0; t < valueSize; t++) {
                        if (t == 0) {
                            temp.put("value", 0);
                        } else {
                            temp.put("value" + t, 0);
                        }
                    }
                    all.add(temp);
                }
                calendar.add(Calendar.DAY_OF_YEAR, 1);
            }
        }
        return all;
    }
}
