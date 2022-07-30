package com.cao.xps.common.designpattern.Composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 主要针对数结构
 */
public class TreeText {

}
abstract class Node{
    abstract void print();
}
class Tree extends Node{
    String name;
    @Override
    void print() {
        System.out.println(name);
    }

    public void setName(String name) {
        this.name = name;
    }
}
class TreeP extends Node{
    List<Node> list = new ArrayList<>();
    String name;
    @Override
    void print() {
        System.out.println(name);
    }
    void add(Node node){
        list.add(node);
    }

    public void setName(String name) {
        this.name = name;
    }
}
