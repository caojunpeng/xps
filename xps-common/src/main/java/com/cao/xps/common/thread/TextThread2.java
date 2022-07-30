package com.cao.xps.common.thread;

/**
 * @author: xpse
 * @remark: 初识并发
 * @create: 2022-06-17 15:52
 */
public class TextThread2 implements Runnable{

    //总数100个苹果
    private int i = 100;

    @Override
    public void run() {
        while (true){
            if(i<=0){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"拿到了第"+i+"个苹果");
            i--;
        }
    }

    /**
     * 并发初现
     * @param args
     */
    public static void main(String[] args) {
        TextThread2 t2 = new TextThread2();
        new Thread(t2,"小明").start();
        new Thread(t2,"小红").start();
        new Thread(t2,"小张").start();
    }


}
