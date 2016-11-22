/**   
 * @Title: CommonCacheServiceImpl.java 
 * @Package com.xtt.common.common.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年11月10日 下午6:33:27 
 *
 */
package com.xtt.common.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.cache.CmDictCache;
import com.xtt.common.cache.PatientCache;
import com.xtt.common.common.service.ICmDictService;
import com.xtt.common.common.service.ICmFormNodesService;
import com.xtt.common.common.service.ICommonCacheService;
import com.xtt.common.common.service.ISysParamService;
import com.xtt.common.common.service.ISysTenantService;
import com.xtt.common.dao.model.SysObj;
import com.xtt.common.dao.model.SysRole;
import com.xtt.common.dao.model.SysTenant;
import com.xtt.common.dao.po.CmDictPO;
import com.xtt.common.dao.po.CmFormPO;
import com.xtt.common.dao.po.PatientPO;
import com.xtt.common.dao.po.SysParamPO;
import com.xtt.common.dto.DictDto;
import com.xtt.common.dto.FormDto;
import com.xtt.common.dto.FormNodesDto;
import com.xtt.common.dto.PatientDto;
import com.xtt.common.dto.SysObjDto;
import com.xtt.common.dto.SysParamDto;
import com.xtt.common.form.service.ICmFormService;
import com.xtt.common.patient.service.IPatientService;
import com.xtt.common.permission.PermissionCache;
import com.xtt.common.user.service.IRoleService;
import com.xtt.common.util.DynamicFormUtil;
import com.xtt.common.util.SysParamUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.framework.core.redis.RedisCacheUtil;

