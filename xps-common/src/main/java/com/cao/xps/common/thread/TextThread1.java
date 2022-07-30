package com.cao.xps.common.thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TextThread1 extends Thread{
    public String filepath;
    public String url;

    /**
    *@author: peng
    *@description: 
    *@create: 15:40 2022/6/17
    *@param: [url, filepath]
    */
    public TextThread1(String url, String filepath) {
        this.filepath = filepath;
        this.url = url;
    }

    /**
     * 重写run
     */
    @Override
    public void run() {
        WebDownLoad webDownLoad = new WebDownLoad();
        webDownLoad.imgownLoad(url,filepath);
        System.out.println("下载了文件："+url);
        System.out.println("文件下载地址："+filepath);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        TextThread1 textThread2 = new TextThread1("https://zkres1.myzaker.com/202206/62a9845d8e9f094333578159_1024.jpg","C:\\Users\\peng\\Desktop\\urlImage\\1.jpg");
        textThread2.start();
    }
    class WebDownLoad{
        //下载网络图片
        public void imgownLoad(String url,String filepath){
            try {
                FileUtils.copyURLToFile(new URL(url),new File(filepath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



