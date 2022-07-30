package com.cao.xps.common.designpattern.intarator;

import java.util.LinkedList;
import java.util.List;

/**
 * 模拟数组实现
 */
public class Arraylist_ {
    //容器
    Object[] objects = new Object[10];

    //下标
    public int index = 0;

    public void add(Object o){
        if(objects.length == index){
            Object[] newObjects = new Object[objects.length * 2];
            System.arraycopy(objects,0,newObjects,0,objects.length);
            objects=newObjects;
        }
        objects[index]=o;
        index++;
    }
    List list = new LinkedList();
    public int getSize(){
        return index+1;
    }
}
class Node<T>{
    T node;
    Node<T> next;
    Node<T> parentNode;

    public Node(Node<T> parentNode,T node,Node<T> next) {
        this.node = node;
        this.next = next;
        this.parentNode = parentNode;
    }
}

class LinkList_<T>{
    //开头的对象
    Node<T> startObject;
    //当前对象
    Node<T> lastObject;
    //总数
    int size=0;

    public void add(T t){
        Node<T> one = lastObject;
        Node<T> two = new Node<>(one,t,null);
        lastObject = two;
        if(one == null) startObject = two;
        else one.next = two;
        size++;
    }
    public int getSize(){
        return  size;
    }

    public Node<T> getStartObject(){
        return  startObject;
    }
    public Intarator_ getIntarator(){
        return new LinkListIntarator(this);
    }
}

/**
 * 迭代器；容器遍历
 *  特点：不考虑数组中对象属性
 */
interface Intarator_<T>{
    boolean hasNext();
    T next();
}
class LinkListIntarator<T> implements Intarator_<T>{
    int index = 0;
    LinkList_ linkList_;

    public LinkListIntarator(LinkList_ linkList_) {
        this.linkList_ = linkList_;
    }

    @Override
    public boolean hasNext() {
        return index >= linkList_.getSize() ? false : true ;
    }

    @Override
    public T next() {
        Node<T> one = linkList_.getStartObject();
        for(int i = 0;i<index;i++){
            one = one.next;
        }
        index++;
        return one.node;
    }

    public static void main(String[] args) {
        LinkList_<String> list = new LinkList_();
        list.add("1");
        list.add("2");
        list.add("3");
        Intarator_ intarator = list.getIntarator();
        while (intarator.hasNext()){
            System.out.println(intarator.next());
        }
    }
}
