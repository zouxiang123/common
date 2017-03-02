package com.xtt.common.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FreemarkerUtil.class);

    /**
     * 获取模板的html
     * 
     * @Title: printToString
     * @param ftlPath
     *            模板路径
     * @param ftlName
     *            模板文件名
     * @param root
     *            传入的map
     * @param formClassPath
     *            是不是从classPath中读取模板
     * @return
     * @throws IOException
     *
     */
    public static String printToString(String ftlPath, String ftlName, Map<String, Object> root, boolean formClassPath) {
        StringWriter stringWriter = null;
        String html = "";
        try {
            Template temp = getTemplate(ftlName, ftlPath, formClassPath); // 通过Template可以将模板文件输出到相应的流
            stringWriter = new StringWriter();
            temp.process(root, stringWriter);
            html = stringWriter.toString();
        } catch (Exception e) {
            LOGGER.error("generate template failed,case by :", e);
        } finally {
            if (stringWriter != null) {
                stringWriter.flush();
                try {
                    stringWriter.close();
                } catch (IOException e) {
                    LOGGER.error("close writer failed,case by :", e);
                }
            }
        }
        return html;
    }

    /**
     * 
     * 
     * @param ftlName
     * 
     * @param root
     * 
     * @param outFile
     * 
     * @param filePath
     * 
     */

    /**
     * 输出到文件
     * 
     * @Title: printFile
     * @param ftlPath
     *            模板路径
     * @param ftlName
     *            模板文件名
     * @param root
     *            传入的map
     * @param filePath
     *            输出文件目录
     * @param fileName
     *            输出文件名称
     * @param formClassPath
     *            是不是从classPath中读取
     * @throws Exception
     *
     */
    public static void printFile(String ftlPath, String ftlName, Map<String, Object> root, String filePath, String fileName, boolean formClassPath)
                    throws Exception {
        Writer out = null;
        try {
            File file = new File(filePath + fileName);
            if (!file.exists()) {
                file.mkdirs();
            }
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
            Template template = getTemplate(ftlName, ftlPath, formClassPath);
            template.process(root, out); // 模版输出
        } catch (Exception e) {
            LOGGER.error("get template failed,case by :", e);
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    /**
     * 加载模板
     * 
     * @Title: getTemplate
     * @param ftlName
     *            模板名称
     * @param ftlPath
     *            模板路径
     * @param formClassPath
     *            是不是从classPath中读取
     * @return
     * @throws Exception
     *
     */
    public static Template getTemplate(String ftlName, String ftlPath, boolean formClassPath) throws Exception {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_23); // 通过Freemaker的Configuration读取相应的ftl
            cfg.setDefaultEncoding("utf-8");
            cfg.setEncoding(Locale.CHINA, "utf-8");
            if (formClassPath) {
                cfg.setClassLoaderForTemplateLoading(FreemarkerUtil.class.getClassLoader(), ftlPath);
            } else {
                cfg.setDirectoryForTemplateLoading(new File(ftlPath)); // 设定去哪里读取相应的ftl模板文件
            }
            return cfg.getTemplate(ftlName, "UTF-8"); // 在模板文件目录中找到名称为name的文件
        } catch (IOException e) {
            LOGGER.error("get template failed,case by :", e);
        }
        return null;
    }
}
