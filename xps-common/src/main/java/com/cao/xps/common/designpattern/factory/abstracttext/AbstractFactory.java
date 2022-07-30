package com.cao.xps.common.designpattern.factory.abstracttext;

import com.cao.xps.common.designpattern.factory.vo.*;

/**
 * 抽象工厂：便于横向扩展
 */
public abstract class AbstractFactory {

    abstract Animal createAnimal() ;

    abstract Food createFood() ;

}

class DogFactory extends AbstractFactory{

    @Override
    public Animal createAnimal() {
        return new Dog();
    }

    @Override
    public Food createFood() {
        return new Bone();
    }
}

class CatFactory extends AbstractFactory{

    @Override
    public Animal createAnimal() {
        return new Cat();
    }

    @Override
    public Food createFood() {
        return new Fish();
    }
}
