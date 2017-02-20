/**   
 * @Title: Test7.java 
 * @Package com.xtt.txgl.common.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年9月24日 下午6:41:54 
 *
 */
package com.xtt.common.util;

public class NumberTransitionUtil {
    public static void main(String[] args) {
        // 要输入的数字12
        String i = 12 + "";
        System.out.println(transition(i));
    }

    // 阿拉伯数字转中文小写？
    public static String transition(String si) {
        String str = "";

        String[] aa = { "", "十", "百", "千", "万", "十万", "百万", "千万", "亿", "十亿" };
        String[] bb = { "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        char[] ch = si.toCharArray();
        int maxindex = ch.length;
        // 字符的转换
        // 两位数的特殊转换
        if (maxindex == 2) {
            for (int i = maxindex - 1, j = 0; i >= 0; i--, j++) {
                if (ch[j] != 48) {
                    if (j == 0 && ch[j] == 49) {
                        str += aa[i];
                    } else {
                        str += (bb[ch[j] - 49] + aa[i]);
                    }
                }
            }
            // 其他位数的特殊转换，使用的是int类型最大的位数为十亿
        } else {
            for (int i = maxindex - 1, j = 0; i >= 0; i--, j++) {
                if (ch[j] != 48) {
                    str += (bb[ch[j] - 49] + aa[i]);
                }
            }
        }
        return str;
    }
}
