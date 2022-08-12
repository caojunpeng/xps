package com.cao.xps.common.utils;

import org.apache.commons.io.FileUtils;

import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;

public class XpsFileUtils extends FileUtils {

    private static Logger logger=LoggerFactory.getLogger(XpsFileUtils.class);

    public static final List<String> pictoreList= Arrays.asList("jpg","png");//图片
    public static final List<String> voiceList= Arrays.asList("mp3");//音频
    public static final List<String> videoList= Arrays.asList("mp4");//视频
    public static final List<String> fileList= Arrays.asList("dox","doxs","zip","txt");//文件

    /**
     * MultipartFile 转 File
     *
     * @param file
     * @throws Exception
     */
    public static File multipartFileToFile(MultipartFile file, String toPath) throws Exception {

        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(toPath);
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }
    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param file
     * @param type
     * @return
     */
    public static String getMd5ByFile(File file,String type) {
        if (!file.exists()|| StringUtils.isBlank(type)){
            return null;
        }
        InputStream fis;
        byte[] buffer = new byte[2048];
        int numRead = 0;
        MessageDigest md5;

        try {
            fis = new FileInputStream(file);
            md5 = MessageDigest.getInstance(type);
            while ((numRead = fis.read(buffer)) > 0) {
                md5.update(buffer, 0, numRead);
            }
            fis.close();
            return md5ToString(md5.digest());
        } catch (Exception e) {
            System.out.println("error");
            return null;
        }
    }
    public static String md5ToString(byte[] md5Bytes) {
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 格式化路径   \斜杠替换为/斜杠
     * @param path
     * @return
     */
    public static String formatPath(String path){
        String returnPath = path;
        try {
            if(path.indexOf("\\") != -1){
                returnPath = path.replace("\\", "/");
            }
        } catch (Exception e) {
            logger.error("格式化路径异常"+e.getMessage(),e);
        }
        return returnPath;
    }

    /**
     * 将list数据写入文件
     * @param fileInfo
     * @param path
     */
    public static void writeObject(List<String> fileInfo,String path) {
        OutputStreamWriter osw =null;
        try {
            File file=new File(path);
            if(file.exists()) {
                file.delete();
            }
            //参数true:覆盖文件中内容，反之
            FileOutputStream fos = new FileOutputStream(path,true);
            //将信息写入文件之后出现乱码情况需要配置字体编码
            osw = new OutputStreamWriter(fos, "UTF-8");
            StringBuffer infoValue=new StringBuffer();
            if(fileInfo!=null&&!fileInfo.isEmpty()) {
                for(String info:fileInfo){
                    infoValue.append(info+"\r\n");//  '\r\n' 是用换行使用
                }
            }
            osw.write(infoValue.toString());
            osw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                osw.close();
            } catch (IOException e) {
                logger.info("文件流关闭异常"+e.getMessage());
            }
        }

    }
}
