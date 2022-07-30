package com.cao.xps.common.thread;

/**
 * 测试线程停止 stop、destory（不建议使用）
 * 通过标识为停止线程 （建议使用）--线程中定义标识位，提供修改
 * 线程正常跑结束 （建议使用）
 */
public class StopThread {
    public static void main(String[] args) {
        TestThread testThread=new TestThread();
        testThread.start();
        for (int i = 0;i<100000;i++){
            System.out.println("主线程----------"+i);
            if(i==200){
                System.out.println("线程停止----------------------------------------------------"+i++);
                testThread.stopThread();
                break;
            }
        }
    }
}

class TestThread extends Thread{

    private boolean flag = true;

    @Override
    public void run() {
        int i = 1;
        while (flag){
            System.out.println("线程继续"+i++);
        }
    }

    public void stopThread(){
        flag=false;
    }
}