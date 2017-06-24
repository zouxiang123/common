/**   
 * @Title: SyncGroupModule.java 
 * @Package com.xtt.common.enums
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年6月22日 下午2:08:56 
 *
 */
package com.xtt.common.enums;

import java.util.Objects;

import com.xtt.platform.util.lang.StringUtil;

public enum SyncModuleEnum {
    dict("字典", "dict"), drug("药品", "drug"), supplies("耗材", "supplies"), complication("并发症", "complication"), medical_order("医嘱", "medical_order");

    private String name;
    private String value;

    private SyncModuleEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    /**
     * 根据多个以","分割的值获取对应的名称
     * 
     * @Title: getNameByValues
     * @param values
     * @return 以","分割名称
     *
     */
    public static String getNameByValues(String values) {
        if (StringUtil.isBlank(values)) {
            return values;
        }
        if (values.indexOf(",") == -1) {
            return getNameByValue(values);
        } else {
            String[] valueArr = values.split(",");
            String result = "";
            for (String value : valueArr) {
                result += getNameByValue(value) + ",";
            }
            return result.substring(0, result.length() - 1);
        }
    }

    /**
     * 根据value 获取名称
     * 
     * @Title: getNameByValue
     * @param val
     * @return
     *
     */
    private static String getNameByValue(String val) {
        SyncModuleEnum[] moules = SyncModuleEnum.values();
        for (SyncModuleEnum moule : moules) {
            if (Objects.equals(moule.value, val)) {
                return moule.name;
            }
        }
        return val;
    }
}
