package com.cao.xps.common.designpattern.proxy.dynamicproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLB动态代理
 *  无需让被代理对象实现接口
 *  需要导入cglib的jar包
 */
public class CGLBProxy {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Person.class);
        enhancer.setCallback(new LogProxy());
        Person p = (Person)enhancer.create();
        p.move();
    }
}
class LogProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始...");
        Object o1 = methodProxy.invokeSuper(o, objects);
        System.out.println("结束...");
        return o1;
    }
}

class Person {
    public void move(){
        System.out.println("person move ....");
    }
}