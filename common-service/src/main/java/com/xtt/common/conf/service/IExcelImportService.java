/**   
 * @Title: IExcelImportService.java 
 * @Package com.xtt.txgl.excel.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年12月17日 下午12:54:13 
 *
 */
package com.xtt.common.conf.service;

import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

public interface IExcelImportService {
    /**
     * 导入excel
     * 
     * @Title: importExcel
     * @param file
     * @return
     * @throws Exception
     *
     */
    HashMap<String, Object> importExcel(MultipartFile file, String sysOwner) throws Exception;

}
