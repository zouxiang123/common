/**   
 * @Title: DictionaryUtil.java 用于操作获取字典表的相关信息
 * @Package com.xtt.txgl.common.util
 * Copyright: Copyright (c) 2015
 * @author: 陈光浩   
 * @date: 2015年9月9日 下午4:29:48 
 *
 */
package com.xtt.common.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.xtt.common.cache.CmDictCache;
import com.xtt.common.cache.ICmDictFactory;
import com.xtt.common.dto.DictDto;
import com.xtt.platform.util.lang.StringUtil;

public class DictUtil {

    private static ICmDictFactory factory = new CmDictCache();

    private DictUtil() {
    }

    /**
     * 根据类别编号和编号获取对应的名称<br>
     * 如果是以“,”分割的多个code，则返回以“,”分割的多个名称
     * 
     * @Title: getItemName
     * @param pItemCode
     *            类别编号
     * @param itemCode
     *            类别名称
     * @return
     *
     */
    public static String getItemName(String pItemCode, String itemCode) {
        if (StringUtils.isNotEmpty(itemCode)) {
            if (itemCode.indexOf(",") != -1) {
                String[] selectedValues = itemCode.split(",");
                Map<String, String> map = getMapByPItemCode(pItemCode);
                if (map != null) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < selectedValues.length; i++) {
                        sb.append(map.get(selectedValues[i]));
                        sb.append(",");
                    }
                    if (sb.length() > 0) {
                        return sb.substring(0, sb.length() - 1);
                    }
                }
                return "";
            } else {
                return factory.getItemName(pItemCode, itemCode);
            }
        }
        return itemCode;
    }

    /**
     * 根据类别和类别的名称获取对应的itemCode <br>
     * 如果是以“,”分割的多个名称，则返回已“”分割的多个itemCode
     * 
     * @Title: getItemCode
     * @param pItemCode
     *            类别编号
     * @param itemName
     *            类别名称
     * @return
     *
     */
    public static String getItemCode(String pItemCode, String itemName) {
        if (StringUtils.isNotEmpty(itemName)) {
            if (itemName.indexOf(",") != -1) {
                String[] names = itemName.split(",");
                List<DictDto> list = listByPItemCode(pItemCode);
                if (CollectionUtils.isNotEmpty(list)) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < names.length; i++) {
                        for (DictDto dict : list) {
                            if (Objects.equals(names[i], dict.getItemName())) {
                                sb.append(dict.getItemCode());
                                sb.append(",");
                                break;
                            }
                        }
                    }
                    if (sb.length() > 0) {
                        return sb.substring(0, sb.length() - 1);
                    }
                }
                return "";
            } else {
                return factory.getItemCode(pItemCode, itemName);
            }
        }

        return itemName;
    }

    /**
     * 根据类别获取该类别的字典数据集合
     * 
     * @Title: listByPItemCode
     * @param pItemCode
     *            类别编号
     * @return
     *
     */
    public static List<DictDto> listByPItemCode(String pItemCode) {

        return factory.listByPItemCode(pItemCode);
    }

    /**
     * 根据类别获取该类别的itemCode和itemName组成的map
     * 
     * @Title: getMapByPItemCode
     * @param pItemCode
     *            类别
     * @return Map<itemCode,itemName>,查不到指定类别的数据，返回null
     *
     */
    public static Map<String, String> getMapByPItemCode(String pItemCode) {
        List<DictDto> list = factory.listByPItemCode(pItemCode);
        if (CollectionUtils.isNotEmpty(list)) {
            final Map<String, String> map = new LinkedHashMap<String, String>(list.size());
            list.forEach(dict -> {
                map.put(dict.getItemCode(), dict.getItemName());
            });
            return map;
        }
        return null;
    }

    private static List<DictDto> listByPItemCode(String type, String selectStr, boolean isBool) {
        List<DictDto> list = listByPItemCode(type);
        if (!StringUtil.isEmpty(selectStr)) {
            if (selectStr.indexOf(",") != -1) {
                String[] selectedValues = selectStr.split(",");
                for (DictDto d : list) {
                    for (int i = 0; i < selectedValues.length; i++) {
                        if (d.getItemCode().equals(selectedValues[i])) {
                            d.setIsChecked(true);
                        }
                    }
                }
            } else {
                for (DictDto d : list) {
                    if (d.getItemCode().equals(selectStr)) {
                        d.setIsChecked(true);
                    }
                    if (isBool) {
                        if (d.getItemCode().equals("0")) {
                            d.setItemCode("false");
                        } else if (d.getItemCode().equals("1")) {
                            d.setItemCode("true");
                        }
                    }
                }
            }
        }
        return list;
    }

    /**
     * 根据类别获取该类别下的字典数据集合
     * 
     * @Title: listByPItemCode
     * @param pItemCode
     *            类别
     * @param itemCode
     *            当前选中的值，默认选中
     * @return
     *
     */
    public static List<DictDto> listByPItemCode(String pItemCode, String itemCode) {
        return listByPItemCode(pItemCode, itemCode, false);
    }

    /**
     * 根据类别获取该类别下的字典数据集合
     * 
     * @Title: listByPItemCode
     * @param pItemCode
     *            类别
     * @param itemCode
     *            当前选中的值，默认选中
     * @return
     *
     */
    public static List<DictDto> listByPItemCode(String pItemCode, Boolean itemCode) {
        String selectStr = "";
        if (itemCode == null || !itemCode) {
            selectStr = "0";
        } else {
            selectStr = "1";
        }
        return listByPItemCode(pItemCode, selectStr, true);
    }
}
