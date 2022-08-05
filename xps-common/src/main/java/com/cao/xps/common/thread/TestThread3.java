package com.cao.xps.common.thread;

import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.*;

/**
 * 模拟龟兔赛跑
 */
public class TestThread3 implements Runnable{

    //胜利者
    private static String winner;

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if("兔子".equals(Thread.currentThread().getName())){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            boolean statue = getWinner(i);
            if(statue){
                break;
            }
        }
    }

    private boolean getWinner(int i){
        if(winner!=null){
            System.out.println("胜利者是："+winner);
            return true;
        }else{
            if(i==100){
                winner=Thread.currentThread().getName();
                return true;
            }else{
                i++;
                System.out.println(Thread.currentThread().getName()+"跑了"+i+"步");
            }
        }
        return false;
    }

    public static void main(String[] args) {

        TestThread3 testThread3 = new TestThread3();
        ExecutorService e = Executors.newWorkStealingPool();

        e.submit(testThread3);

    }

}
