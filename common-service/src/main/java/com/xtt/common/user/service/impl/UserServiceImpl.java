/**
 * @Title: UserServiceImpl.java
 * @Package com.xtt.common.system.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce
 * @date: 2015年10月10日 下午12:34:06
 *
 */
package com.xtt.common.user.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.xtt.common.cache.TenantAuthorityCache;
import com.xtt.common.cache.UserCache;
import com.xtt.common.common.service.IFamilyInitialService;
import com.xtt.common.common.service.ISysLogService;
import com.xtt.common.common.service.ISysTenantService;
import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.SysUser2roleMapper;
import com.xtt.common.dao.mapper.SysUserMapper;
import com.xtt.common.dao.model.SysGroupTenant;
import com.xtt.common.dao.model.SysTenant;
import com.xtt.common.dao.model.SysUser;
import com.xtt.common.dao.model.SysUser2role;
import com.xtt.common.dao.model.SysUserTenant;
import com.xtt.common.dao.po.SysUserPO;
import com.xtt.common.dto.DictDto;
import com.xtt.common.dto.LoginUser;
import com.xtt.common.dto.SysUserDto;
import com.xtt.common.user.service.ISysUserTenantService;
import com.xtt.common.user.service.IUserService;
import com.xtt.common.util.BusinessCommonUtil;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.HttpServletUtil;
import com.xtt.common.util.ImageTailorUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.security.MD5Util;

import sun.misc.BASE64Decoder;

