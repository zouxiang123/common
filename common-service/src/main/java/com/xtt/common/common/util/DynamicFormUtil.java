/**   
 * @Title: FormConfUtil.java 
 * @Package com.xtt.common.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年9月22日 下午4:28:41 
 *
 */
package com.xtt.common.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

import com.xtt.common.common.service.ICmFormNodesService;
import com.xtt.common.dao.po.CmFormNodes;
import com.xtt.common.dao.po.CmFormPO;
import com.xtt.common.form.service.ICmFormService;
import com.xtt.platform.framework.core.redis.RedisCacheUtil;
import com.xtt.platform.util.config.SpringUtil;
import com.xtt.platform.util.lang.StringUtil;

@SuppressWarnings("unchecked")
public class DynamicFormUtil {
	private static ICmFormNodesService cmFormNodesService = SpringUtil.getBean("cmFormNodesServiceImpl", ICmFormNodesService.class);
	private static ICmFormService cmFormService = SpringUtil.getBean("cmFormServiceImpl", ICmFormService.class);
	private static boolean hasInit = false;

	private static String getKey(Integer tenantId, Long formId) {
		return (tenantId + "").concat("DynamicForm").concat(formId == null ? "*" : String.valueOf(formId));
	}

	/**
	 * 根据类别获取对象集合
	 * 
	 * @Title: getByItemCode
	 * @param itemCode
	 * @return
	 *
	 */
	public static List<CmFormNodes> getByFromId(Integer tenantId, Long formId) {
		if (!hasInit)
			refresh(tenantId, null);
		List<CmFormNodes> list = (List<CmFormNodes>) RedisCacheUtil.getObject(getKey(tenantId, formId));
		return list;
	}

	/** 初始list中的对象为树状结构 */
	public static List<CmFormNodes> initTreeList(List<CmFormNodes> list) {
		if (CollectionUtils.isEmpty(list))
			return list;
		return initTreeList(list, null);
	}

	/** 初始list中的对象为树状结构 */
	public static List<CmFormNodes> initTreeList(List<CmFormNodes> list, String rootCode) {
		if (CollectionUtils.isEmpty(list))
			return list;
		if (StringUtil.isBlank(rootCode)) {
			// rootCode默认为pCode为零的code
			for (CmFormNodes node : list) {
				if ("0".equals(node.getpItemCode())) {
					rootCode = node.getItemCode();
					break;
				}
			}
		}
		List<CmFormNodes> rootNodes = getChildNodes(list, rootCode);
		if (CollectionUtils.isNotEmpty(rootNodes))
			for (CmFormNodes node : rootNodes) {
				if (!node.getIsLeaf())
					setChildNodes(node, list);
			}
		return rootNodes;
	}

	private static void setChildNodes(CmFormNodes node, List<CmFormNodes> allNodes) {
		List<CmFormNodes> children = getChildNodes(allNodes, node.getItemCode());
		if (CollectionUtils.isNotEmpty(children)) {
			for (CmFormNodes n : children) {
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
	public static List<CmFormNodes> getChildNodes(List<CmFormNodes> list, String itemCode) {
		List<CmFormNodes> children = new ArrayList<CmFormNodes>();
		for (CmFormNodes fuc : list) {
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
	public static List<CmFormNodes> getTreeByLeaf(List<CmFormNodes> list, List<CmFormNodes> allItems) {
		List<CmFormNodes> resultList = new ArrayList<CmFormNodes>();
		if (CollectionUtils.isEmpty(list) || CollectionUtils.isEmpty(allItems))
			return resultList;
		Set<String> pCodes = new LinkedHashSet<String>();
		for (CmFormNodes item : list) {// 获取所有父节点编号
			if (StringUtil.isNotBlank(item.getItemPath())) {
				for (String pCode : item.getItemPath().split(",")) {
					pCodes.add(pCode);
				}
			}
		}
		int addCount = 0;
		mark: for (String itemCode : pCodes) {
			if (addCount < pCodes.size()) {// 如果节点没全部添加,则需要继续遍历
				for (CmFormNodes item : allItems) {
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
			repetitionMark: for (Iterator<CmFormNodes> iterator = resultList.iterator(); iterator.hasNext();) {
				CmFormNodes item = iterator.next();
				for (CmFormNodes temp : list) {
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

	/** 刷新缓存 */
	public static void refresh(Integer tenantId, String sysOwner) {
		// 删除数据
		RedisCacheUtil.deletePattern(getKey(tenantId, null));
		// 获取当前系统下所有的表单列表
		CmFormPO record = new CmFormPO();
		record.setSysOwner(sysOwner);
		List<CmFormPO> formList = cmFormService.selectByCondition(record);
		if (CollectionUtils.isNotEmpty(formList)) {
			HashMap<String, List<CmFormNodes>> map = new HashMap<>();
			CmFormPO form;
			for (int c = 0; c < formList.size(); c++) {
				form = formList.get(c);
				map.put(getKey(tenantId, form.getId()), cmFormNodesService.selectByFormId(form.getId()));
			}
			RedisCacheUtil.batchSetObject(map);
		}
		hasInit = true;
	}
}
