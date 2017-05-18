package com.xtt.common.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import com.xtt.platform.util.time.DateFormatUtil;

public class ImageTailorUtil {

    private static String DEFAULT_THUMB_PREVFIX = "thumb_";
    private static String DEFAULT_CUT_PREVFIX = "cut_";
    private static Boolean DEFAULT_FORCE = false;

    /**
     * 
     * @param srcImg
     *            源图片
     * @param output
     *            图片输出流
     * @param rect
     *            需要截取部分的坐标和大小
     */
    public boolean cutImage(File srcImg, OutputStream output, Rectangle rect) {
        if (srcImg.exists()) {
            FileInputStream fis = null;
            ImageInputStream iis = null;
            try {
                fis = new FileInputStream(srcImg);
                // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
                String types = Arrays.toString(ImageIO.getReaderFormatNames()).replace("]", ",");
                String suffix = null;
                // 获取图片后缀
                if (srcImg.getName().indexOf(".") > -1) {
                    suffix = srcImg.getName().substring(srcImg.getName().lastIndexOf(".") + 1);
                } // 类型和图片后缀全部小写，然后判断后缀是否合法
                if (suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase() + ",") < 0) {
                    System.out.println("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
                    return false;
                }
                // 将FileInputStream 转换为ImageInputStream
                iis = ImageIO.createImageInputStream(fis);
                // 根据图片类型获取该种类型的ImageReader
                ImageReader reader = ImageIO.getImageReadersBySuffix(suffix).next();
                reader.setInput(iis, true);
                ImageReadParam param = reader.getDefaultReadParam();
                param.setSourceRegion(rect);
                BufferedImage bi = reader.read(0, param);
                ImageIO.write(bi, suffix, output);
                return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fis != null)
                        fis.close();
                    if (iis != null)
                        iis.close();
                    if (output != null)
                        output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("the src image is not exist.");
        }
        return false;
    }

    public void cutImage(File srcImg, OutputStream output, int x, int y, int width, int height) {
        cutImage(srcImg, output, new Rectangle(x, y, width, height));
    }

    public boolean cutImage(File srcImg, String destImgPath, String destFileName, Rectangle rect) {
        File destImg = new File(destImgPath);
        if (!destImg.exists()) {
            System.out.println("the dest " + destImgPath + "  folder is not exist.");
            destImg.mkdirs();
            System.out.println("create " + destImgPath + " folder ");
        }
        try {
            String outPutFilePath = destImgPath + destFileName;
            System.out.println(outPutFilePath);
            if (new File(destImgPath).exists()) {
                new File(destImgPath).delete();
            }
            boolean b = cutImage(srcImg, new FileOutputStream(outPutFilePath), rect);
            if (b) {
                return true;
            }
        } catch (FileNotFoundException e) {
            System.out.println("the dest " + destImgPath + "  is not exist.");
        }
        return false;
    }

    /**
     *
     * @param srcImg
     *            原图片
     * @param destImg
     *            存放新生成的图片的文件夹路径
     * @param x
     *            左上角 x轴坐标
     * @param y
     *            左上角 y轴坐标
     * @param width
     *            宽
     * @param height
     *            高
     */
    public boolean cutImage(File srcImg, String destImg, String destFileName, int x, int y, int width, int height) {
        if (!srcImg.exists()) {
            return false;
        }
        return cutImage(srcImg, destImg, destFileName, new Rectangle(x, y, width, height));
    }

    /**
     *
     * @param srcImg
     *            原图片
     * @param destImgDir
     *            存放新生成的图片的文件夹路径
     * @param destFileName
     *            新生成的图片名称
     * @param x
     *            左上角 x轴坐标
     * @param y
     *            左上角 y轴坐标
     * @param width
     *            宽
     * @param height
     *            高
     */
    public boolean cutImage(String srcImg, String destImgDir, String destFileName, int x, int y, int width, int height) {
        return cutImage(new File(srcImg), destImgDir, destFileName, new Rectangle(x, y, width, height));
    }

    public static void main(String[] args) {
        String inputDir = "C:\\xtt\\10101\\images\\user\\autograph\\original\\";
        String outputDir = "C:\\xtt\\10101\\images\\user\\autograph\\";
        String inputFileName = "101010000000024.jpg";
        //String outputFileName = "temp_picture.jpg";
        String destFileName = "101010000000024.jpg";
        // 根据尺寸放大或缩小原图
        //int sWidth = 1102;
        //int sHeight = 620;
        //BusinessCommonUtil.compressPic(inputDir, outputDir, inputFileName, outputFileName, sWidth, sHeight, false);
        // 根据处理后的图片进行裁剪
        new ImageTailorUtil().cutImage(inputDir + inputFileName, outputDir, destFileName, 234, 490, 394, 222);
    }
}
