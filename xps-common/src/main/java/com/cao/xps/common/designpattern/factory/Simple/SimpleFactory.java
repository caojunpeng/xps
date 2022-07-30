package com.cao.xps.common.designpattern.factory.Simple;

import com.cao.xps.common.designpattern.factory.vo.Bone;
import com.cao.xps.common.designpattern.factory.vo.Cat;
import com.cao.xps.common.designpattern.factory.vo.Dog;

/**
 * 简单工厂
 */
public class SimpleFactory {

    public static Bone getBone(){
        return new Bone();
    }
    public static Cat getCat(){
        return new Cat();
    }
    public static Dog getDog(){
        return new Dog();
    }
}
