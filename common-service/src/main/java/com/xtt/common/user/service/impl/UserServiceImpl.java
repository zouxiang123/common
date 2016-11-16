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

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.SysUser2roleMapper;
import com.xtt.common.dao.mapper.SysUserMapper;
import com.xtt.common.dao.model.SysUser;
import com.xtt.common.dao.model.SysUser2role;
import com.xtt.common.dao.po.SysUserPO;
import com.xtt.common.user.service.IUserService;
import com.xtt.common.util.BusinessCommonUtil;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.HttpServletUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.security.MD5Util;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUser2roleMapper sysUser2roleMapper;

	@Override
	public List<SysUserPO> getDoctors(Integer tenantId) {
		String[] arr = { CommonConstants.ROLE_DOCTOR };
		return sysUserMapper.selectByParentRoleIds(tenantId, arr, HttpServletUtil.getProjectName());
	}

	@Override
	public List<SysUserPO> getNurses(Integer tenantId) {
		String[] arr = { CommonConstants.ROLE_NURSE };
		return sysUserMapper.selectByParentRoleIds(tenantId, arr, HttpServletUtil.getProjectName());
	}

	@Override
	public SysUserPO getUserById(Long userId) {
		return sysUserMapper.selectPOById(userId);
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
			sysUserMapper.updateByPrimaryKeySelective(user);// 更新用户数据
		} else {
			DataUtil.setSystemFieldValue(user);
			user.setDelFlag(false);
			user.setFkTenantId(UserUtil.getTenantId());
			user.setInitial(PinyinHelper.getShortPinyin(user.getName()).substring(0, 1).toUpperCase());
			user.setPassword(CommonConstants.DEFAULT_PASSWORD);// 设置默认密码
			if (StringUtil.isBlank(user.getSysOwner())) {// 项目名称为空时，默认为当前系统
				user.setSysOwner(HttpServletUtil.getProjectName());
			}
			sysUserMapper.insert(user);
			associationRole(user.getRoleId(), user.getId());// 创建关联数据
			// 新增用户创建默认头像
			String newFilename = "/" + UserUtil.getTenantId() + "/" + CommonConstants.IMAGE_FILE_PATH + "/" + CommonConstants.USER_IMAGE_FILE_PATH
							+ "/" + user.getId() + ".png";
			String name = user.getName().length() >= 2 ? user.getName().substring(user.getName().length() - 2) : user.getName();
			BusinessCommonUtil.combineImage(name, newFilename);
			user.setImagePath(newFilename);
			sysUserMapper.updateByPrimaryKeySelective(user);
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
		String fileName = CommonConstants.USER_IMAGE_FILE_PATH + "/" + String.valueOf(UserUtil.getLoginUserId())
						+ imgName.substring(imgName.lastIndexOf("."), imgName.length());
		File f = new File(path + fileName);
		if (!f.exists()) {
			f.mkdirs();
		}
		image.transferTo(f);
		BusinessCommonUtil.compressPic(path, fileName);// 压缩图片
		SysUser user = sysUserMapper.selectByPrimaryKey(UserUtil.getLoginUserId());
		user.setImagePath("/" + UserUtil.getTenantId() + "/" + CommonConstants.IMAGE_FILE_PATH + "/" + fileName);
		user.setUpdateTime(new Date());
		sysUserMapper.updateByPrimaryKeySelective(user);
		return fileName;
	}

	@Override
	public List<SysUserPO> selectByTenantId(Integer tenantId) {
		return sysUserMapper.selectAllUserByTenantId(tenantId, HttpServletUtil.getProjectName());
	}

	@Override
	public void deleteUserById(Long id) {
		sysUser2roleMapper.deleteByUserId(id);
		SysUser user = sysUserMapper.selectByPrimaryKey(id);
		user.setDelFlag(true);
		DataUtil.setSystemFieldValue(user);
		sysUserMapper.updateByPrimaryKey(user);
	}

	@Override
	public List<SysUserPO> selectUserWithFilter(SysUserPO user) {
		user.setSysOwner(HttpServletUtil.getProjectName());
		return sysUserMapper.selectUserWithFilter(user);
	}

	@Override
	public SysUser getUserByAccount(String account, Integer tenantId) {
		return sysUserMapper.selectUserByAccount(account, tenantId, HttpServletUtil.getProjectName());
	}

	@Override
	public SysUserPO login(String account, String password, Integer tenantId) {
		return sysUserMapper.login(account, password, tenantId, HttpServletUtil.getProjectName());
	}

	@Override
	public Integer getDoctorsCount(Integer tenantId) {
		return getCountByRoleType(tenantId, CommonConstants.ROLE_DOCTOR);
	}

	@Override
	public Integer getNursesCount(Integer tenantId) {
		return getCountByRoleType(tenantId, CommonConstants.ROLE_NURSE);
	}

	@Override
	public void updateUser(SysUserPO user) {
		user.setInitial(PinyinHelper.getShortPinyin(user.getName()).substring(0, 1).toUpperCase());
		if (StringUtils.isNotBlank(user.getPassword()))
			user.setPassword(MD5Util.md5(user.getPassword()));
		user.setUpdateTime(new Date());
		user.setUpdateUserId(user.getId());
		sysUserMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public void resetUserPassword(Long id) {
		sysUserMapper.updatePassword(id, CommonConstants.DEFAULT_PASSWORD);
	}

	@Override
	public List<SysUserPO> getOthers(Integer tenantId) {
		String[] arr = { CommonConstants.ROLE_OTHER, CommonConstants.ROLE_ADMIN };
		return sysUserMapper.selectByParentRoleIds(tenantId, arr, HttpServletUtil.getProjectName());
	}

	@Override
	public List<Map<String, Object>> getRolesCount(Integer tenantId) {
		return sysUserMapper.selectRolesCount(tenantId, HttpServletUtil.getProjectName());
	}

	private Integer getCountByRoleType(Integer tenantId, String roleType) {
		List<Map<String, Object>> list = sysUserMapper.selectRolesCount(tenantId, HttpServletUtil.getProjectName());
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
		sysUserMapper.updateByPrimaryKeySelective(user);
	}

}
