package com.cao.xps.common.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式1-原始
 *  例：孩子哭
 */
public class CommonObserver {
    public static boolean ciy = false;

    //喂
    public void feed(){
        while(ciy){

        }
    }
}

/**
 * 观察者模式2-基于面向对象,单个/多个
 *  例：孩子哭
 */

/**
 * 被观察对象
 */
class Chidden{
    public static boolean ciy = true;
    //引入观察者（单个）
    public Mom mom = new Mom();

    public static List<Man> list = new ArrayList<>();
    {
        list.add(new Mom());
        list.add(new Dad());
    }

    //睡醒哭
    public void weekup(){
        ciy = true;
        System.out.println("bigin ciy wuwuwu...");
        mom.feed();
        list.stream().forEach(s->s.feed());
    }
}

interface Man{
    void feed();
}
/**
 * 观察者
 */
class Mom implements Man{
    public void feed(){
        System.out.println("Mom feed food ...");
    }
}
class Dad implements Man{
    public void feed(){
        System.out.println("Dad feed food ...");
    }
}


