package com.cao.xps.common.designpattern.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Jdk动态代理
 *  被代理对象需要事项接口
 */
public class WoMan implements Moveable{

    @Override
    public void move() {
        System.out.println("woman move dadadada....");
    }

    public static void moveInstance(){
        WoMan woMan = new WoMan();
        Moveable m = (Moveable)Proxy.newProxyInstance(WoMan.class.getClassLoader(), new Class[]{Moveable.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("开始");
                Object invoke = method.invoke(woMan, args);
                System.out.println("结束");
                return null;
            }
        });
        m.move();
    }
    public static void moveInstance2(){
        WoMan woMan = new WoMan();
        Moveable m = (Moveable)Proxy.newProxyInstance(WoMan.class.getClassLoader(), new Class[]{Moveable.class}, new TimeProxy(woMan));
        m.move();
    }

    public static void main(String[] args) {
        WoMan.moveInstance2();
    }
}
interface Moveable{
    void move();
}
class TimeProxy implements InvocationHandler{
    WoMan woMan;

    public TimeProxy(WoMan woMan) {
        this.woMan = woMan;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始");
        Object o = method.invoke(woMan, args);
        System.out.println("结束");
        return o;
    }
}
