/**   
 * @Title: ExcelExportUtil.java 
 * @Package com.xtt.txgl.common.util.excel
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年6月12日 上午11:57:56 
 *
 */
package com.xtt.common.util.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

public class ExcelExportUtil {

    /**
     * 创建表头
     */
    public static void createHeadRow(HSSFWorkbook wb, HSSFSheet sheet, int rowNum, String[] headNames) {
        // 表头
        HSSFRow headRow = sheet.createRow(rowNum);
        headRow.setHeight((short) 350);
        // 序号列
        HSSFCell snCell = headRow.createCell(0);
        CellStyle headStyle = ExcelExportUtil.initHeadCellStyle(wb);
        snCell.setCellStyle(headStyle);
        snCell.setCellValue("序号");
        // 列头名称
        for (int i = 0; i < headNames.length; i++) {
            HSSFCell cell = headRow.createCell(i + 1);
            cell.setCellStyle(headStyle);
            cell.setCellValue(headNames[i]);
        }
    }

    /**
     * @Description: 创建内容行的每一列(附加一列序号)
     */
    public static HSSFCell[] getCells(HSSFWorkbook wb, HSSFRow contentRow, Object[] objArr) {
        HSSFCell[] cells = new HSSFCell[objArr.length + 1];

        for (int i = 0, len = cells.length; i < len; i++) {
            cells[i] = contentRow.createCell(i);
            cells[i].setCellStyle(initContentCellStyle(wb, contentRow.getRowNum() % 2 == 0));

            if (i == 0) {
                // 设置序号列值，因为出去标题行和日期行，所有-2
                cells[0].setCellValue(contentRow.getRowNum());
            } else {
                cells[i].setCellValue(objArr[i - 1] == null ? "" : objArr[i - 1].toString());
            }
        }

        return cells;
    }

    /**
     * @Description: 初始化表头行样式
     */
    public static CellStyle initHeadCellStyle(HSSFWorkbook wb) {
        CellStyle headStyle = wb.createCellStyle();
        headStyle.setAlignment(CellStyle.ALIGN_CENTER);
        headStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        headStyle.setFont(initHeadFont(wb));
        headStyle.setFillBackgroundColor(IndexedColors.YELLOW.index);
        headStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
        headStyle.setBorderBottom(CellStyle.BORDER_THIN);
        headStyle.setBorderLeft(CellStyle.BORDER_THIN);
        headStyle.setBorderRight(CellStyle.BORDER_THIN);
        headStyle.setTopBorderColor(IndexedColors.BLUE.index);
        headStyle.setBottomBorderColor(IndexedColors.BLUE.index);
        headStyle.setLeftBorderColor(IndexedColors.BLUE.index);
        headStyle.setRightBorderColor(IndexedColors.BLUE.index);
        return headStyle;
    }

    /**
     * @Description: 初始化内容行样式
     */
    public static CellStyle initContentCellStyle(HSSFWorkbook wb, boolean isOdd) {
        CellStyle contentStyle = wb.createCellStyle();
        contentStyle.setAlignment(CellStyle.ALIGN_CENTER);
        contentStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        contentStyle.setFont(initContentFont(wb));

        if (isOdd) {// 奇数行
            contentStyle.setFillForegroundColor((short) 61);
        } else {
            contentStyle.setFillForegroundColor((short) 62);
        }
        contentStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        contentStyle.setBorderTop(CellStyle.BORDER_THIN);
        contentStyle.setBorderBottom(CellStyle.BORDER_THIN);
        contentStyle.setBorderLeft(CellStyle.BORDER_THIN);
        contentStyle.setBorderRight(CellStyle.BORDER_THIN);
        contentStyle.setTopBorderColor(IndexedColors.BLUE.index);
        contentStyle.setBottomBorderColor(IndexedColors.BLUE.index);
        contentStyle.setLeftBorderColor(IndexedColors.BLUE.index);
        contentStyle.setRightBorderColor(IndexedColors.BLUE.index);
        contentStyle.setWrapText(true); // 字段换行
        return contentStyle;
    }

    /**
     * @Description: 初始化表头行字体
     */
    public static Font initHeadFont(HSSFWorkbook wb) {
        Font headFont = wb.createFont();
        headFont.setFontName("宋体");
        headFont.setFontHeightInPoints((short) 10);
        headFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headFont.setCharSet(Font.DEFAULT_CHARSET);
        headFont.setColor(IndexedColors.BLUE_GREY.index);
        return headFont;
    }

    /**
     * @Description: 初始化内容行字体
     */
    public static Font initContentFont(HSSFWorkbook wb) {
        Font contentFont = wb.createFont();
        contentFont.setFontName("宋体");
        contentFont.setFontHeightInPoints((short) 10);
        contentFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
        contentFont.setCharSet(Font.DEFAULT_CHARSET);
        contentFont.setColor(IndexedColors.BLUE_GREY.index);
        return contentFont;
    }

    public static void init(HSSFWorkbook wb) {
        // 自定义颜色
        addCustomColor(wb, (short) 58, (byte) (192), (byte) (192), (byte) (192));// headlinecolor
        addCustomColor(wb, (short) 59, (byte) (225), (byte) (225), (byte) (225));// contentlinecolor
        addCustomColor(wb, (short) 60, (byte) 233, (byte) 234, (byte) 236);// headcolor
        addCustomColor(wb, (short) 61, (byte) (254), (byte) (254), (byte) (254));// oddcolor
        addCustomColor(wb, (short) 62, (byte) (246), (byte) (246), (byte) (246));// evencolor
    }

    /**
     * 自定义颜色,index须位于1到63之间；超出无效
     * 
     * @Title: addCustomColor
     * @param index
     * @param r
     * @param g
     * @param b
     * 
     */
    public static void addCustomColor(HSSFWorkbook wb, short index, byte r, byte g, byte b) {
        HSSFPalette palette = wb.getCustomPalette();
        palette.setColorAtIndex(index, r, g, b);
    }

    /**
     * @Description: 自动调整列宽
     */
    public static void autoAdjustColumnSize(HSSFWorkbook wb) {
        int sheetNumber = wb.getNumberOfSheets();
        for (int i = 0; i < sheetNumber; i++) {
            HSSFSheet sheet = wb.getSheetAt(i);
            for (int j = 0; j < sheet.getLastRowNum() + 1; j++) {
                sheet.autoSizeColumn(j, true);
            }
        }
    }

    /**
     * 写成流返回
     * 
     * @Title: writeToOutStream
     * @param request
     * @param response
     * @param fileName
     * @throws IOException
     * 
     */
    public static void writeToOutStream(HSSFWorkbook wb, HttpServletRequest request, HttpServletResponse response, String fileName)
                    throws IOException {
        fileName = URLDecoder.decode(fileName, "UTF-8");
        String agent = request.getHeader("User-Agent").toLowerCase();
        if (agent.indexOf("firefox") != -1) {
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "iso8859-1"));
        } else {
            response.setHeader("Content-Disposition",
                            "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20").replaceAll("%28", "\\(")
                                            .replaceAll("%29", "\\)").replaceAll("%3B", ";").replaceAll("%40", "@").replaceAll("%23", "\\#")
                                            .replaceAll("%26", "\\&"));
        }
        response.setContentType("application/octet-stream;charset=utf-8");
        OutputStream outputStream = response.getOutputStream();
        wb.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
