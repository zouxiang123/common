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

import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.cache.CmDictCache;
import com.xtt.common.cache.FamilyInitialCache;
import com.xtt.common.cache.FormulaCache;
import com.xtt.common.cache.PatientCache;
import com.xtt.common.cache.TenantAuthorityCache;
import com.xtt.common.cache.UserCache;
import com.xtt.common.common.service.ICmDictService;
import com.xtt.common.common.service.ICmFormNodesService;
import com.xtt.common.common.service.ICommonCacheService;
import com.xtt.common.common.service.IFamilyInitialService;
import com.xtt.common.common.service.ISysParamService;
import com.xtt.common.common.service.ISysTenantService;
import com.xtt.common.conf.service.ICmFormulaConfService;
import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.FamilyInitial;
import com.xtt.common.dao.model.SysObj;
import com.xtt.common.dao.model.SysRole;
import com.xtt.common.dao.model.SysTenant;
import com.xtt.common.dao.po.CmDictPO;
import com.xtt.common.dao.po.CmFormPO;
import com.xtt.common.dao.po.CmFormulaConfPO;
import com.xtt.common.dao.po.PatientPO;
import com.xtt.common.dao.po.SysParamPO;
import com.xtt.common.dao.po.SysUserPO;
import com.xtt.common.dto.DictDto;
import com.xtt.common.dto.FamilyInitialDto;
import com.xtt.common.dto.FormDto;
import com.xtt.common.dto.FormNodesDto;
import com.xtt.common.dto.PatientDto;
import com.xtt.common.dto.SysObjDto;
import com.xtt.common.dto.SysParamDto;
import com.xtt.common.dto.SysUserDto;
import com.xtt.common.form.service.ICmFormService;
import com.xtt.common.patient.service.IPatientService;
import com.xtt.common.permission.PermissionCache;
import com.xtt.common.user.service.IRoleService;
import com.xtt.common.user.service.IUserService;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.DynamicFormUtil;
import com.xtt.common.util.SysParamUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.framework.core.redis.RedisCacheUtil;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.secret.AESUtil;

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
    @Autowired
    private IUserService userService;
    @Autowired
    private ICmFormulaConfService cmFormulaConfService;
    @Autowired
    private IFamilyInitialService familyInitialService;

    @Override
    public void cacheDict(Integer tenantId) {
        RedisCacheUtil.deletePattern(CmDictCache.getKey(tenantId, null));
        List<CmDictPO> list = cmDictService.selectAll(tenantId);
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
        List<SysParamPO> list = sysParamService.getByTenantId(tenantId, null);
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
        RedisCacheUtil.delete(tenantId + PermissionCache.ALL_SYS_OBJ_KEY);
        RedisCacheUtil.deletePattern(PermissionCache.getKey(tenantId, null));
        String[] types = { "1", "2" };
        map.put(tenantId + PermissionCache.ALL_SYS_OBJ_KEY, convertSysObjList(roleService.getAllMenuList(types, null)));
        List<SysRole> list = roleService.getRoleListByTenantId(tenantId, null);
        for (SysRole sysRole : list) {
            Long[] roleIds = { sysRole.getId() };
            map.put(PermissionCache.getKey(tenantId, sysRole.getId()), convertSysObjList(roleService.getMenuListByRoleId(roleIds, types)));
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
        List<SysObjDto> tempList = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            SysObjDto cmSysObj;
            for (SysObj so : list) {// 缓存有需要的数据
                cmSysObj = new SysObjDto();
                cmSysObj.setName(so.getName());
                cmSysObj.setKey(so.getKey());
                cmSysObj.setUrl(so.getUrl());
                cmSysObj.setType(so.getType());
                cmSysObj.setCode(so.getCode());
                cmSysObj.setpCode(so.getpCode());
                cmSysObj.setSysOwner(so.getSysOwner());
                tempList.add(cmSysObj);
            }
            list.clear();
        }
        return tempList;
    }

    @Override
    public void cachePatient() {
        RedisCacheUtil.deletePattern(PatientCache.getKey(null));
        List<PatientPO> list = patientService.listAll();
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
        if (StringUtil.isBlank(sysOwner)) {
            RedisCacheUtil.deletePattern(DynamicFormUtil.getKey(tenantId, null));
        }
        RedisCacheUtil.deletePattern(DynamicFormUtil.getCategoryFormKey(tenantId, sysOwner, null));
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
                map.put(DynamicFormUtil.getKey(tenantId, form.getId()), initDynamicFormNode(cmFormNodesService.selectByFormId(form.getId()),
                                DictUtil.getMapByPItemCode(CmDictConsts.FORM_ITEM_UNIT)));
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

    /**
     * 初始化动态表单数据
     *
     * @return
     *
     */
    private List<FormNodesDto> initDynamicFormNode(List<FormNodesDto> nodes, Map<String, String> unitMap) {
        if (MapUtils.isEmpty(unitMap) || CollectionUtils.isEmpty(nodes)) {
            return nodes;
        }
        for (FormNodesDto node : nodes) {
            node.setUnitShow(unitMap.get(node.getUnit()));
        }
        return nodes;
    }

    @Override
    public void cacheUser() {
        UserCache.clear();
        List<SysUserPO> list = userService.listAll();
        if (CollectionUtils.isNotEmpty(list)) {
            SysUserDto cacheUser;
            List<SysUserDto> cacheList = new ArrayList<>(list.size() + 1);
            // 添加系统用户缓存
            SysUserDto sysUser = new SysUserDto();
            sysUser.setId(CommonConstants.SYSTEM_USER_ID);
            sysUser.setName(CommonConstants.SYSTEM_USER_NAME);
            cacheList.add(sysUser);
            for (SysUserPO user : list) {
                cacheUser = new SysUserDto();
                cacheUser.setSexShow(DictUtil.getItemName(CmDictConsts.SEX, user.getSex(), user.getFkTenantId()));
                BeanUtils.copyProperties(user, cacheUser);
                cacheList.add(cacheUser);
            }
            UserCache.cacheAll(cacheList);
        }
    }

    @Override
    public void cacheAll() {
        LOGGER.info("******************** start cache data ***********");
        long start = System.currentTimeMillis();
        cacheAuthority();
        List<SysTenant> tenantList = sysTenantService.listAllEnable();
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
                // cache dynamic form
                cacheDynamicFormNode(tenant.getId(), null);
                // cache formula data
                cacheFormula(tenant.getId());
            }
            // cache user data
            cacheUser();
            // fourth cache patient
            cachePatient();
        }
        LOGGER.info("******************** end data cache,total cost {} ms ***********", System.currentTimeMillis() - start);
    }

    @Override
    public void cacheFormula(Integer tenantId) {
        RedisCacheUtil.deletePattern(FormulaCache.getKey(tenantId, null));
        CmFormulaConfPO record = new CmFormulaConfPO();
        record.setIsChecked(true);
        record.setIsEnable(true);
        record.setFkTenantId(tenantId);
        List<CmFormulaConfPO> list = cmFormulaConfService.selectByCondition(record);
        Map<String, String> cacheMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (CmFormulaConfPO formula : list) {
                cacheMap.put(FormulaCache.getKey(tenantId, formula.getCategory()), formula.getItemCode());
            }
            FormulaCache.cacheALL(cacheMap);
        }
    }

    @Override
    public void cacheFamilyInitial() {
        RedisCacheUtil.deletePattern(FamilyInitialCache.getKey(null));
        FamilyInitial query = new FamilyInitial();
        List<FamilyInitial> list = familyInitialService.listByCondition(query);
        Map<String, FamilyInitialDto> map = new HashMap<>(list.size());
        for (FamilyInitial record : list) {
            FamilyInitialDto toObj = new FamilyInitialDto();
            BeanUtils.copyProperties(record, toObj);
            map.put(FamilyInitialCache.getKey(record.getName()), toObj);
        }
        FamilyInitialCache.init(map);
    }

    @Override
    public void cacheAuthority(String content) {
        Map<String, String> map = new HashMap<String, String>();
        if (StringUtils.isNotEmpty(content)) {
            String key = AESUtil.decrypt(content);
            String[] lics = key.split(";");
            for (String kv : lics) {
                map.put(kv.split("=")[0], kv.split("=")[1]);
            }
        }
        TenantAuthorityCache.cacheALL(map);
    }

    @Override
    public void cacheAuthority() {
        List<SysTenant> tenantList = sysTenantService.listAllEnable();
        for (SysTenant st : tenantList) {
            String content = st.getLicense();
            cacheAuthority(content);
        }
    }
}
