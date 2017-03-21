/**   
 * @Title: PinyinUtil.java 
 * @Package com.xtt.common.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年3月21日 下午7:00:07 
 *
 */
package com.xtt.common.util;

import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

public class PinyinUtil {
    /**
     * 获取字符串的中文拼音
     * 
     * @Title: getPinyin
     * @param str
     *            字符串
     * @return 字符串的拼音
     *
     */
    public static String getPinyin(String str) {
        return PinyinHelper.convertToPinyinString(str, "", PinyinFormat.WITHOUT_TONE);
    }

    /**
     * 获取字符串对应拼音的首字母
     * 
     * @Title: getShortPinyin
     * @param str
     * @return 对应拼音的首字母
     *
     */
    public static String getShortPinyin(String str) {
        return PinyinHelper.getShortPinyin(str);
    }
}
