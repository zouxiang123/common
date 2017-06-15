/**
 * @Title: SysTenantServiceImpl.java
 * @Package com.xtt.common.system.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce
 * @date: 2016年3月3日 下午1:40:07
 *
 */
package com.xtt.common.common.service.impl;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.xtt.common.common.service.ISysTenantService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.SysGroupMapper;
import com.xtt.common.dao.mapper.SysGroupTenantMapper;
import com.xtt.common.dao.mapper.SysTenantMapper;
import com.xtt.common.dao.model.SysGroup;
import com.xtt.common.dao.model.SysGroupTenant;
import com.xtt.common.dao.model.SysTenant;
import com.xtt.common.dao.po.SysTenantPO;
import com.xtt.common.dao.po.SysUserPO;
import com.xtt.common.user.service.IUserService;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.time.DateFormatUtil;
import com.xtt.platform.util.time.DateUtil;

@Service
public class SysTenantServiceImpl implements ISysTenantService {
	@Autowired
	private SysTenantMapper sysTenantMapper;
	@Autowired
	private SysGroupMapper sysGroupMapper;

	@Autowired
	private SysGroupTenantMapper sysGroupTenantMapper;

	@Autowired
	private IUserService userService;

	@Value("${url}")
	private String dbUrl;

	@Override
	public SysTenant getById(Integer id) {
		return sysTenantMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SysTenant> listAllEnable() {
		SysTenant record = new SysTenant();
		record.setIsEnable(true);
		return listByCondition(record);
	}

	public List<SysTenant> listByCondition(SysTenant record) {
		return sysTenantMapper.listByCondition(record);
	}

	@Override
	public List<SysTenant> listByAccount(String account, String sysOwner) {
		return sysTenantMapper.listByAccount(account, sysOwner);
	}

	@Override
	public List<SysTenant> listByGroupId(Integer groupId) {
		return sysTenantMapper.listByGroupId(groupId);
	}

	@Override
	public List<SysTenant> listAllEnableNormal() {
		SysTenant record = new SysTenant();
		record.setIsEnable(true);
		record.setGroupFlag(false);
		return listByCondition(record);
	}

	@Override
	public SysTenant getDefault() {
		SysTenant record = new SysTenant();
		record.setIsDefault(true);
		record.setIsEnable(true);
		List<SysTenant> list = listByCondition(record);
		// 默认租户只可能存在一个
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<SysTenant> listByIds(Collection<Integer> ids) {
		if (CollectionUtils.isNotEmpty(ids)) {
			return sysTenantMapper.listByIds(ids);
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public Object saveSysBasicsGroup(SysTenantPO sysTenant) {
		String[] tableNames = { "complication_dictionary", "cm_dict", "complication_dictionary", "dict_grade", "dict_perform_freq", "Drug",
				"eng_machine_display_field", "eng_maintain_merchant", "eng_water_inspection_config", "estimates_conf", "medical_order_conf",
				"medical_order_dict", "patient_assay_conf", "patient_assay_filter_rule", "patient_label", "patient_serial_number",
				"propaganda_dictionary", "secretary_business", "secretary_business_rule", "secretary_config", "shift_borad_conf", "sickbed_shift",
				"stage_assessment_config", "Supplies", "sys_param", "sys_process_setting", "sys_role", "sys_template", "sys_template_child",
				"year_evaluation_conf" };
		String tableSchema = dbUrl.substring(dbUrl.lastIndexOf("/") + 1, dbUrl.lastIndexOf("?"));
		sysTenantMapper.setPrimaryKeyById(sysTenant.getFkTenantId() + "SysUser");
		sysTenantMapper.setPrimaryKeyById(sysTenant.getFkTenantId() + "SysUserTenant");
		for (int i = 0; i < tableNames.length; i++) {
			System.out.println(tableNames[i]);
			List<String> tablePropertyNameList = sysTenantMapper.getTablePropertyName(tableSchema, tableNames[i]);

			String tablePropertys = newTablePropertys(tablePropertyNameList.toString().substring(1, tablePropertyNameList.toString().length() - 1));
			String newtablePropertys = getNewtablePropertys(tablePropertyNameList, sysTenant, tableNames[i]);
			System.out.println(newtablePropertys);
			sysTenantMapper.saveSysBasiCsGroup(tableNames[i], tablePropertys, newtablePropertys, sysTenant);
		}
		sysTenantMapper.upSupplies(sysTenant);
		sysTenantMapper.saveMedicalOrderDictRPackage(sysTenant);
		sysTenantMapper.saveZkAssayRef(sysTenant);
		sysTenantMapper.upComplicationDictionary(sysTenant);
		sysTenantMapper.upMedicalOrderDict(sysTenant);
		// BusinessCache.cacheTenantId(Integer.parseInt(sysTenant.getFkTenantId()));
		this.savaUser(sysTenant);
		return null;
	}

	@Override
	public List<SysGroup> getSysGroupAll(SysGroup sysGroup) {

		return sysGroupMapper.getSysGroupAll(sysGroup);
	}

	@Override
	public Map<String, Object> save(MultipartFile logoUpload, MultipartFile logoPrintUpload, MultipartFile tvLogoUpload, SysTenantPO sysTenant)
					throws IOException {
		if (sysTenant.getId() == null) {

			Map<String, Object> map = new HashMap<>();
			sysTenant.setCreateUserId(UserUtil.getLoginUserId());
			sysTenant.setIsEnable(true);
			sysTenant.setGroupFlag(false);
			sysTenant.setStartDate(new Date());
			sysTenant.setEndDate(DateFormatUtil.convertStrToDate("2099-01-01", "yyyy-MM-dd"));
			DataUtil.setSystemFieldValue(sysTenant);
			sysTenantMapper.insert(sysTenant);
			SysTenant sysTenant3 = new SysTenant();
			sysTenant3.setName(sysTenant.getName());
			List<SysTenant> sysTenantList = sysTenantMapper.listByCondition(sysTenant3);
			SysTenant sysTenant2 = sysTenantList.get(0);
			SysGroupTenant sysGroupTenant = new SysGroupTenant();
			sysGroupTenant.setFkGroupId(sysTenant.getFkGroupId());
			sysGroupTenant.setFkTenantId(sysTenant2.getId());
			sysGroupTenant.setpTenantId(sysTenant.getFkGroupId());
			sysGroupTenantMapper.save(sysGroupTenant);

			/*
			 * 保存blank_background.png、blank_front.png、default-user.png 图片
			 */
			String path = this.getClass().getClassLoader().getResource("/").getPath();
			int indexOf = path.indexOf("WEB-INF");
			path = path.substring(0, indexOf);
			String blankBackgroundImg = path + "assets/img/blank_background.png";
			String blankFrontImg = path + "assets/img/blank_front.png";
			String defaultUserImg = path + "assets/img/default-user.png";
			File blankBackgroundImgFile = new File(blankBackgroundImg);
			File blankFrontImgFile = new File(blankFrontImg);
			File defaultUserImgFile = new File(defaultUserImg);

			newFilePath(sysTenant2.getId(), CommonConstants.IMAGE_FILE_PATH);
			File temp = null;

			temp = new File(CommonConstants.BASE_PATH + "/" + sysTenant2.getId() + "/" + "images" + "/blank_background.png");
			copyFileUsingFileStreams(blankBackgroundImgFile, temp);
			temp = new File(CommonConstants.BASE_PATH + "/" + sysTenant2.getId() + "/" + "images" + "/blank_front.png");
			copyFileUsingFileStreams(blankFrontImgFile, temp);
			temp = new File(CommonConstants.BASE_PATH + "/" + sysTenant2.getId() + "/" + "images" + "/default-user.png");
			copyFileUsingFileStreams(defaultUserImgFile, temp);
			if (logoUpload != null) {
				temp = new File(CommonConstants.BASE_PATH + "/" + sysTenant2.getId() + "/" + "images" + "/logo"
								+ logoUpload.getOriginalFilename().substring(logoUpload.getOriginalFilename().lastIndexOf(".")));
				logoUpload.transferTo(temp);
				if (!checkImg(temp)) {
					map.put("logoUploadErrer", "医院LOGO内容不正确");
				}
			}
			if (logoPrintUpload != null) {
				temp = new File(CommonConstants.BASE_PATH + "/" + sysTenant2.getId() + "/" + "images" + "/logo_print"
								+ logoPrintUpload.getOriginalFilename().substring(logoPrintUpload.getOriginalFilename().lastIndexOf(".")));
				logoPrintUpload.transferTo(temp);
				if (!checkImg(temp)) {
					map.put("logoPrintErrer", "打印页面医院LOGO内容不正确");
				}
			}
			if (tvLogoUpload != null) {
				temp = new File(CommonConstants.BASE_PATH + "/" + sysTenant2.getId() + "/" + "images" + "/tv_logo"
								+ tvLogoUpload.getOriginalFilename().substring(tvLogoUpload.getOriginalFilename().lastIndexOf(".")));
				tvLogoUpload.transferTo(temp);
				if (!checkImg(temp)) {
					map.put("tvLogoUploadErrer", "大屏医院LOGO内容不正确");
				}
			}
			/*
			 * 创建文件夹
			 */
			newFilePath(sysTenant2.getId(), CommonConstants.ANNOUNCEMENT_FILE_PATH); // 创建公告路径
			newFilePath(sysTenant2.getId(), CommonConstants.IMAGE_FILE_PATH + "/" + CommonConstants.COMPLICATION); // 并发症目录
			newFilePath(sysTenant2.getId(), CommonConstants.IMAGE_FILE_PATH + "/" + CommonConstants.IMAGE_FILE_PATH_DIALYSIS_MACHINE); // 透析机二维码目录
			newFilePath(sysTenant2.getId(), CommonConstants.IMAGE_FILE_PATH + "/" + CommonConstants.IMAGE_FILE_PATH_PATIENT); // 患者
			newFilePath(sysTenant2.getId(), CommonConstants.IMAGE_FILE_PATH + "/" + CommonConstants.IMAGE_FILE_PATH_PATIENT_BARCODE); // 患者二维码
			newFilePath(sysTenant2.getId(), CommonConstants.IMAGE_FILE_PATH + "/" + CommonConstants.USER_IMAGE_FILE_PATH); // 医护人员头像目录
			newFilePath(sysTenant2.getId(), CommonConstants.IMAGE_FILE_PATH + "/" + CommonConstants.HEALTH_COMMUNITIES); // 健康社区目录
			newFilePath(sysTenant2.getId(), CommonConstants.IMAGE_FILE_PATH + "/" + CommonConstants.HEALTH_PROPAGANDA); // 健康宣教目录
			newFilePath(sysTenant2.getId(), CommonConstants.IMAGE_FILE_PATH + "/" + CommonConstants.QC_FILE_PATH); // 通路目录
			newFilePath(sysTenant2.getId(), CommonConstants.PATHWAY); // 质控文件下载路径
			newFilePath(sysTenant2.getId(), CommonConstants.TEMP); // 下功或控件生成的临时文件

			/*基础数据的导入
			 */
			SysTenantPO sysBasicsGroupPo = new SysTenantPO();
			sysBasicsGroupPo.setFkTenantId(sysTenant2.getId().toString());
			sysBasicsGroupPo.setModelFkTenantId("10101");
			this.saveSysBasicsGroup(sysBasicsGroupPo);
			map.put("userName", sysBasicsGroupPo.getFkTenantId());
			return map;
		} else {
			Map<String, Object> map = new HashMap<>();

			/*
			 *更新医院名称 
			 */
			sysTenant.setUpdateUserId(UserUtil.getLoginUserId());
			sysTenantMapper.updateByPrimaryKeySelective(sysTenant);
			/*
			 * 图片的更新
			 */
			File temp = null;

			if (logoUpload != null) {
				temp = new File(CommonConstants.BASE_PATH + "/" + sysTenant.getId() + "/" + "images" + "/logo"
								+ logoUpload.getOriginalFilename().substring(logoUpload.getOriginalFilename().lastIndexOf(".")));
				temp.delete();
				logoUpload.transferTo(temp);
				if (!checkImg(temp)) {
					map.put("logoUploadErrer", "医院LOGO内容不正确");
				}
			}
			if (logoPrintUpload != null) {
				temp = new File(CommonConstants.BASE_PATH + "/" + sysTenant.getId() + "/" + "images" + "/logo"
								+ logoPrintUpload.getOriginalFilename().substring(logoPrintUpload.getOriginalFilename().lastIndexOf(".")));
				temp.delete();
				logoPrintUpload.transferTo(temp);
				if (!checkImg(temp)) {
					map.put("logoPrintUpload", "医院LOGO内容不正确");
				}
			}
			if (tvLogoUpload != null) {
				temp = new File(CommonConstants.BASE_PATH + "/" + sysTenant.getId() + "/" + "images" + "/logo"
								+ tvLogoUpload.getOriginalFilename().substring(tvLogoUpload.getOriginalFilename().lastIndexOf(".")));
				temp.delete();
				tvLogoUpload.transferTo(temp);
				if (!checkImg(temp)) {
					map.put("tvLogoUpload", "医院LOGO内容不正确");
				}
			}

			return map;
		}

	}

	/**
	 * 判断文件夹是否存在
	 * 
	 * @Title: getFilePath
	 * @param integer
	 * @return
	 *
	 */
	private File newFilePath(Integer integer, String fileName) {
		File tempDir = new File(CommonConstants.BASE_PATH + "/" + integer + "/" + fileName);
		if (!tempDir.exists()) {
			tempDir.mkdirs();
		}
		return tempDir;
	}

	/**
	 * 效验图片是否合法
	 * 
	 * @Title: checkImg
	 * @param newFile
	 * @return
	 *
	 */
	private boolean checkImg(File newFile) {
		boolean isLegal = false;
		try {
			BufferedImage image = ImageIO.read(newFile);
			if (image != null) {
				image.getWidth();
				image.getHeight();
				isLegal = true;
			}
		} catch (IOException e) {
		}

		if (!isLegal) {
			// 不合法，将磁盘上的文件删除
			newFile.delete();
		}
		return isLegal;
	}

	private String getNewtablePropertys(List<String> tablePropertyNameList, SysTenantPO sysTenant, String tableName) {
		StringBuffer str = new StringBuffer();
		for (String property : tablePropertyNameList) {
			if ("id".equals(property)) {
				str.append("getPrimaryKey('" + sysTenant.getFkTenantId() + upTable(tableName) + "')").append(", ");
			} else if ("fk_tenant_id".equals(property)) {
				str.append("'" + sysTenant.getFkTenantId() + "'").append(", ");
			} else if ("create_time".equals(property)) {
				str.append("'" + DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss") + "'").append(", ");
			} else if ("update_time".equals(property)) {
				str.append("'" + DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss") + "'").append(", ");
			} else {
				str.append("`" + property.trim() + "`").append(", ");
			}

		}

		return str.toString().substring(0, str.length() - 2);
	}

	/**
	 * 拼接插入的列
	 * 
	 * @Title: newTablePropertys
	 * @param tablePropertys
	 * @return
	 *
	 */
	public String newTablePropertys(String tablePropertys) {
		String[] Propertys = tablePropertys.split(",");
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < Propertys.length; i++) {

			str.append("`").append(Propertys[i].trim()).append("` ,");
		}
		return str.toString().substring(0, str.toString().length() - 1);
	}

	/**
	 * 将表明改成类名
	 * 
	 * @Title: upTable
	 * @param tableName
	 * @return
	 *
	 */
	private String upTable(String tableName) {
		String[] splits = tableName.split("_");
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < splits.length; i++) {
			String substring = splits[i].substring(0, 1).toUpperCase();
			str.append(substring).append(splits[i].substring(1, splits[i].length()));
		}
		return str.toString();
	}

	@Override
	public List<SysTenant> getTenantList(SysTenantPO sysTenant) {
		return sysTenantMapper.listByCondition(sysTenant);
	}

	@Override
	public Map<String, Object> uploadLicense(MultipartFile licensorUpload, SysTenantPO sysTenant) throws IOException {
		HashMap<String, Object> map = new HashMap<>();
		String licensorStr = getLicensorStr(licensorUpload.getInputStream());
		if (licensorStr.length() > 1024) {
			map.put("status", '0');
			map.put("result", "文件过大");
		} else {
			SysTenant sysTenant2 = new SysTenant();
			sysTenant2.setLicense(licensorStr);
			sysTenant2.setId(sysTenant.getId());
			sysTenantMapper.updateByPrimaryKeySelective(sysTenant2);
			map.put("status", '0');
		}
		return map;
	}

	/**
	 * 将流文件转换成String
	 * 
	 * @Title: getLicensorStr
	 * @param inputStream
	 * @return
	 *
	 */
	private String getLicensorStr(InputStream inputStream) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}

	@Override
	public List<SysGroup> getSysGroupAll() {
		return sysGroupMapper.getSysGroupAll(new SysGroup());
	}

	@Override
	public SysGroupTenant getSysGroupByFkTenantId(SysTenant sysTenant) {
		SysGroupTenant sysGroupTenant = new SysGroupTenant();
		sysGroupTenant.setFkTenantId(sysTenant.getId());
		SysGroupTenant getSysGroupTenantbyId = sysGroupTenantMapper.getSysGroupByFkTenantId(sysGroupTenant);
		return getSysGroupTenantbyId;
	}

	@Override
	public List<SysTenant> getCheckTenanName(SysTenant sysTenant) {
		return sysTenantMapper.listByCondition(sysTenant);
	}

	/**
	 * 复制文件
	 * 
	 * @Title: copyFileUsingFileStreams
	 * @param source
	 * @param dest
	 * @throws IOException
	 *
	 */
	private void copyFileUsingFileStreams(File source, File dest) throws IOException {
		InputStream input = null;
		OutputStream output = null;
		try {
			input = new FileInputStream(source);
			output = new FileOutputStream(dest);
			byte[] buf = new byte[1024];

			int bytesRead;
			while ((bytesRead = input.read(buf)) > 0) {
				output.write(buf, 0, bytesRead);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			input.close();
			output.close();
		}
	}

	@Override
	public void savaUser(SysTenantPO sysTenant) {
		SysUserPO user = new SysUserPO();
		user.setAccount(sysTenant.getFkTenantId());
		user.setGroupFlag(false);
		user.setName(sysTenant.getFkTenantId());
		user.setRoleId(sysTenant.getFkTenantId() + "0000000001");
		user.setRoleType("1");
		user.setPosition("管理员");
		user.setMultiTenant(sysTenant.getFkTenantId());
		user.setFkTenantId(Integer.parseInt(sysTenant.getFkTenantId()));
		userService.saveUser(user);

	}

}
