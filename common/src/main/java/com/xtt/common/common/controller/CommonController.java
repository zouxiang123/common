/**   
 * @Title: CommonController.java 
 * @Package com.xtt.txgl.common.controller
 * Copyright: Copyright (c) 2015
 * @author: Tik   
 * @date: 2015年9月16日 上午11:50:09 
 *
 */
package com.xtt.common.common.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtt.common.common.service.ICommonService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.County;
import com.xtt.common.util.BusinessCommonUtil;
import com.xtt.platform.util.PrimaryKeyUtil;
import com.xtt.platform.util.time.DateFormatUtil;

/**
 * @ClassName: CommonController
 * @date: 2015年9月16日 上午11:50:09
 * @version: V1.0
 * 
 */
@Controller
@RequestMapping("/common")
public class CommonController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	ICommonService commonService;

	/**
	 * 获取指定省份下所有的县/区
	 * 
	 * @Title: getCountyList
	 * @param provinceId
	 * @return
	 * 
	 */
	@RequestMapping("getCountyList")
	@ResponseBody
	public List<County> getCountyList(Integer provinceId) {
		return commonService.getCountyList(provinceId);
	}

	@SuppressWarnings("resource")
	@RequestMapping("showImage")
	@ResponseBody
	public void showImage(HttpServletRequest re, HttpServletResponse response, String fileName) {// pic_addr:图片路径(d:\\upload\a.jpg)

		response.setContentType("image/*");
		FileInputStream fis = null;
		OutputStream os = null;
		try {
			String path = BusinessCommonUtil.getFilePath(CommonConstants.IMAGE_FILE_PATH);
			fis = new FileInputStream(path + fileName);
			os = response.getOutputStream();
			int count = 0;
			byte[] buffer = new byte[1024 * 4];
			while ((count = fis.read(buffer)) != -1) {
				os.write(buffer, 0, count);
				os.flush();
			}
		} catch (Exception e) {
			if (e instanceof FileNotFoundException) {
				LOGGER.warn("can not found image file, file name is {}", fileName);
				String path = BusinessCommonUtil.getFilePath(CommonConstants.IMAGE_FILE_PATH);
				try {
					fis = new FileInputStream(path + "/default-user.png");
					os = response.getOutputStream();
					int count = 0;
					byte[] buffer = new byte[1024 * 4];
					while ((count = fis.read(buffer)) != -1) {
						os.write(buffer, 0, count);
						os.flush();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping("fileDownload")
	public void download(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "fileName", required = true) String fileName,
					@RequestParam(value = "type", required = true) String type, @RequestParam(value = "realName", required = false) String realName)
					throws IOException {
		fileName = URLDecoder.decode(fileName, "UTF-8");
		if (StringUtils.isEmpty(realName)) {
			realName = fileName;
		} else {
			realName = URLDecoder.decode(realName, "UTF-8");
		}
		String agent = request.getHeader("User-Agent").toLowerCase();
		if (agent.indexOf("firefox") != -1) {
			response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "iso8859-1"));
		} else {
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
		}
		response.setContentType("application/octet-stream;charset=utf-8");
		FileInputStream fis = null;
		OutputStream os = null;
		try {
			String path = BusinessCommonUtil.getFilePath(type);
			fis = new FileInputStream(path + realName);
			os = response.getOutputStream();
			int count = 0;
			byte[] buffer = new byte[1024 * 8];
			while ((count = fis.read(buffer)) != -1) {
				os.write(buffer, 0, count);
				os.flush();
			}
		} catch (Exception e) {
			if (e instanceof FileNotFoundException) {
				LOGGER.warn("can not found file, file name is {}", fileName);
			}
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取服务器时间
	 * 
	 * @Title: getServerDate
	 * @return
	 * 
	 */
	@RequestMapping("getServerDate")
	public Map<String, Integer> getServerDate() {
		return DateFormatUtil.getHourMinuteSecond(new Date());
	}

	/**
	 * 刷新主键缓存
	 * 
	 * @Title: refreshPrimaryKey
	 * @return
	 * 
	 */
	@RequestMapping("refreshPrimaryKey")
	public Map<String, Object> refreshPrimaryKey() {
		Map<String, Object> map = new HashMap<String, Object>();
		PrimaryKeyUtil.refresh();
		map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
		return map;
	}
}
