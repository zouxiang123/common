/**   
 * @Title: UserServiceImpl.java 
 * @Package com.xtt.txgl.system.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年10月10日 下午12:34:06 
 *
 */
package com.xtt.common.user.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.xtt.common.cache.UserCache;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.SysUser2roleMapper;
import com.xtt.common.dao.mapper.SysUserMapper;
import com.xtt.common.dao.model.SysUser;
import com.xtt.common.dao.model.SysUser2role;
import com.xtt.common.dao.po.SysUserPO;
import com.xtt.common.dto.SysUserDto;
import com.xtt.common.user.service.IUserService;
import com.xtt.common.util.BusinessCommonUtil;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.security.MD5Util;

@Service
public class UserServiceImpl implements IUserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUser2roleMapper sysUser2roleMapper;

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
	public SysUserPO selectById(Long userId) {
		return selectById(userId, true);
	}

	/**
	 * 通过id获取用户数据
	 * 
	 * @Title: selectById
	 * @param id
	 * @param fromCache是否从缓存获取
	 * @return
	 *
	 */
	private SysUserPO selectById(Long id, boolean fromCache) {
		if (fromCache) {
			SysUserDto user = UserCache.getById(id);
			if (user == null) {
				return sysUserMapper.selectPOById(id);
			}
			SysUserPO sysUser = new SysUserPO();
			BeanUtils.copyProperties(user, sysUser);
			return sysUser;
		} else {
			return sysUserMapper.selectPOById(id);
		}
	}

	@Override
	public String saveUser(SysUserPO user) {
		if (user.getId() != null) {
			user.setInitial(PinyinHelper.getShortPinyin(user.getName()).substring(0, 1).toUpperCase());
			sysUser2roleMapper.deleteByUserId(user.getId());// 删除原来旧的关联数据
			associationRole(user.getRoleId(), user.getId());// 重新创建关联
			user.setUpdateTime(new Date());
			user.setUpdateUserId(UserUtil.getLoginUserId());
			if (StringUtils.isNotBlank(user.getPassword()))
				user.setPassword(MD5Util.md5(user.getPassword()));
			updateByPrimaryKeySelective(user);// 更新用户数据
		} else {
			DataUtil.setSystemFieldValue(user);
			user.setDelFlag(false);
			user.setFkTenantId(UserUtil.getTenantId());
			user.setInitial(PinyinHelper.getShortPinyin(user.getName()).substring(0, 1).toUpperCase());
			user.setPassword(CommonConstants.DEFAULT_PASSWORD);// 设置默认密码
			sysUserMapper.insert(user);
			associationRole(user.getRoleId(), user.getId());// 创建关联数据
			// 新增用户创建默认头像
			String newFilename = "/" + UserUtil.getTenantId() + "/" + CommonConstants.IMAGE_FILE_PATH + "/" + CommonConstants.USER_IMAGE_FILE_PATH
							+ "/" + user.getId() + ".png";
			String name = user.getName().length() >= 2 ? user.getName().substring(user.getName().length() - 2) : user.getName();
			BusinessCommonUtil.combineImage(name, newFilename);
			user.setImagePath(newFilename);
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
		if (StringUtils.isEmpty(roleId)) {
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

	@Override
	public String uploadImage(MultipartFile image) throws IllegalStateException, IOException {
		String path = BusinessCommonUtil.getFilePath(CommonConstants.IMAGE_FILE_PATH);
		String imgName = image.getOriginalFilename();
		SysUser user = selectById(UserUtil.getLoginUserId(), true);
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
		BusinessCommonUtil.compressPic(path, fileName);// 压缩图片
		user.setImagePath("/" + UserUtil.getTenantId() + "/" + CommonConstants.IMAGE_FILE_PATH + "/" + fileName);
		user.setUpdateTime(new Date());
		updateByPrimaryKeySelective(user);
		return fileName;
	}

	@Override
	public List<SysUserPO> selectByTenantId(Integer tenantId, String sysOwner) {
		return sysUserMapper.selectAllUserByTenantId(tenantId, sysOwner);
	}

	@Override
	public void deleteUserById(Long id) {
		sysUser2roleMapper.deleteByUserId(id);
		SysUser user = selectById(id, true);
		user.setDelFlag(true);
		DataUtil.setSystemFieldValue(user);
		updateByPrimaryKeySelective(user);
	}

	@Override
	public List<SysUserPO> selectUserWithFilter(SysUserPO user) {
		return sysUserMapper.selectUserWithFilter(user);
	}

	@Override
	public SysUser getUserByAccount(String account, Integer tenantId, String sysOwner) {
		return sysUserMapper.selectUserByAccount(account, tenantId, sysOwner);
	}

	@Override
	public SysUserPO login(String account, String password, Integer tenantId, String sysOwner) {
		return sysUserMapper.login(account, password, tenantId, sysOwner);
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
	public void updateUser(SysUserPO user) {
		if (StringUtils.isNotBlank(user.getName()))
			user.setInitial(PinyinHelper.getShortPinyin(user.getName()).substring(0, 1).toUpperCase());
		if (StringUtils.isNotBlank(user.getPassword()))
			user.setPassword(MD5Util.md5(user.getPassword()));
		user.setUpdateTime(new Date());
		user.setUpdateUserId(user.getId());
		updateByPrimaryKeySelective(user);
	}

	@Override
	public void resetUserPassword(Long id) {
		sysUserMapper.updatePassword(id, CommonConstants.DEFAULT_PASSWORD);
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
			if (m.get("roleType").equals(roleType)) {
				return ((Long) m.get("count")).intValue();
			}
		}
		return 0;

	}

	@Override
	public void updatePassword(SysUserPO user) {
		user.setPassword(MD5Util.md5(user.getPassword()));
		user.setUpdateTime(new Date());
		user.setUpdateUserId(UserUtil.getLoginUserId());
		updateByPrimaryKeySelective(user);
	}

	private void updateByPrimaryKeySelective(SysUser user) {
		sysUserMapper.updateByPrimaryKeySelective(user);
		// refresh cache
		SysUserPO sysUserPO = selectById(user.getId(), false);
		SysUserDto cacheUser = new SysUserDto();
		BeanUtils.copyProperties(sysUserPO, cacheUser);
		UserCache.refresh(cacheUser);
	}
}
