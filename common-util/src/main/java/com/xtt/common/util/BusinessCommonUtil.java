/**   
 * @Title: CommonUtil.java 
 * @Package com.xtt.common.common.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年10月10日 上午10:26:15 
 *
 */
package com.xtt.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xtt.common.constants.CommonConstants;

public class BusinessCommonUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessCommonUtil.class);

    public static String getFilePath(String module) {
        String basePath = CommonConstants.BASE_PATH + "/" + UserUtil.getTenantId() + "/";
        if (CommonConstants.ANNOUNCEMENT_FILE_PATH.equals(module)) {
            return basePath + CommonConstants.ANNOUNCEMENT_FILE_PATH + "/";
        } else if (CommonConstants.IMAGE_FILE_PATH.equals(module)) {
            return basePath + CommonConstants.IMAGE_FILE_PATH + "/";
        } else if (CommonConstants.QC_FILE_PATH.equals(module)) {
            return basePath + CommonConstants.QC_FILE_PATH + "/";
        } else if (CommonConstants.EXCEL_TEMPLATE_FILE_PATH.equals(module)) {
            return basePath + CommonConstants.EXCEL_TEMPLATE_FILE_PATH + "/";
        }
        return null;
    }

    /**
     * 获取images基础路径
     * 
     * @Title: getBaseImagePath
     * @return
     *
     */
    public static String getBaseImagePath() {
        return "/" + UserUtil.getTenantId() + "/" + CommonConstants.IMAGE_FILE_PATH + "/";
    }

    /**
     * 生成用户头像。在图片1上写名字，再将较长片2覆盖在图片1上，生成新的图片头像
     * 
     * @Title: combineImage
     * @param filename1
     * @param name
     * @param filename2
     * @param newFilename
     * 
     */
    public static void combineImage(String name, String newFilename) {
        String path = CommonConstants.BASE_PATH + "/" + UserUtil.getTenantId() + "/" + CommonConstants.IMAGE_FILE_PATH;
        newFilename = CommonConstants.BASE_PATH + "/" + newFilename;
        File f1 = new File(path + "/blank_background.png");
        File f2 = new File(path + "/blank_front.png");
        if (!f1.exists()) {
            f1 = new File(CommonConstants.BASE_PATH + "/blank_background.png");
        }
        if (!f2.exists()) {
            f2 = new File(CommonConstants.BASE_PATH + "/blank_front.png");
        }
        BufferedImage image;
        BufferedImage image2;
        try {
            image = ImageIO.read(f1);
            Graphics g = image.getGraphics();
            g.setFont(new Font("微软雅黑", Font.BOLD, 90));
            g.setColor(Color.WHITE);
            int y = image.getHeight() / 2 + 30;
            g.drawString(name, -20, y);
            image2 = ImageIO.read(f2);
            g.drawImage(image2, 0, 0, null);
            File f3 = new File(newFilename);
            if (f3.exists()) {
                f3.delete();
            } else {
                f3.mkdirs();
            }
            ImageIO.write(image, "PNG", f3);
        } catch (IOException e) {
            LOGGER.error("catch combine image error", e);
        }
    }

    public static void compressPic(String path, String filename) {
        CompressPicUtil mypic = new CompressPicUtil();
        mypic.compressPic(path, path, filename, filename, 144, 144, true);
    }

    public static void compressPic(String inputDir, String outputDir, String inputFileName, String outputFileName) {
        CompressPicUtil mypic = new CompressPicUtil();
        mypic.compressPic(inputDir, outputDir, inputFileName, outputFileName, 144, 144, true);
    }

    public static void compressPic(String inputDir, String outputDir, String inputFileName, String outputFileName, int width, int height) {
        CompressPicUtil mypic = new CompressPicUtil();
        mypic.compressPic(inputDir, outputDir, inputFileName, outputFileName, width, height, true);
    }

    public static void compressPic(String inputDir, String outputDir, String inputFileName, String outputFileName, int width, int height, boolean gp) {
        CompressPicUtil mypic = new CompressPicUtil();
        mypic.compressPic(inputDir, outputDir, inputFileName, outputFileName, width, height, gp);
    }

    /**
     * 将Map中Null转成空串
     * 
     * @Title: trimNullByMap
     * @param map
     * 
     */
    public static void trimNullByMap(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() == null) {
                entry.setValue("");
            }
        }
    }
}
