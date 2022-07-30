package com.cao.xps.common.thread;

/**
 * 静态代理模拟-结婚
 *
 */
public class StaticProxy {
    public static void main(String[] args) {

        //代理实例
        Proxy proxy = new Proxy(new You());
        proxy.happyMarried();

        //与线程实例对比
        new Thread(new textRunable()).start();
        new Proxy(new You()).happyMarried();
    }
}

//模拟线程
class textRunable implements Runnable{
    @Override
    public void run() {

    }
}
//结婚接口
interface Married{
    void happyMarried();
}

//真实人
class You implements Married{
    @Override
    public void happyMarried() {
        System.out.println("我要结婚了！");
    }
}

//代理
class Proxy implements Married{

    //目标对象
    private Married married;

    //构造方法
    public Proxy(Married married) {
        this.married = married;
    }

    @Override
    public void happyMarried() {
        beffor();
        married.happyMarried();
        affter();
    }

    private void affter() {
        System.out.println("结婚后收拾");
    }

    private void beffor() {
        System.out.println("结婚前准备");
    }
}