@Service
public class UserServiceImpl implements IUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUser2roleMapper sysUser2roleMapper;
    @Autowired
    private ISysUserTenantService sysUserTenantService;
    @Autowired
    private ISysLogService sysLogService;
    @Autowired
    private ISysTenantService sysTenantService;
    @Autowired
    private IFamilyInitialService familyInitialService;

    /**
     * 统计人员信息
     */
    @Override
    public List<SysUserPO> countAllUser(Integer tenantId) {
        return sysUserMapper.countAllUser(tenantId);
    }

    @Override
    public List<SysUserPO> getDoctors(Integer tenantId, String sysOwner) {
        String[] arr = { CommonConstants.ROLE_DOCTOR };
        return sysUserMapper.selectByParentRoleIds(tenantId, arr, sysOwner);
    }

    @Override
    public List<SysUserPO> getNurses(Integer tenantId, String sysOwner) {
        String[] arr = { CommonConstants.ROLE_NURSE };
        return sysUserMapper.selectByParentRoleIds(tenantId, arr, sysOwner);
    }

    @Override
    public List<SysUserPO> getNurseAndDoctor(Integer tenantId, String sysOwner) {
        String[] arr = { CommonConstants.ROLE_NURSE, CommonConstants.ROLE_DOCTOR };
        return sysUserMapper.selectByParentRoleIds(tenantId, arr, sysOwner);
    }

    @Override
    public List<SysUserPO> listByRoleTypes(Integer tenantId, String[] arr, String sysOwner) {
        if (arr != null && arr.length > 0) {
            return sysUserMapper.selectByParentRoleIds(tenantId, arr, sysOwner);
        }
        return new ArrayList<>();
    }

    @Override
    public SysUserPO selectById(Long userId) {
        return sysUserMapper.getById(userId);
    }

    @Override
    public String saveUser(SysUserPO user) {
        if (StringUtil.isNotBlank(user.getName())) {
            user.setName(user.getName().trim());
            user.setInitial(familyInitialService.getInitial(user.getName().substring(0, 1)));
        }
        // 是否是维护集团用户标识
        boolean groupFlag = user.getGroupFlag() == null || !user.getGroupFlag() ? false : true;
        if (user.getId() != null) {
            if (!groupFlag && StringUtil.isNotBlank(user.getRoleId())) {// 非集团用户且角色id不为空时，才需建立用户和角色的关联
                sysUser2roleMapper.deleteByUserId(user.getId(), UserUtil.getTenantId(), UserUtil.getSysOwner());// 删除原来旧的关联数据
                associationRole(user.getRoleId(), user.getId());// 重新创建关联
            }
            // 建立用户和租户之间的关联
            associationTenant(user, false, groupFlag);
            user.setUpdateTime(new Date());
            user.setUpdateUserId(UserUtil.getLoginUserId());
            if (StringUtil.isNotBlank(user.getPassword())) {
                user.setPassword(MD5Util.md5(user.getPassword()));
            }
            updateByPrimaryKeySelective(user);// 更新用户数据
        } else {
            if (user.getSkin() == null) {
                user.setSkin(CommonConstants.USER_SKIN_DEFAULT);
            }
            DataUtil.setSystemFieldValue(user);
            user.setDelFlag(false);
            if (user.getFkTenantId() == null) {
                user.setFkTenantId(UserUtil.getTenantId());
            }
            /*            user.setPassword(MD5Util.md5(CommonConstants.DEFAULT_PASSWORD));// 设置默认密码
            */
            if (StringUtil.isNotBlank(user.getPassword())) {
                user.setPassword(MD5Util.md5(user.getPassword()));
            } else {
                user.setPassword(MD5Util.md5(CommonConstants.DEFAULT_PASSWORD));
            }
            if (!groupFlag) {
                // 设置关联的租户为当前租户
                if (StringUtil.isBlank(user.getMultiTenant())) {
                    user.setMultiTenant(String.valueOf(user.getFkTenantId()));
                }
                user.setUserType(CommonConstants.USER_TYPE_NORMAL);
            } else {
                user.setUserType(CommonConstants.USER_TYPE_GROUP);
            }
            String newFilename;
            sysUserMapper.insert(user);
            if (user.getSex() == null && user.getMobile() == null) {
                newFilename = "/" + user.getFkTenantId() + "/" + CommonConstants.IMAGE_FILE_PATH + "/" + CommonConstants.USER_IMAGE_FILE_PATH + "/"
                                + user.getId() + ".png";
            } else {
                newFilename = "/" + UserUtil.getTenantId() + "/" + CommonConstants.IMAGE_FILE_PATH + "/" + CommonConstants.USER_IMAGE_FILE_PATH + "/"
                                + user.getId() + ".png";
            }

            if (!groupFlag) {// 非集团用户保存才需建立用户和角色的关联
                associationRole(user.getRoleId(), user.getId());// 创建关联数据
            }
            // 建立用户和租户之间的关联
            associationTenant(user, true, groupFlag);
            // 新增用户创建默认头像
            Long timeStamp = System.currentTimeMillis();

            String name = user.getName().length() >= 2 ? user.getName().substring(user.getName().length() - 2) : user.getName();
            BusinessCommonUtil.combineImage(name, newFilename);
            user.setImagePath(newFilename + "?t=" + timeStamp);
            updateByPrimaryKeySelective(user);
        }
        return CommonConstants.SUCCESS;
    }

    /**
     * 插入用户和角色关联表数据
     *
     * @Title: associationRole
     * @param roleId
     * @param userId
     *
     */
    private void associationRole(String roleId, Long userId) {
        if (StringUtil.isEmpty(roleId)) {
            return;
        }
        SysUser2role user2Role;
        String[] rIds = roleId.split(",");
        Long rId;
        for (String id : rIds) {
            rId = Long.valueOf(id);
            user2Role = new SysUser2role();
            user2Role.setFkRoleId(rId);
            user2Role.setFkUserId(userId);
            DataUtil.setSystemFieldValue(user2Role);
            sysUser2roleMapper.insert(user2Role);// 插入用户及角色关联数据
        }
    }

    /**
     * 建立用户和租户之间的数据关联
     *
     * @Title: associationTenant
     * @param user
     *            用户对象
     * @param isNewUser
     *            是否是新增用户
     * @param groupFlag
     *            维护集团用户操作
     *
     */
    private void associationTenant(SysUserPO user, boolean isNewUser, boolean groupFlag) {
        // 关联的的医院不能为空
        if (StringUtil.isEmpty(user.getMultiTenant())) {
            return;
        }
        String[] tenantIdStr = user.getMultiTenant().split(",");
        // 转换成list
        final List<Integer> tenantIds = new ArrayList<>(tenantIdStr.length);
        for (int i = 0; i < tenantIdStr.length; i++) {
            tenantIds.add(Integer.valueOf(tenantIdStr[i]));
        }
        // 维护集团用户操作
        if (groupFlag) {
            if (!isNewUser) {// 修改集团用户数据，检查是否存在关联数据，已存在的不需要重新建立，删除不需要的
                List<SysUserTenant> list = sysUserTenantService.listByUserId(user.getId());
                // 关联的租户id
                Set<Integer> existsTenantIds = new HashSet<>(list.size());
                // 需要删除的关联记录id
                List<Long> removeIds = new ArrayList<>(list.size());
                if (CollectionUtils.isNotEmpty(list)) {
                    list.forEach(ut -> {
                        existsTenantIds.add(ut.getFkTenantId());
                        if (!tenantIds.contains(ut.getFkTenantId())) {
                            removeIds.add(ut.getId());
                        }
                    });
                }
                tenantIds.removeAll(existsTenantIds);
                // 如果需要删除的关联记录id不为空
                if (removeIds.size() > 0) {
                    sysUserTenantService.removeByIds(removeIds);
                }
            }
            // 保存用户和租户之间的关联数据
            saveSysUserTenant(tenantIds, user);
        } else {// 维护本租户用户
            if (!isNewUser) {
                SysUserTenant ut = sysUserTenantService.getByUserId(user.getId());
                if (ut != null) {
                    SysUserTenant sysUserTenant = new SysUserTenant();
                    sysUserTenant.setId(ut.getId());
                    sysUserTenant.setRoleType(user.getRoleType());
                    sysUserTenant.setPosition(user.getPosition());
                    sysUserTenant.setTelephone(user.getTelephone());
                    sysUserTenant.setUpdateTime(new Date());
                    sysUserTenant.setUpdateUserId(UserUtil.getLoginUserId());
                    sysUserTenantService.updateByPrimaryKeySelective(sysUserTenant);
                }
            } else {
                // 保存用户和租户之间的关联数据
                saveSysUserTenant(tenantIds, user);
            }
        }
    }

    /**
     * 生成用户、系统、租户之间的关联数据
     *
     * @Title: saveSysUserTenant
     * @param tenantIds
     * @param user
     *
     */
    private void saveSysUserTenant(Collection<Integer> tenantIds, SysUserPO user) {
        if (CollectionUtils.isNotEmpty(tenantIds)) {
            SysUserTenant record;
            List<SysUserTenant> saveList = new ArrayList<>();
            for (Integer tenantId : tenantIds) {
                // 生成该租户下所有系统的关联数据
                List<DictDto> systems = DictUtil.listByPItemCode(CmDictConsts.SYS_OWNER, tenantId);
                if (CollectionUtils.isNotEmpty(systems)) {
                    for (DictDto dict : systems) {
                        record = new SysUserTenant();
                        record.setSkin(user.getSkin() == null ? CommonConstants.USER_SKIN_DEFAULT : user.getSkin());// 设置默认皮肤
                        record.setRoleType(user.getRoleType());
                        record.setPosition(user.getPosition());
                        record.setTelephone(user.getTelephone());
                        record.setFkTenantId(tenantId);
                        record.setFkUserId(user.getId());
                        record.setSysOwner(dict.getItemCode());
                        DataUtil.setSystemFieldValue(record);
                        saveList.add(record);
                    }
                }
            }
            if (saveList.size() > 0) {
                sysUserTenantService.saveBatch(saveList);
            }
        }

    }

    @Override
    public String uploadImage(MultipartFile image) throws IllegalStateException, IOException {
        String path = BusinessCommonUtil.getFilePath(CommonConstants.IMAGE_FILE_PATH);
        String imgName = image.getOriginalFilename();
        SysUser user = selectById(UserUtil.getLoginUserId());
        String fileName = CommonConstants.USER_IMAGE_FILE_PATH + "/" + String.valueOf(user.getId())
                        + imgName.substring(imgName.lastIndexOf("."), imgName.length());
        File f = new File(path + fileName);
        if (!f.exists()) {
            f.mkdirs();
        }
        image.transferTo(f);
        try {// save original drawing
            String originalFileName = CommonConstants.USER_IMAGE_FILE_PATH + "/original/" + String.valueOf(user.getId())
                            + imgName.substring(imgName.lastIndexOf("."), imgName.length());
            FileUtils.copyFile(f, new File(path + originalFileName));
        } catch (Exception e) {
            LOGGER.info("save original drawing failed,error ms is", e);
        }
        Long timeStamp = System.currentTimeMillis();
        BusinessCommonUtil.compressPic(path, fileName);// 压缩图片
        user.setImagePath("/" + UserUtil.getTenantId() + "/" + CommonConstants.IMAGE_FILE_PATH + "/" + fileName + "?t=" + timeStamp);
        user.setUpdateTime(new Date());
        updateByPrimaryKeySelective(user);
        // 重新更新当前用户的头像
        LoginUser loginUser = UserUtil.getLoginUser();
        loginUser.setImagePath(user.getImagePath());
        UserUtil.setLoginUser(loginUser);
        return user.getImagePath();
    }

    @Override
    public List<SysUserPO> selectByTenantId(Integer tenantId, String sysOwner) {
        return listByTenantId(tenantId, sysOwner, false);
    }

    @Override
    public void deleteUserById(Long id) {
        sysUser2roleMapper.deleteByUserId(id, null, null);
        SysUser user = new SysUser();
        user.setId(id);
        user.setDelFlag(true);
        user.setUpdateTime(new Date());
        user.setUpdateUserId(UserUtil.getLoginUserId());
        updateByPrimaryKeySelective(user);
    }

    @Override
    public List<SysUserPO> selectUserWithFilter(SysUserPO user) {
        return sysUserMapper.selectUserWithFilter(user);
    }

    @Override
    public SysUser getUserByAccount(String account, Integer tenantId, String sysOwner, boolean groupFlag) {
        if (!groupFlag) {
            return sysUserMapper.selectUserByAccount(account, tenantId, sysOwner);
        }
        return sysUserMapper.getGroupUserByAccount(account, CommonConstants.USER_TYPE_GROUP, tenantId);
    }

    @Override
    public List<SysUserPO> checkUser(SysUserPO user) {

        return sysUserMapper.checkUser(user);
    }

    private SysUserPO login(String account, String password, Integer tenantId, String sysOwner) {
        SysUserPO user = sysUserMapper.login(account, password, tenantId, sysOwner);
        // 设置当前用户关联的租户和关联的系统
        if (user != null) {
            List<SysUserTenant> utList = sysUserTenantService.listByUserId(user.getId());
            if (CollectionUtils.isNotEmpty(utList)) {
                List<Integer> tenantIds = new ArrayList<>(utList.size());
                StringBuilder tenantSB = new StringBuilder();
                Set<String> sysOwners = new HashSet<>(utList.size());
                StringBuilder ownerSB = new StringBuilder();
                utList.forEach(ut -> {
                    if (!tenantIds.contains(ut.getFkTenantId())) {
                        tenantSB.append(",");
                        tenantSB.append(ut.getFkTenantId());
                        tenantIds.add(ut.getFkTenantId());
                    }
                    if (!sysOwners.contains(ut.getSysOwner())) {
                        ownerSB.append(",");
                        ownerSB.append(ut.getSysOwner());
                        sysOwners.add(ut.getSysOwner());
                    }
                });
                user.setMultiTenant(tenantSB.substring(1));
                user.setSysOwner(ownerSB.substring(1));
            }
        }
        return user;
    }

    @Override
    public Integer getDoctorsCount(Integer tenantId, String sysOwner) {
        return getCountByRoleType(tenantId, CommonConstants.ROLE_DOCTOR, sysOwner);
    }

    @Override
    public Integer getNursesCount(Integer tenantId, String sysOwner) {
        return getCountByRoleType(tenantId, CommonConstants.ROLE_NURSE, sysOwner);
    }

    @Override
    public int updateUserBasicInfo(SysUser user) {
        if (StringUtil.isNotBlank(user.getName())) {
            user.setInitial(PinyinHelper.getShortPinyin(user.getName()).substring(0, 1).toUpperCase());
        }
        if (StringUtil.isNotBlank(user.getPassword())) {
            user.setPassword(MD5Util.md5(user.getPassword()));
        }
        user.setUpdateTime(new Date());
        user.setUpdateUserId(user.getId());
        return updateByPrimaryKeySelective(user);
    }

    @Override
    public void resetUserPassword(Long id) {
        SysUserPO user = new SysUserPO();
        user.setId(id);
        user.setPassword(CommonConstants.DEFAULT_PASSWORD);
        updatePassword(user);
    }

    @Override
    public List<SysUserPO> getOthers(Integer tenantId, String sysOwner) {
        String[] arr = { CommonConstants.ROLE_OTHER, CommonConstants.ROLE_ADMIN };
        return sysUserMapper.selectByParentRoleIds(tenantId, arr, sysOwner);
    }

    @Override
    public List<Map<String, Object>> getRolesCount(Integer tenantId, String sysOwner) {
        return sysUserMapper.selectRolesCount(tenantId, sysOwner);
    }

    private Integer getCountByRoleType(Integer tenantId, String roleType, String sysOwner) {
        List<Map<String, Object>> list = sysUserMapper.selectRolesCount(tenantId, sysOwner);
        for (Map<String, Object> m : list) {
            if (Objects.equals(m.get("roleType"), roleType)) {
                return ((Long) m.get("count")).intValue();
            }
        }
        return 0;

    }

    @Override
    public int updatePassword(SysUserPO user) {
        user.setPassword(MD5Util.md5(user.getPassword()));
        user.setUpdateTime(new Date());
        user.setUpdateUserId(UserUtil.getLoginUserId());
        return updateByPrimaryKeySelective(user);
    }

    @Override
    public int updateByPrimaryKeySelective(SysUser user) {
        int count = sysUserMapper.updateByPrimaryKeySelective(user);
        // refresh cache
        SysUser sysUser = selectById(user.getId());
        SysUserDto cacheUser = new SysUserDto();
        BeanUtils.copyProperties(sysUser, cacheUser);
        UserCache.refresh(cacheUser);
        return count;
    }

    @Override
    public List<SysUserPO> listAll() {
        return sysUserMapper.listAll();
    }

    @Override
    public SysUserPO getFullById(Long id, String sysOwner, Integer tenantId, boolean groupFlag) {
        if (!groupFlag) {
            return sysUserMapper.getFullById(id, sysOwner, tenantId);
        }
        return sysUserMapper.getGroupFullById(id, tenantId);
    }

    public SysUserPO groupAdminLogin(String account, String password, Integer tenantId) {
        SysUserPO user = sysUserMapper.groupAdminLogin(account, password, tenantId, CommonConstants.USER_TYPE_GROUP_ADMIN);
        return user;
    }

    @Override
    public Map<String, Object> loginSubmit(String account, String password, Integer tenantId, Boolean groupAdmin, String sysOwner, Boolean isSwitch)
                    throws Exception {
        Map<String, Object> map = new HashMap<>();
        SysTenant st = sysTenantService.getById(tenantId);
        // 非集团管理员需要校验权限
        if (st != null && !st.getGroupFlag()) {
            UserUtil.setThreadTenant(tenantId);
            if (TenantAuthorityCache.getValue(CommonConstants.SYS_HD) == null) {
                map.put(CommonConstants.API_ERROR_MESSAGE, "当前医院没有权限使用该系统，请联系管理员！");
                map.put(CommonConstants.API_STATUS, CommonConstants.FAILURE);
                return map;
            }
        }
        if (StringUtil.isNotBlank(account) && StringUtil.isNotBlank(password) && tenantId != null) {
            // 如果不是切换系统，且密码不为空，md5 password
            /*  if (isSwitch == null && StringUtil.isNotBlank(password)) {
                password = MD5Util.md5(password);
            }*/
            boolean isGroupAdmin = groupAdmin == null ? false : groupAdmin;
            SysUserPO sysUser = null;
            if (!isGroupAdmin) {
                sysUser = login(StringUtil.trim(account), password, tenantId, sysOwner);
            } else {
                sysUser = groupAdminLogin(StringUtil.trim(account), password, tenantId);
            }
            if (sysUser != null) {

                String token = UUID.randomUUID().toString();
                HttpServletUtil.getRequest().setAttribute(CommonConstants.COOKIE_TOKEN, token);

                LoginUser loginUser = new LoginUser();
                loginUser.setId(sysUser.getId());
                loginUser.setAccount(account);
                loginUser.setName(sysUser.getName());
                loginUser.setSex(sysUser.getSex());
                loginUser.setTenantId(tenantId);
                loginUser.setImagePath(sysUser.getImagePath());
                loginUser.setRoleId(sysUser.getRoleId());
                loginUser.setPosition(sysUser.getPosition());
                loginUser.setSysOwner(sysOwner);
                loginUser.setRoleType(sysUser.getRoleType());
                loginUser.setMultiSysOwner(sysUser.getSysOwner());
                loginUser.setMultiTenant(sysUser.getMultiTenant());
                loginUser.setUserType(sysUser.getUserType());
                loginUser.setSkin(sysUser.getSkin());
                UserUtil.setLoginUser(token, loginUser);
                if (!isGroupAdmin) {// 集团管理员不需要设置角色相关信息
                    UserUtil.setNonPermissionList(sysUser.getRoleId());// 设置没有权限的菜单列表
                    UserUtil.setPermission(sysUser.getRoleId());// 设置有权限的菜单列表
                    // 设置用户职称
                    if (Objects.equals(CommonConstants.ROLE_DOCTOR, sysUser.getRoleType())) {
                        loginUser.setPositionShow(DictUtil.getItemName(CmDictConsts.DOCTOR_PROFESSIONAL_TITLE, loginUser.getPosition()));
                    } else if (Objects.equals(CommonConstants.ROLE_NURSE, sysUser.getRoleType())) {
                        loginUser.setPositionShow(DictUtil.getItemName(CmDictConsts.NURSE_PROFESSIONAL_TITLE, loginUser.getPosition()));
                    } else {
                        loginUser.setPositionShow(sysUser.getPosition());
                    }
                    SysGroupTenant sgt = sysTenantService.getSysGroupTenantByFkTenantId(tenantId);
                    List<SysTenant> stList = sysTenantService.listAllNormalByGroupId(sgt.getFkGroupId(), null);
                    if (CollectionUtils.isNotEmpty(stList)) {
                        StringBuilder sts = new StringBuilder();
                        stList.forEach(tenant -> {
                            sts.append(",").append(tenant.getId());
                        });
                        loginUser.setGroupTenant(sts.toString().substring(1));
                    }
                    // refresh redis cache
                    UserUtil.setLoginUser(loginUser);
                }
                sysLogService.insertSysLog(CommonConstants.SYS_LOG_TYPE_2, "登陆成功", sysOwner);
                map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
                map.put(CommonConstants.COOKIE_TOKEN, token);
            } else {
                map.put("account", account);
                map.put("tenantId", tenantId);
                map.put(CommonConstants.API_ERROR_MESSAGE, "用户名或密码错误！");
                map.put(CommonConstants.API_STATUS, CommonConstants.FAILURE);
            }
        } else {
            map.put(CommonConstants.API_ERROR_MESSAGE, "用户名或密码不能为空！");
            map.put(CommonConstants.API_STATUS, CommonConstants.FAILURE);
        }
        return map;
    }

    @Override
    public SysUser getGroupAdminByAccount(String account) {
        SysUser user = sysUserMapper.getGroupUserByAccount(account, CommonConstants.USER_TYPE_GROUP_ADMIN, null);
        return user;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<Integer, Map<String, Object>> getTenantsAndSysOwnersById(Long id) {
        List<SysUserTenant> list = sysUserTenantService.listByUserId(id);
        Map<Integer, Map<String, Object>> map = new HashMap<>();
        list.forEach(ut -> {
            Map<String, Object> tenantMap = map.get(ut.getFkTenantId());
            Map<String, String> ownerMap;
            if (tenantMap == null) {
                tenantMap = new HashMap<>();
                tenantMap.put("name", sysTenantService.getById(ut.getFkTenantId()).getName());
                ownerMap = new LinkedHashMap<>();
                tenantMap.put("owners", ownerMap);
                map.put(ut.getFkTenantId(), tenantMap);
            } else {
                ownerMap = (Map<String, String>) tenantMap.get("owners");
            }
            ownerMap.put(ut.getSysOwner(), DictUtil.getItemName(CmDictConsts.SYS_OWNER, ut.getSysOwner(), ut.getFkTenantId()));
        });
        return map;
    }

    @Override
    public String uploadAutograph(MultipartFile image, int x, int y, int width, int height) throws IllegalStateException, IOException {
        String path = BusinessCommonUtil.getFilePath(CommonConstants.IMAGE_FILE_PATH);
        String imgName = image.getOriginalFilename();
        SysUser user = selectById(UserUtil.getLoginUserId());
        String imgPath = CommonConstants.USER_IMAGE_FILE_PATH + "/" + CommonConstants.USER_AUTOGRAPH_FILE_PATH + "/";
        String fileName = String.valueOf(user.getId()) + imgName.substring(imgName.lastIndexOf("."), imgName.length());
        String originalImgPath = imgPath + "original/";
        try {// save original drawing
            File originalFile = new File(path + originalImgPath + fileName);
            if (!originalFile.exists()) {
                originalFile.mkdirs();
            } else {
                originalFile.delete();
            }
            image.transferTo(originalFile);
        } catch (Exception e) {
            LOGGER.info("save original drawing failed,error ms is", e);
        }
        String outputDir = path + originalImgPath;
        // 根据处理后的图片进行裁剪
        String destImgDir = path + imgPath;
        boolean isOk = new ImageTailorUtil().cutImage(outputDir + fileName, destImgDir, fileName, x, y, width, height);
        if (isOk) {
            user.setAutographPath("/" + UserUtil.getTenantId() + "/" + CommonConstants.IMAGE_FILE_PATH + "/" + imgPath + fileName);
            user.setUpdateTime(new Date());
            updateByPrimaryKeySelective(user);
            return user.getAutographPath();
        }
        return "";
    }

    /**
     * 对字节数组字符串进行Base64解码并生成图片
     * 
     * @param imgStr
     * @param imgFilePath
     * @return
     */
    private boolean GenerateImage(String imgStr, String imgFilePath) {
        if (imgStr == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<SysUserPO> listByTenantId(Integer tenantId, String sysOwner, Boolean delFlag) {
        return sysUserMapper.listByTenantId(tenantId, sysOwner, delFlag);
    }

    @Override
    public List<SysUserPO> listByAccount(SysUserPO user) {
        return sysUserMapper.listByAccount(user);
    }

    @Override
    public void saveSkin(String skin) {
        SysUserTenant sut = sysUserTenantService.getByUserId(UserUtil.getLoginUserId());
        sut.setSkin(skin);
        DataUtil.setUpdateSystemFieldValue(sut);
        sysUserTenantService.updateByPrimaryKeySelective(sut);
        // refresh redis cache
        LoginUser loginUser = UserUtil.getLoginUser();
        loginUser.setSkin(skin);
        UserUtil.setLoginUser(loginUser);
    }

    @Override
    public SysUser getRoundUser(Integer constantType) {
        return sysUserMapper.getRoundUser(constantType, UserUtil.getTenantId());
    }

    @Override
    public List<SysUser> listUserByParentId(Integer constantType) {
        return sysUserMapper.listUserByParentId(constantType, UserUtil.getTenantId());
    }
}
