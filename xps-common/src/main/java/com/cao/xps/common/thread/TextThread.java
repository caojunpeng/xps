package com.cao.xps.common.thread;

/**
 * 多线程方法-继承Thread类  1.继承Thread类;2.重写Run()方法;3.调用start()方法
 * 启动线程不一定立即执行，看cpu调度
 */
public class TextThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("线程："+i);
        }
    }

    //主线程
    public static void main(String[] args) {
        TextThread textThread = new TextThread();
        textThread.start();
        for (int i = 0; i < 20; i++) {
            System.out.println("主线程："+i);
        }
    }

}
