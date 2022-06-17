package com.cao.xps.common.utils;

import com.deepoove.poi.XWPFTemplate;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Date;
import java.util.Map;

/**
 * word工具类
 */
public class WordUtils {

    protected static final String WORD = ".docx";

    private static final Logger logger= LoggerFactory.getLogger(WordUtils.class);

    /**
     * 读取word文件的内容
     * @param file file
     * @return 返回文件内容
     */
    public static String readWordToString(File file) {
        String wordContent = "";
        if(file!=null && file.exists()){
            String path = file.getAbsolutePath();
            if(StringUtils.isNotBlank(path)){
                try {
                    if (path.endsWith(".doc")) {
                        InputStream is = new FileInputStream(file);
                        WordExtractor ex = new WordExtractor(is);
                        wordContent = ex.getText();
                        ex.close();
                    } else if (path.endsWith("docx")) {
                        OPCPackage opcPackage = POIXMLDocument.openPackage(path);
                        POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                        wordContent = extractor.getText();
                        extractor.close();
                    } else {
                        System.out.println("此文件不是word文件！");
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }
        return wordContent;
    }

    /**
     *  Word文档模板解析
     * @param stream    模板流
     * @param installDir    安装目录
     * @param fileName  文件名称
     * @param map   模板中需要替换的值
     * @return
     */
    public static String wordAnalys(InputStream stream,String installDir,String fileName,Map<String,Object> map){
        XWPFTemplate template ;
        FileOutputStream out = null;
        String success = installDir + File.separator + fileName;
        try {
            if(stream!=null){
                template = XWPFTemplate.compile(stream).render(map);
                File successfile = new File(installDir);
                if (!successfile.exists()) {
                    successfile.mkdirs();
                }
                out = new FileOutputStream(success);
                template.write(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(stream);
            IOUtils.closeQuietly(out);
        }
        return null;
    }

    /**
     * word模板转义
     * @param templateFile 模板路径(替换 {{ }} 中内容)
     * @param successFile   文件成功路径
     * @param data  替换内容
     */
    public static boolean wordTemplateFile(String templateFile,String successFile,Object data){
        boolean result=false;
        try {
            //填充模板中的数据
            logger.info("模板转换开始，原文件路径："+templateFile);
            if(StringUtils.isBlank(templateFile)){
                logger.info("源文件路径为空");
                return result;
            }
            File file=new File(templateFile);
            if (!file.exists()){
                logger.info("模板文件不存在，模板路径："+templateFile);
                return result;
            }
            String filename=file.getName();
            String start=filename.substring(0,filename.indexOf("."));
            successFile=successFile+File.separator+start+ DateUtils.formatDate(new Date(), "yyyyMMddHHmmss")+WORD ;
            BufferedInputStream stream = new BufferedInputStream(new FileInputStream(templateFile));
            XWPFTemplate template = XWPFTemplate.compile(stream).render(data);
            FileOutputStream out = new FileOutputStream(successFile);
            template.write(out);
            result=true;
            logger.info("模板转换成功，生成文件路径："+successFile);
        }catch (Exception e){
            logger.error("模板转换异常，异常信息："+e.getMessage(),e);
        }
        return result;
    }
}
