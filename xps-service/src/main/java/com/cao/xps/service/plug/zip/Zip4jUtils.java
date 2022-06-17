package com.cao.xps.service.plug.zip;

import java.io.File;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;

public class Zip4jUtils {
    private static Logger logger = LoggerFactory.getLogger(Zip4jUtils.class);

    /**
     * 压缩方式	COMP_STORE = 0;（仅打包，不压缩）	COMP_DEFLATE = 8;（默认）COMP_AES_ENC = 99; 加密压缩
     压缩级别	DEFLATE_LEVEL_FASTEST = 1; (速度最快，压缩比最小)
     DEFLATE_LEVEL_FAST = 3; (速度快，压缩比小)
     DEFLATE_LEVEL_NORMAL = 5; (一般)
     DEFLATE_LEVEL_MAXIMUM = 7;
     DEFLATE_LEVEL_ULTRA = 9;
     */
    public static String  createSplitZipFile(String sourcefilePath,String collectId ) {
        long startTime = System.currentTimeMillis();
        String flag = null;
        try {
            File sourceFile = new File(sourcefilePath);
            if(!sourceFile.exists() || sourceFile.length()<=Cmd7zUtils.fileLength) {
                logger.info("zip4j文件不存在或不需要分卷");
                return flag;
            }

            String splitName = sourceFile.getName().substring(0,sourceFile.getName().length()-4)+".zip";
            String splitePathName = sourceFile.getParent()+"/split/";
            if(collectId!=null) {
                splitePathName = sourceFile.getParent()+"/split/Task_"+collectId+"/";
            }
            File splitPorder = new File(splitePathName);
            if(!splitPorder.exists()) {//创建独自分卷目录
                splitPorder.mkdirs();
            }

            // Initiate ZipFile object with the path/name of the zip file.
            ZipFile zipFile = new ZipFile(splitePathName+splitName);
            logger.info("zip4j分卷压缩压缩目录："+splitePathName+splitName);
            ArrayList<File> filesToAdd = new ArrayList<>();
            filesToAdd.add(new File(sourcefilePath));

            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(CompressionMethod.STORE);//压缩方式
            parameters.setCompressionLevel(CompressionLevel.FASTEST);//压缩级别
            // 分片压缩方法  1048576=1M   10485760000l=10g
            zipFile.createSplitZipFile(filesToAdd, parameters, true, Cmd7zUtils.fileLength);
            long endTime = System.currentTimeMillis();
            logger.info("zip4j分卷压缩耗时：" + (endTime - startTime)/1000 + "s");

            flag = Cmd7zUtils.credentials(splitPorder, collectId);

            return flag;
        } catch (ZipException e) {
            logger.error("zip4j分卷压缩异常"+e.getMessage(),e);
        }
        return null;
    }


    public static String  unzipSplitZipFile(String sourcefilePath,String collectId) {
        String flag = null;
        long startTime = System.currentTimeMillis();
        try {
            File sourceFile = new File(sourcefilePath+"/CRE_"+collectId);
            if(!sourceFile.exists()) {
                logger.info("凭证文件不存在");
                return flag;
            }

            ZipFile zipFile = new ZipFile(sourcefilePath+File.separator+"Task_"+collectId+".zip");
            if (!zipFile.isValidZipFile()) {
                throw new ZipException("文件不合法或不存在");
            }
            logger.info("zip4j分卷解压："+sourcefilePath+"Task_"+collectId+".zip");
            // 解压
            zipFile.extractAll(sourcefilePath);

            //第二步：删除分卷文件以及合并文件、凭证文件，只保留解压文件
            Cmd7zUtils.deleteFile(sourcefilePath);

            long endTime = System.currentTimeMillis();
            System.out.println("zip4j分卷解压耗时：" + (endTime - startTime)/1000 + "s");
            flag = "success";
        } catch (ZipException e) {
            logger.error("zip4j分卷压缩异常"+e.getMessage(),e);
        }
        return flag;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        //分片压缩
//		String soucrefilePath = "F:\\serverzip/Task_69d48d6964184550a44c1f4544e73eed.tar";
//		String collectId = soucrefilePath.substring(soucrefilePath.indexOf("Task_")+5,soucrefilePath.indexOf(".tar"));
//		String createSplitBy7z = createSplitZipFile(soucrefilePath,collectId);
//		System.out.println(createSplitBy7z);

        //分卷解压缩
        unzipSplitZipFile("F:\\serverzip\\split\\Task_69d48d6964184550a44c1f4544e73eed", "69d48d6964184550a44c1f4544e73eed");

    }

}
