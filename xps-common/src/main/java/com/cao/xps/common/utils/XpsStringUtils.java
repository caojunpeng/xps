package com.cao.xps.common.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class XpsStringUtils extends StringUtils {
    //系统版本
    private static  String  os = System.getProperty("os.name");

    //安装路径
    @Value("#{ @environment['app.collect.untarpath'] ?: T(java.lang.System).getProperty('user.dir')}")
    private String installDir;

    /**
     * 判断操作系统类型
     * @return
     */
    public static boolean isWindow(){
        boolean flag = false;
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("window")){
            flag = true;
        }
        return flag;
    }

    /**
     * 视频文件解析
     * @param ffmpegExePath
     * @param inputFilePath
     * @param outputFilePath
     */
    public static void decodeAmr(String ffmpegExePath,String inputFilePath,String outputFilePath) {
        try {
            if (isWindow()) {
                //			ffmpegExePath = "C:\\Users\\user\\Desktop\\tar\\ffmpeg.exe";
                ffmpegExePath = System.getProperty("user.dir")+ File.separator+"SRServer"+File.separator+"ffmpeg.exe";
                File file = new File(ffmpegExePath);
                if (!file.exists()){
                    return;
                }
                List<String> command = new ArrayList<String>();
                command.add(ffmpegExePath);
                command.add("-i");
                command.add(inputFilePath);
                command.add("-ab");
                command.add("128k");
                command.add(outputFilePath);
                ProcessBuilder builder = new ProcessBuilder();
                builder.command(command);
                //正常信息和错误信息合并输出
                builder.redirectErrorStream(true);
                try {
                    //开始执行命令
                    Process process = builder.start();
                    //如果你想获取到执行完后的信息，那么下面的代码也是需要的
                    StringBuffer sbf = new StringBuffer();
                    String line = null;
                    BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        sbf.append(line);
                        sbf.append(" ");
                    }
                    String resultInfo = sbf.toString();
                    //				System.out.println(resultInfo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                String raw2flvCmd = "/monchickey/ffmpeg/bin/ffmpeg -i "+inputFilePath+" -ab 128k "+outputFilePath;
                try {
                    Runtime.getRuntime().exec(new String[]{"sh","-c",raw2flvCmd});
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
