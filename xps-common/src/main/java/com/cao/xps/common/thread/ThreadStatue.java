package com.cao.xps.common.thread;

/**
 * 线程状态
 */
public class ThreadStatue {
    public static void main(String[] args) {
        TextStatue textStatue = new TextStatue();
        System.out.println(textStatue.getState());
        textStatue.start();
        textStatue.setPriority(1);
        while (textStatue.getState() != Thread.State.TERMINATED){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(textStatue.getState());
        }

    }
}

class TextStatue extends Thread{
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println("线程经过");


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
