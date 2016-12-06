/**   
 * @Title: FormConfUtil.java 
 * @Package com.xtt.common.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年9月22日 下午4:28:41 
 *
 */
package com.xtt.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

import com.xtt.common.dto.FormDto;
import com.xtt.common.dto.FormNodesDto;
import com.xtt.platform.framework.core.redis.RedisCacheUtil;
import com.xtt.platform.util.lang.StringUtil;

@SuppressWarnings("unchecked")
public class DynamicFormUtil {

	public static String getKey(Integer tenantId, Long formId) {
		return (tenantId + "").concat("DynamicForm").concat(formId == null ? "*" : String.valueOf(formId));
	}

	/** 获取某种类别的表单key */
	public static String getCategoryFormKey(Integer tenantId, String sysOwner, String itemCode) {
		return (tenantId + "").concat("DynamicFormCategory").concat(StringUtil.stripToEmpty(sysOwner))
						.concat(StringUtil.isBlank(itemCode) ? "*" : itemCode);
	}

	/**
	 * 根据类别获取对象集合
	 * 
	 * @Title: getByItemCode
	 * @param itemCode
	 * @return
	 *
	 */
	public static List<FormNodesDto> getByFromId(Integer tenantId, Long formId) {
		List<FormNodesDto> list = (List<FormNodesDto>) RedisCacheUtil.getObject(getKey(tenantId, formId));
		return list;
	}

	/** 初始list中的对象为树状结构 */
	public static List<FormNodesDto> initTreeList(List<FormNodesDto> list) {
		if (CollectionUtils.isEmpty(list))
			return list;
		return initTreeList(list, null);
	}

	/** 初始list中的对象为树状结构 */
	public static List<FormNodesDto> initTreeList(List<FormNodesDto> list, String rootCode) {
		if (CollectionUtils.isEmpty(list))
			return list;
		if (StringUtil.isBlank(rootCode)) {
			// rootCode默认为pCode为零的code
			for (FormNodesDto node : list) {
				if ("0".equals(node.getpItemCode())) {
					rootCode = node.getItemCode();
					break;
				}
			}
		}
		List<FormNodesDto> rootNodes = getChildNodes(list, rootCode);
		if (CollectionUtils.isNotEmpty(rootNodes))
			for (FormNodesDto node : rootNodes) {
				if (!node.getIsLeaf())
					setChildNodes(node, list);
			}
		return rootNodes;
	}

	private static void setChildNodes(FormNodesDto node, List<FormNodesDto> allNodes) {
		List<FormNodesDto> children = getChildNodes(allNodes, node.getItemCode());
		if (CollectionUtils.isNotEmpty(children)) {
			for (FormNodesDto n : children) {
				if (!n.getIsLeaf())
					setChildNodes(n, allNodes);
			}
		}
		node.setChildNodes(children);
	}

	/**
	 * 获取list中的itemCode的叶子节点
	 * 
	 * @Title: getChildNodes
	 * @param list节点集合
	 * @param itemCode节点编号
	 * @return
	 *
	 */
	public static List<FormNodesDto> getChildNodes(List<FormNodesDto> list, String itemCode) {
		List<FormNodesDto> children = new ArrayList<FormNodesDto>();
		for (FormNodesDto fuc : list) {
			if (itemCode.equals(fuc.getpItemCode())) {
				children.add(fuc);
			}
		}
		return children;
	}

	/**
	 * 根据叶子节点数据，反向生成树结构数据
	 * 
	 * @Title: getTreeByLeaf
	 * @param list叶子节点集合
	 * @param allItems所有的节点
	 * @return
	 *
	 */
	public static List<FormNodesDto> getTreeByLeaf(List<FormNodesDto> list, List<FormNodesDto> allItems) {
		List<FormNodesDto> resultList = new ArrayList<FormNodesDto>();
		if (CollectionUtils.isEmpty(list) || CollectionUtils.isEmpty(allItems))
			return resultList;
		Set<String> pCodes = new LinkedHashSet<String>();
		for (FormNodesDto item : list) {// 获取所有父节点编号
			if (StringUtil.isNotBlank(item.getItemPath())) {
				for (String pCode : item.getItemPath().split(",")) {
					pCodes.add(pCode);
				}
			}
		}
		int addCount = 0;
		mark: for (String itemCode : pCodes) {
			if (addCount < pCodes.size()) {// 如果节点没全部添加,则需要继续遍历
				for (FormNodesDto item : allItems) {
					if (item.getItemCode().equals(itemCode)) {
						resultList.add(item);
						addCount++;
						continue mark;
					}
				}
			}
			// 如果所有节点均已添加，或者某个节点找不到数据，终止遍历
			break;
		}
		{// 过滤重复项
			repetitionMark: for (Iterator<FormNodesDto> iterator = resultList.iterator(); iterator.hasNext();) {
				FormNodesDto item = iterator.next();
				for (FormNodesDto temp : list) {
					if (temp.getItemCode().equals(item.getItemCode())) {
						iterator.remove();
						continue repetitionMark;
					}
				}
			}
		}
		resultList.addAll(list);
		return initTreeList(resultList);
	}

	/**
	 * 将List集合转换为map
	 * 
	 * @Title: convertToMap
	 * @return
	 *
	 */
	public static Map<String, FormNodesDto> convertToMap(List<FormNodesDto> list) {
		Map<String, FormNodesDto> map = new HashMap<>();
		if (CollectionUtils.isNotEmpty(list)) {
			for (FormNodesDto node : list) {
				map.put(node.getItemCode(), node);
			}
		}
		return map;
	}

	/** 缓存所有数据 */
	public static void cacheAll(HashMap<String, List<FormNodesDto>> map) {
		RedisCacheUtil.batchSetObject(map);
	}

	/**
	 * 缓存某种类别对应的表单
	 * 
	 * @Title: cacheCategoryForm
	 * @param categoryMap
	 *
	 */
	public static void cacheCategoryForm(HashMap<String, List<FormDto>> categoryMap) {
		RedisCacheUtil.batchSetObject(categoryMap);
	}

	/**
	 * 根据类别和所属系统获取表单
	 * 
	 * @Title: getCategoryForm
	 * @param sysOwner
	 * @param itemCode
	 * @return
	 *
	 */
	public static List<FormDto> getCategoryForm(String sysOwner, String itemCode) {
		return (List<FormDto>) RedisCacheUtil.getObject(getCategoryFormKey(UserUtil.getTenantId(), sysOwner, itemCode));
	}

	/**
	 * 根据类别和所属系统获取正在使用的表单
	 * 
	 * @Title: getEnableCategoryForm
	 * @param sysOwner
	 * @param itemCode
	 * @return
	 *
	 */
	public static List<FormDto> getEnableCategoryForm(String sysOwner, String itemCode) {
		List<FormDto> list = getCategoryForm(sysOwner, itemCode);
		if (CollectionUtils.isNotEmpty(list))
			for (Iterator<FormDto> it = list.iterator(); it.hasNext();) {
				FormDto form = it.next();
				if (!form.getIsEnable())
					it.remove();
			}
		return list;
	}
}
