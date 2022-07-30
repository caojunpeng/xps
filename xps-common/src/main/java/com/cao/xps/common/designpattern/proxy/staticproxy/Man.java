package com.cao.xps.common.designpattern.proxy.staticproxy;

/**
 * 初识代理
 */
public class Man {
    public void move(){
        System.out.println("方法执行：向前移动...");
    }
    public void proxyTest(){
        System.out.println("开始");
        move();
        System.out.println("结束");
    }
}
/**
 * 当不改变源码的情况--通过实现同一接口
 */
interface Movable{
    void move();
}
class Man1 implements Movable{
    public void move(){
        System.out.println("方法执行：向前移动...");
    }
    public void proxyTest(){
        System.out.println("开始");
        move();
        System.out.println("结束");
    }
}
class Proxy1 implements Movable{
    Man1 man1=new Man1();
    @Override
    public void move() {
        System.out.println("开始");
        man1.move();
        System.out.println("结束");
    }

    public static void main(String[] args) {
        Proxy1 p = new Proxy1();
        p.move();
    }
}

/**
 * 静态代理--可以代理代理对象（代理之间互相代理）
 */
class Proxy2 implements Movable{
    Movable m;

    public Proxy2(Movable m) {
        this.m = m;
    }
    @Override
    public void move() {
        System.out.println("开始...");
        m.move();
        System.out.println("结束...");
    }
}
class Proxy3 implements Movable{
    Movable m;

    public Proxy3(Movable m) {
        this.m = m;
    }
    @Override
    public void move() {
        System.out.println("前进...");
        m.move();
        System.out.println("后退...");
    }

    public static void main(String[] args) {
        Proxy3 p = new Proxy3(new Proxy2(new Man1()));
        p.move();
    }
}
