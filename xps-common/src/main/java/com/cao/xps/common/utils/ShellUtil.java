package com.cao.xps.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * linux系统中shell脚本工具类
 */
public class ShellUtil {

    private static  final Logger logger=LoggerFactory.getLogger(ShellUtil.class);
    /**
     * 运行shell脚本
     * @param comands 多个命令时以“;”结尾
     */
    public static void runShellCommand(List<String> comands){
        try {
            if(comands==null || comands.isEmpty()){
                return;
            }
            StringBuffer sb = new StringBuffer();
           for(String cmd:comands){
               sb.append(cmd);
           }
            Process ps = Runtime.getRuntime().exec(sb.toString());
           //等待
            ps.waitFor(1, TimeUnit.HOURS);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
