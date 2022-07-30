package com.cao.xps.common.thread;

/**
 * 线程抢占
 */
public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new TextJson());
        thread.start();
        for (int i = 0; i < 1000; i++) {
            System.out.println("主线程启动"+i);
            if(i==300){
                thread.join();
            }
        }
    }
}
class TextJson implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("线程VIP开始"+i);
        }
    }
}
