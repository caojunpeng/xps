package com.cao.xps.common.io;

import ch.qos.logback.classic.net.SyslogAppender;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 字节输出流
 *  构造方法
 *      FileOutputStream(String path); path： 路径
 *      FileOutputStream(File file);
 *      FileOutputStream(String path,boolean append);   append：是否覆盖    true：覆盖   false：追加
 *      FileOutputStream(File file,boolean append);     append：是否覆盖    true：覆盖   false：追加
 *  主要方法
 *      write(byte b[])：写出
 *      write(byte b[], int off, int len) :写出指定字节   off：开始索引    len长度
 *      close(): 关闭流
 *   换行符
 *      windows：\r\n
 *      linux：/n
 *      max：/r
 */
public class OutputStream {

    public static void main(String[] args) {
        try(
            FileOutputStream fos = new FileOutputStream("D:\\a.txt",true);
            ){
            fos.write("你好\r\n".getBytes());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * 字节输入流
 *  构造方法
 *      FileInputStream(String path);
 *      FileInputStream(File file);
 *  主要方法
 *      read();         返回读到的第一个字符
 *      read(byte[] b[]);   读取多个字符 返回值为读到的个数
 *      readBytes(byte b[], int off, int len)   返回置顶字符  off：开始索引    len长度
 *      close(): 关闭流
 */
class InputStream{
    public static void main(String[] args) {
        try(
            FileInputStream fos = new FileInputStream("D:\\a.txt");
        ){
            /*while (fos.read()!=-1){
                System.out.println(fos.read());
            }*/
            byte[] bytes = new byte[1024];
            int len=0;
            while ((len=fos.read(bytes))!=-1){
                System.out.println(bytes);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class TextStream{

    public static void main(String[] args) {
        copyFileByByte("D:\\a.txt","D:\\b.txt");
        copyFileByBytes("D:\\a.txt","D:\\c.txt");
    }

    /**
     * 以单个字节为单位复制文件
     * @param sourcePath
     * @param outPaht
     */
    public static void copyFileByByte(String sourcePath, String outPaht){
        long s = System.currentTimeMillis();
        try(
            FileInputStream fis = new FileInputStream(sourcePath);
            FileOutputStream fos = new FileOutputStream(outPaht);
        ){
            int len = 0;
            while((len=fis.read())!=-1){
                fos.write(len);
            }
            long e = System.currentTimeMillis();
            System.out.println("单字节复制耗时："+(e-s)+"毫秒");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 以多个字节为单位复制文件
     * @param sourcePath
     * @param outPaht
     */
    public static void copyFileByBytes(String sourcePath, String outPaht){
        long s = System.currentTimeMillis();
        try(
                FileInputStream fis = new FileInputStream(sourcePath);
                FileOutputStream fos = new FileOutputStream(outPaht);
        ){
            int len = 0;
            byte[] b = new byte[1024];
            while((len=fis.read(b))!=-1){
                fos.write(b,0,len);
            }
            long e = System.currentTimeMillis();
            System.out.println("多字节复制耗时："+(e-s)+"毫秒");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}