package com.cao.xps.common.thread;

/**
 * 线程礼让
 */
public class ThreadYield {
    public static void main(String[] args) {
        new Thread(new TextYidld(),"a").start();
        new Thread(new TextYidld(),"b").start();
    }
}
class TextYidld implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"线程开始");
        Thread.yield();//线程礼让
        System.out.println(Thread.currentThread().getName()+"线程结束");
    }
}
