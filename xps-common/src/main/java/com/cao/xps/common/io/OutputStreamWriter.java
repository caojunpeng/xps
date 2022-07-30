package com.cao.xps.common.io;

import org.apache.commons.io.output.FileWriterWithEncoding;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 字符输出流
 *  构造方法
 *      FileWriter(String path); path： 路径
 *      FileWriter(File file);
 *      FileWriter(String path,boolean append);   append：是否覆盖    true：覆盖   false：追加
 *      FileWriter(File file,boolean append);     append：是否覆盖    true：覆盖   false：追加
 *  主要方法
 *      write(chart[] b[])：写出
 *      write(String b, int off, int len) :写出指定字节   off：开始索引    len长度
 *      close(): 关闭流
 *   换行符
 *      windows：\r\n
 *      linux：/n
 *      max：/r
 */
public class OutputStreamWriter {
    public static void main(String[] args) {
        try (
            FileWriter fileWriter = new FileWriter("D:\\a.txt",true);
        ){
            fileWriter.write("测试输出字符流\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/**
 * 字节输入流
 *  构造方法
 *      FileReader(String path);
 *      FileReader(File file);
 *  主要方法
 *      read();         返回读到的第一个字符
 *      read(char[] b[]);   读取多个字符 返回值为读到的个数
 *      readBytes(char b[], int off, int len)   返回置顶字符  off：开始索引    len长度
 *      close(): 关闭流
 */
class InputStreamReader{
    public static void main(String[] args) {
        try(
                FileReader fileReader = new FileReader("D:\\a.txt");
        ){
           /* int len = 0;
            while ((len=fileReader.read())!=-1){
                System.out.println((char)len);
            }*/
            char[] bytes = new char[1024];
            int len=0;
            while ((len=fileReader.read(bytes))!=-1){
                System.out.println(bytes);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class TextReaderAndWrider{
    public static void main(String[] args) {
        copyFileByRW("D:\\a.txt","D:\\b.txt");
        copyFileByRWs("D:\\a.txt","D:\\c.txt");
    }

    /**
     * 以单个字符为单位复制
     * @param sourcePath
     * @param outputPath
     */
    public static void copyFileByRW(String sourcePath, String outputPath){
        long s = System.currentTimeMillis();
        try(
            FileReader fileReader = new FileReader(sourcePath);
            FileWriter fileWriter = new FileWriter(outputPath,true);
        ){
            int len = 0;
            while ((len=fileReader.read())!=-1){
                fileWriter.write(len);
            }
            long e = System.currentTimeMillis();
            System.out.println("单字符复制耗时："+(e-s)+"毫秒");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 以多个字符为单位复制
     * @param sourcePath
     * @param outputPath
     */
    public static void copyFileByRWs(String sourcePath, String outputPath){
        long s = System.currentTimeMillis();
        try(
                FileReader fileReader = new FileReader(sourcePath);
                FileWriter fileWriter = new FileWriter(outputPath,true);
        ){
            char[] c = new char[1024];
            int len = 0;
            while ((len=fileReader.read(c))!=-1){
                fileWriter.write(c);
            }
            long e = System.currentTimeMillis();
            System.out.println("多字符复制耗时："+(e-s)+"毫秒");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
