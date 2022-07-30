package com.cao.xps.common.designpattern.singleton;

/**
 * 懒汉模式
 *  1、私有化构造方法
 *  2、
 */
public class Lazy {

    private static Lazy lazy ;

    private Lazy(){

    }

    //有线程安全问题
    public Lazy getInstance1(){
        if(lazy!=null){
            lazy = new Lazy();
        }
        return lazy;
    }

    //效率略低
    public synchronized Lazy getInstance2(){
        if(lazy!=null){
            lazy = new Lazy();
        }
        return lazy;
    }
    //双层检查
    public Lazy getInstance3(){
        if(lazy!=null){
            synchronized(lazy){
                if(lazy!=null){
                    lazy = new Lazy();
                }
            }
        }
        return lazy;
    }


    //匿名内部类：实体类加载时匿名内部类是不加载的
    private static class LazyHolder{
        public static Lazy lazy = new Lazy();
    }
    public static Lazy getLazy(){
        return LazyHolder.lazy;
    }


}
