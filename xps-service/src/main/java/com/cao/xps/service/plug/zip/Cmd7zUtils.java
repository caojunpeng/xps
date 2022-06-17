package com.cao.xps.service.plug.zip;


import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * zip压缩工具类
 */
public class Cmd7zUtils {
    private static Logger logger = LoggerFactory.getLogger(Cmd7zUtils.class);

    public static long fileLength = 1024000;//分片限定文件大小           10485760000l = 10g

    public static String volume = "100k";//每卷大小

    public static final String os = System.getProperty("os.name");

    public static void main(String[] args) {

        //分卷压缩
		/*String soucrefilePath = "D:\\GoogleFile\\37715.zip";
		String createSplitBy7z = createSplitBy7z(soucrefilePath);
		System.out.println(createSplitBy7z);*/

        //分卷解压缩
        unzipSplitBy7z("D:\\GoogleFile\\split\\37715");

    }

    /**
     *    合并分卷文件，并解压缩分卷文件
     *    分卷数据独立分到每一个文件夹中，方便组合
     * @param
     * @return
     */
    public static String unzipSplitBy7z(String sourcefilePath){
        String flag = null;
        try {
            File sourceFile = new File(sourcefilePath);
            if(!sourceFile.exists()){
                logger.info("源文件不存在，路径："+sourcefilePath);
                return flag;
            }
            String fileName=sourceFile.getName();
            File creFile = new File(sourcefilePath+"/CRE_"+fileName);
            if(!creFile.exists()) {
                logger.info("凭证文件不存在");
                return flag;
            }
            Long startTime = System.currentTimeMillis();
            String install7z = find7zPath();
            if(install7z==null) {
                logger.info("未安装7z软件");
                return flag;
            }

            //第一步：合并分卷文件
            String copyCmd = sourcefilePath+"/"+fileName+".zip* "+sourcefilePath+"/"+fileName+".zip";
            copyCmd = copyCmd.replace("/", "\\");
            String copycmdarray [] = {"cmd.exe","/c","copy /b "+copyCmd};
            if(!os.toLowerCase().startsWith("window")) {
                copycmdarray[0] = "/bin/sh";
                copycmdarray[1] = "-c";
                copyCmd ="cat "+ sourcefilePath+"/"+fileName+".zip* >> "+sourcefilePath+"/"+fileName+".zip";
                copyCmd = copyCmd.replace("\\\\", "/");
                logger.info("分卷合并windows执行命令："+copyCmd);
                copycmdarray[2] = copyCmd;
            }else {
                logger.info("分卷合并windows执行命令："+copyCmd);
            }
            Process copyProcess = Runtime.getRuntime().exec(copycmdarray);
            copyProcess.waitFor(4, TimeUnit.HOURS);//默认4小时强制结束

            //第二步：删除分卷文件以及合并文件、凭证文件，只保留解压文件
            deleteFile(sourcefilePath);
            flag = "success";
            Long endTime = System.currentTimeMillis();
            logger.info("分卷解压缩成功，耗时："+(endTime - startTime)/1000 +"秒");
        } catch (Exception e) {
            logger.error("分卷解压缩异常"+e.getMessage(),e);
        }
        return flag;
    }
    /**
     * 删除分卷文件以及合并文件、凭证文件
     * @param
     */
    public static void deleteFile(String sourcefilePath) {
        try {
            File sourceFile = new File(sourcefilePath);
            File[] listFiles = sourceFile.listFiles();
            for (File file : listFiles) {
                if(!file.getName().endsWith(".zip")) {
                    logger.info("删除文件"+file.getAbsolutePath());
                    FileUtils.forceDelete(file);
                }
            }
        } catch (Exception e) {
            logger.error("删除分卷文件异常"+e.getMessage(),e);
        }
    }

