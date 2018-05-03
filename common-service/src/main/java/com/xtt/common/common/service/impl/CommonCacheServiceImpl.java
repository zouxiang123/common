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
import com.xtt.common.cache.TenantCache;
import com.xtt.common.cache.UserCache;
import com.xtt.common.common.service.ICmDictService;
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
import com.xtt.common.dao.po.CmFormulaConfPO;
import com.xtt.common.dao.po.PatientPO;
import com.xtt.common.dao.po.SysParamPO;
import com.xtt.common.dao.po.SysUserPO;
import com.xtt.common.dto.DictDto;
import com.xtt.common.dto.FamilyInitialDto;
import com.xtt.common.dto.PatientDto;
import com.xtt.common.dto.SysObjDto;
import com.xtt.common.dto.SysParamDto;
import com.xtt.common.dto.SysUserDto;
import com.xtt.common.dto.TenantDto;
import com.xtt.common.patient.service.IPatientService;
import com.xtt.common.permission.PermissionCache;
import com.xtt.common.user.service.IRoleService;
import com.xtt.common.user.service.IUserService;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.SysParamUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.framework.core.redis.RedisCacheUtil;
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
    private ISysTenantService sysTenantService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ICmFormulaConfService cmFormulaConfService;
    @Autowired
    private IFamilyInitialService familyInitialService;
    @Autowired
    private IPatientService patientService;

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
    public void cachePatient(Integer tenantId) {
        RedisCacheUtil.deletePattern(PatientCache.getKey(null));
        List<PatientPO> list = patientService.getPatientByTenantId(tenantId, null);
        if (CollectionUtils.isNotEmpty(list)) {
            List<PatientDto> cacheObjs = new ArrayList<>(list.size());
            PatientDto toObj;
            Map<String, String> sexMap = DictUtil.getMapByPItemCode(CmDictConsts.SEX);
            for (PatientPO obj : list) {
                toObj = new PatientDto();
                BeanUtils.copyProperties(obj, toObj);
                toObj.setSexShow(sexMap.get(toObj.getSex()));
                cacheObjs.add(toObj);
            }
            PatientCache.cacheAll(cacheObjs);
        }
    }

    @Override
    public void cacheUser(Integer tenantId) {
        RedisCacheUtil.deletePattern(UserCache.getKey(tenantId, null));
        List<SysUserPO> list = userService.listByTenantId(tenantId, null, null);
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
        List<SysTenant> tenantList = sysTenantService.selectAll();
        // Cache family initial
        cacheFamilyInitial();
        // cache sys tenant
        cacheTenant();
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
                // cache user data
                cacheUser(tenant.getId());
                // cache formula data
                cacheFormula(tenant.getId());
            }
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
        List<SysTenant> tenantList = sysTenantService.selectAll();
        for (SysTenant st : tenantList) {
            String content = st.getLicense();
            cacheAuthority(content);
        }
    }

    @Override
    public void cacheTenant() {
        RedisCacheUtil.deletePattern(TenantCache.getKey(null));
        List<SysTenant> tenantList = sysTenantService.selectAll();
        if (CollectionUtils.isNotEmpty(tenantList)) {
            List<TenantDto> cacheList = new ArrayList<>(tenantList.size());
            tenantList.forEach(source -> {
                TenantDto target = new TenantDto();
                BeanUtils.copyProperties(source, target);
                cacheList.add(target);
            });
            TenantCache.cacheAll(cacheList);
        }
    }
}