@Service
public class CommonCacheServiceImpl implements ICommonCacheService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonCacheServiceImpl.class);

	@Autowired
	private ISysParamService sysParamService;
	@Autowired
	private ICmDictService cmDictService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IPatientService patientService;
	@Autowired
	private ICmFormNodesService cmFormNodesService;
	@Autowired
	private ICmFormService cmFormService;
	@Autowired
	private ISysTenantService sysTenantService;

	@Override
	public void cacheDict(Integer tenantId) {
		RedisCacheUtil.deletePattern(CmDictCache.getKey(tenantId, null));
		List<CmDictPO> list = cmDictService.selectAll();
		if (CollectionUtils.isNotEmpty(list)) {
			List<DictDto> dicts = new ArrayList<>(list.size());
			DictDto cDict;
			for (CmDictPO dict : list) {
				cDict = new DictDto();
				BeanUtils.copyProperties(dict, cDict);
				dicts.add(cDict);
			}
			CmDictCache.cacheALL(dicts);
		}
	}

	@Override
	public void cacheSysParam(Integer tenantId) {
		RedisCacheUtil.deletePattern(SysParamUtil.getKey(tenantId, null));
		List<SysParamPO> list = sysParamService.getByTenantId(tenantId);
		if (CollectionUtils.isNotEmpty(list)) {
			List<SysParamDto> params = new ArrayList<>(list.size());
			SysParamDto param;
			for (int i = 0; i < list.size(); i++) {
				param = new SysParamDto();
				BeanUtils.copyProperties(list.get(i), param);
				params.add(param);
			}
			SysParamUtil.cacheAll(params);
		}
	}

	@Override
	public void cachePermission(Integer tenantId) {
		Map<String, List<SysObjDto>> map = new HashMap<>();
		RedisCacheUtil.deletePattern(PermissionCache.getKey(tenantId, null));
		String[] types = { "1", "2" };
		map.put(tenantId + PermissionCache.ALL_SYS_OBJ_KEY, convertSysObjList(roleService.getAllMenuList(types, null)));
		List<SysRole> list = roleService.getRoleListByTenantId(tenantId, null);
		for (SysRole sysRole : list) {
			Long[] roleIds = { sysRole.getId() };
			map.put(PermissionCache.getKey(tenantId, sysRole.getId()), convertSysObjList(roleService.getMenuListByRoleId(roleIds, types, null)));
		}
		PermissionCache.cacheAll(map);
	}

	/**
	 * 将sysObj对象转成缓存的SysObjDto
	 * 
	 * @Title: convertCmSysObjList
	 * @param list
	 * @return
	 *
	 */
	public static List<SysObjDto> convertSysObjList(List<SysObj> list) {
		List<SysObjDto> tempList = new ArrayList<SysObjDto>();
		if (list != null && !list.isEmpty()) {
			SysObjDto CmSysObj;
			for (SysObj so : list) {// 缓存有需要的数据
				CmSysObj = new SysObjDto();
				CmSysObj.setName(so.getName());
				CmSysObj.setKey(so.getKey());
				CmSysObj.setUrl(so.getUrl());
				CmSysObj.setType(so.getType());
				CmSysObj.setCode(so.getCode());
				CmSysObj.setpCode(so.getpCode());
				tempList.add(CmSysObj);
			}
			list.clear();
		}
		return tempList;
	}

	@Override
	public void cachePatient(Integer tenantId) {
		RedisCacheUtil.deletePattern(PatientCache.getKey(tenantId, null));
		List<PatientPO> list = patientService.getPatientByTenantId(tenantId, null);
		if (CollectionUtils.isNotEmpty(list)) {
			List<PatientDto> cacheObjs = new ArrayList<>(list.size());
			PatientDto toObj;
			for (PatientPO obj : list) {
				toObj = new PatientDto();
				BeanUtils.copyProperties(obj, toObj);
				cacheObjs.add(toObj);
			}
			PatientCache.cacheAll(cacheObjs);
		}
	}

	@Override
	public void cacheDynamicFormNode(Integer tenantId, String sysOwner) {
		// 删除数据
		RedisCacheUtil.deletePattern(DynamicFormUtil.getKey(tenantId, null));
		// 获取所有的表单列表
		CmFormPO query = new CmFormPO();
		query.setSysOwner(sysOwner);
		List<CmFormPO> formList = cmFormService.selectByCondition(query);
		if (CollectionUtils.isNotEmpty(formList)) {
			HashMap<String, List<FormNodesDto>> map = new HashMap<>();
			HashMap<String, List<FormDto>> categoryMap = new HashMap<>();
			CmFormPO form;
			FormDto cacheForm;
			List<FormDto> categorys;
			String categoryMapKey;
			for (int c = 0; c < formList.size(); c++) {
				form = formList.get(c);
				map.put(DynamicFormUtil.getKey(tenantId, form.getId()), cmFormNodesService.selectByFormId(form.getId()));
				// cache category forms
				categoryMapKey = DynamicFormUtil.getCategoryFormKey(tenantId, form.getSysOwner(), form.getCategory());
				if (!categoryMap.containsKey(categoryMapKey)) {
					categorys = new ArrayList<>();
					categoryMap.put(categoryMapKey, categorys);
				}
				categorys = categoryMap.get(categoryMapKey);
				cacheForm = new FormDto();
				BeanUtils.copyProperties(form, cacheForm);
				categorys.add(cacheForm);
			}
			DynamicFormUtil.cacheCategoryForm(categoryMap);
			DynamicFormUtil.cacheAll(map);
		}
	}

	@Override
	public void cacheAll() {
		LOGGER.info("******************** start cache data ***********");
		long start = System.currentTimeMillis();
		List<SysTenant> tenantList = sysTenantService.selectAll();
		if (CollectionUtils.isNotEmpty(tenantList)) {
			SysTenant tenant;
			for (int i = 0; i < tenantList.size(); i++) {
				tenant = tenantList.get(i);
				UserUtil.setThreadTenant(tenant.getId());
				// first cache sys param
				cacheSysParam(tenant.getId());
				// second cache dict
				cacheDict(tenant.getId());
				// third cache permission
				cachePermission(tenant.getId());
				// fourth cache patient
				cachePatient(tenant.getId());
				// cache dynamic form
				cacheDynamicFormNode(tenant.getId(), null);
			}
		}
		LOGGER.info("******************** end data cache,total cost {} ms ***********", System.currentTimeMillis() - start);
	}
}