package com.cao.xps.common.thread;

/**
 * 多线程方法-实现Runnable 1.实现Runnable;2.创建Thread对象;3.启动线程
 * 优点：使用简便
 */
public class Thread2 implements Runnable{


    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("线程："+i);
        }
    }

    //主线程
    public static void main(String[] args) {
        Thread2 t1 = new Thread2();
        Thread2 t2 = new Thread2();
        Thread2 t3 = new Thread2();
        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();
    }

}