    /**
     * 调用7z命令进行压缩
     * @param sourcefilePath   需要压缩的文件夹名称
     * @return
     */
    public static String createSplitBy7z(String sourcefilePath){
        String flag = null;
        try {
            File sourceFile = new File(sourcefilePath);
            if(!sourceFile.exists() || sourceFile.length()<=fileLength) {
                logger.info("文件不存在或不需要分卷");
                return flag;
            }
            Long startTime = System.currentTimeMillis();
            String install7z = find7zPath();
            if(install7z==null) {
                logger.info("未安装7z软件");
                return flag;
            }
            String fileName=sourceFile.getName().substring(0,sourceFile.getName().length()-4);
            String splitName = fileName+".zip";
            String splitePathName = sourceFile.getParent()+"/split/";
            if(StringUtils.isNotBlank(fileName)) {
                String name=fileName.replace("","");
                splitePathName = sourceFile.getParent()+"/split/"+name+"/";
            }
            File splitPorder = new File(splitePathName);
            if(!splitPorder.exists()) {//创建独自分卷目录
                splitPorder.mkdirs();
            }
            String spliteFileName = splitePathName+splitName;

//    		String commands =  "start /B "+install7z+"7z.exe a " + splitePathName + " " + soucrefilePath +" -v100k ";
            String commands =  install7z+" a " + spliteFileName + " " + sourcefilePath +" -v"+volume;
            commands = commands.replace("\\", "/");
            logger.info("分卷压缩执行命令："+commands.toString());
            String cmdarray [] = {"cmd.exe","/c",commands};
            if(!os.toLowerCase().startsWith("window")) {
                cmdarray[0] = "/bin/sh";
                cmdarray[1] = "-c";
            }
            logger.info("分卷压缩执行命令："+cmdarray.toString());
            Runtime run = Runtime.getRuntime();
            Process proc = run.exec(cmdarray);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(),"gbk"));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc.getOutputStream())), true);
            out.println("exit");// 这个命令必须执行，否则in流不结束。
            String rspLine = "";
            while ((rspLine = in.readLine()) != null) {
                System.out.println(rspLine);
            }
            proc.waitFor(10, TimeUnit.HOURS);//默认10小时强制结束
            in.close();
            out.close();
            proc.destroy();

            flag = credentials(splitPorder, fileName);

            Long endTime = System.currentTimeMillis();
            logger.info("分卷压缩成功，耗时："+(endTime - startTime)/1000 +"秒");
        } catch (Exception e) {
            logger.error("分卷压缩异常"+e.getMessage(),e);
        }
        return flag;
    }

    /**
     * 生成分卷凭证
     * @param splitPorder
     * @param
     * @throws Exception
     */
    public static String credentials(File splitPorder,String fileName) {
        String credentialsFile = null;
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            //生成分卷压缩凭证
            File[] splitFiles = splitPorder.listFiles();
            if(splitFiles==null || splitFiles.length<=0) {
                return null;
            }
            credentialsFile = splitPorder.getPath()+"/CRE_"+fileName;
            fw = new FileWriter(new File(credentialsFile));
            bw= new BufferedWriter(fw);
            for (File file : splitFiles) {
                bw.write(file.getName());
                bw.newLine();
            }
        } catch (Exception e) {
            logger.error("生成凭证文件异常"+e.getMessage(),e);
        } finally {
            try {
                if(bw!=null) {
                    bw.close();
                }
                if(fw!=null) {
                    fw.close();
                }
            } catch (IOException e) {
                logger.error("凭证文件文件流关闭异常"+e.getMessage(),e);
            }
        }
        return credentialsFile;
    }

    /**
     * 查找7z安装目录
     * @return
     */
    public static String find7zPath() {
        String path = null;
        try {
            if(!os.toLowerCase().startsWith("window")){
                return " 7za ";
            }
            Process ps = null;
            ps = Runtime.getRuntime().exec("reg query HKEY_LOCAL_MACHINE\\SOFTWARE\\7-Zip");
            ps.getOutputStream().close();
            InputStreamReader i = new InputStreamReader(ps.getInputStream());
            String line;
            BufferedReader ir = new BufferedReader(i);
            while ((line = ir.readLine()) != null) {
                if (line != null && line.contains("Path")) {
                    path = line.substring(line.indexOf("REG_SZ") + 6).trim();
                    break;
                }
            }
            if(path!=null) {
                path = path.replaceAll(" ", "\" \"")+"7z ";
            }
        } catch (Exception e) {
            logger.error("压缩zip异常"+e.getMessage(),e);
        }
        return path;
    }
}
