package com.cao.xps.common.designpattern.singleton;

/**
 * 饿汉模式：
 *  1、私有化构造器
 *  2、静态加载实例化对象
 */
public class Hungry {

    public static Hungry hungrySingleon= new Hungry();

    private Hungry(){

    }
}
