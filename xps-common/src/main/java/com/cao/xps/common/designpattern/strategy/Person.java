package com.cao.xps.common.designpattern.strategy;

import org.apache.poi.ss.formula.functions.T;

import java.util.Arrays;
import java.util.List;

public class Person implements Man<Person>{
    private int age;

    public int getAge() {
        return age;
    }

    public Person(int age) {
        this.age = age;
    }
    @Override
    public int compartTo(Person o) {
        if(this.age >o.getAge())return 1;
        else if(this.age<o.getAge())return -1;
        else return 0;
    }
}

/**
 * 普通模式-对目标对象进行排序
 */
class SortTest1 {
    public static void sort(List<Person> personList){
        personList.stream().mapToInt(Person::getAge).sorted().forEach(System.out::println);
    }
    public static void main(String[] args) {
        List<Person> list = Arrays.asList(new Person(10),new Person(5),new Person(7));
        SortTest1.sort(list);
    }
}

/**
 * 对不同目标对象进行排序
 */
interface Man<T>{
    int compartTo(T o);
}
class SortTest2 {
    public static void sort(Man[] manList) {
        for (int i=0;i<manList.length;i++){
            int end = i;
            for (int j=0; j<manList.length;j++){
               end =  manList[j].compartTo(manList[i])>=0? j:i;
            }
            swup(manList,i,end);
        }
    }
    public static void swup(Man[] mans,int i,int j){
        Man man = mans[i];
        mans[i] = mans[j];
        mans[j] = man;
    }

    public static void main(String[] args) {
        Person[] list = {new Person(10),new Person(5),new Person(7),new Person(4),new Person(12)};
        SortTest2.sort(list);
        Arrays.stream(list).forEach(s-> System.out.println(s.getAge()));
    }
}

/**
 * 策略模式：可通过不同策略，对不同目标对象进行排序
 */
interface StrategyInterface<E>{
    int compartTo(E o1, E o2);
}
class PersonStrategy implements StrategyInterface<Person>{
    @Override
    public int compartTo(Person o1, Person o2) {
        if(o1.getAge() > o2.getAge())return 1;
        else if(o1.getAge() < o2.getAge())return -1;
        else return 0;
    }
}

class SortTest3<E> {
    public void sort(E[] manList, StrategyInterface<E> strategyInterface) {
        for (int i=0;i<manList.length;i++){
            int end = i;
            for (int j=0; j<manList.length;j++){
                end =strategyInterface.compartTo(manList[j],manList[i])>=0? j:i;
            }
            swup(manList,i,end);
        }
    }
    public void swup(E[] mans,int i,int j){
        E man = mans[i];
        mans[i] = mans[j];
        mans[j] = man;
    }

    public static void main(String[] args) {
        Person[] list = {new Person(10),new Person(5),new Person(7),new Person(4),new Person(12)};
        SortTest3 sortTest3 = new SortTest3();
        sortTest3.sort(list,new PersonStrategy());
        Arrays.stream(list).forEach(s-> System.out.println(s.getAge()));
    }
}