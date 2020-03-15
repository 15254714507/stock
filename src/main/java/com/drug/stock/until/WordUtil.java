package com.drug.stock.until;


import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author lenovo
 */
@Slf4j
public class WordUtil {

    /**
     * 生成word文件
     *
     * @param dataMap      word中需要展示的动态数据，用map集合来保存
     * @param templateName word模板名称，例如：model.ftl
     * @param filePath     文件生成的目标路径，例如：D:\\freemarker
     * @param fileName     生成的文件名称，例如：Test.doc
     */
    public static void createWord(Map<String, Object> dataMap, String templateName, String filePath, String fileName) {
        try {
            //创建配置实例
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);

            //设置编码
            configuration.setDefaultEncoding("utf-8");

            //ftl模板文件
            configuration.setClassForTemplateLoading(WordUtil.class, "/templates/down");

            //获取模板
            Template template = configuration.getTemplate(templateName);

            //输出文件
            File outFile = new File(filePath + File.separator + fileName);

            //如果输出目标文件夹不存在，则创建
            if (!outFile.getParentFile().exists()) {
                if (!outFile.getParentFile().mkdirs()) {
                    log.error("创建文件失败");
                }
                ;
            }

            //将模板和数据模型合并生成文件
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), StandardCharsets.UTF_8));

            //生成文件
            template.process(dataMap, out);

            //关闭流
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}