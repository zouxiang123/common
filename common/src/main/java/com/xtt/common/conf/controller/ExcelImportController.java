/**   
 * @Title: ExcelImporterController.java v
 * @Package com.xtt.txgl.excel.contorller
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年12月17日 上午9:36:32 
 *
 */
package com.xtt.common.conf.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xtt.common.conf.service.IExcelImportService;
import com.xtt.common.constants.CommonConstants;

@Controller
@RequestMapping("excel/")
public class ExcelImportController {
    @Autowired
    private IExcelImportService excelImportService;

    @RequestMapping("dataImport")
    public String dataImport(Model model, String sys) {
        model.addAttribute("sysOwner", sys);
        return "system/data_import";
    }

    @RequestMapping("upload")
    @ResponseBody
    public HashMap<String, Object> upload(@RequestParam(value = "excel", required = true) MultipartFile excel, String sysOwner) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (excel.getSize() > CommonConstants.MAX_FILE_SIZE) {
            map.put("stauts", CommonConstants.WARNING);
            return map;
        }
        map.put("status", CommonConstants.SUCCESS);
        map.put("result", excelImportService.importExcel(excel, sysOwner));
        return map;
    }

    @RequestMapping("getTemplate")
    public void getTemplate(HttpServletRequest request, HttpServletResponse response,
                    @RequestParam(value = "fileName", required = true) String fileName) throws UnsupportedEncodingException {
        fileName = URLDecoder.decode(fileName, "UTF-8");
        String agent = request.getHeader("User-Agent").toLowerCase();
        if (agent.indexOf("firefox") != -1) {
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "iso8859-1"));
        } else {
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        }
        response.setContentType("application/octet-stream;charset=utf-8");
        InputStream fis = null;
        OutputStream os = null;
        try {
            fis = this.getClass().getResourceAsStream("/excel/" + fileName);
            os = response.getOutputStream();
            int count = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((count = fis.read(buffer)) != -1) {
                os.write(buffer, 0, count);
                os.flush();
            }
        } catch (Exception e) {
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
}
