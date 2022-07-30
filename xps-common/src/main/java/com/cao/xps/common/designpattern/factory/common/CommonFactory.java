package com.cao.xps.common.designpattern.factory.common;

import com.cao.xps.common.designpattern.factory.vo.Animal;
import com.cao.xps.common.designpattern.factory.vo.Cat;
import com.cao.xps.common.designpattern.factory.vo.Dog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 普通工厂模式
 */
public class CommonFactory {
    public static Map<String,FactoryInterface> map = new HashMap<>();

    public static Animal getObj(String str){
        map.put("Dog",new DogFactory());
        map.put("Cat",new CatFactory());
        List<FactoryInterface> collect = map.keySet().stream().filter(s->s.equals(str)).map(e -> {
            return map.get(e);
        }).collect(Collectors.toList());
        return collect.get(0).create();
    }

    public static void main(String[] args) {
        Animal dog = getObj("Dog");
        dog.eat();
    }
}

interface FactoryInterface {
    Animal create();
}
class DogFactory implements FactoryInterface{
    @Override
    public Animal create() {
        return new Dog();
    }
}
class CatFactory implements FactoryInterface{
    @Override
    public Animal create() {
        return new Cat();
    }
}

