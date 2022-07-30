package com.cao.xps.common.thread;

/**
 * 线程睡眠: Thread.sleep(1000)  单位：毫秒
 */
public class TestThreadSleep {
    public static void main(String[] args) {
        try {
            numDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void numDown() throws InterruptedException {
        int i = 10;
        while (i>=0){
            Thread.sleep(1000);//线程睡眠
            System.out.println( i--);
        }
    }
}
