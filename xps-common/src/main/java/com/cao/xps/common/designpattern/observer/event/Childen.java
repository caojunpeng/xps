package com.cao.xps.common.designpattern.observer.event;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式(最终)
 */
public class Childen {
    public static boolean ciy = true;
    public static List<Man> list = new ArrayList<>();
    {
        list.add(new Mom());
        list.add(new Dad());
    }
    public void weekUp(){
        while(ciy){
            WeekUpEvent event = new WeekUpEvent("door",1L);
            list.stream().forEach(s->{
                s.feed(event);
            });
            ciy = false;
        }
    }
}

/**
 * 事件本身
 */
class WeekUpEvent{
    public Childen childen;
    public String loc;
    public Long time;

    public WeekUpEvent(String loc, Long time) {
        this.loc = loc;
        this.time = time;
    }
}

interface Man{
    void feed(WeekUpEvent weekUpEvent);
}
/**
 * 观察者
 */
class Mom implements Man{
    @Override
    public void feed(WeekUpEvent weekUpEvent) {

    }
}
class Dad implements Man{
    @Override
    public void feed(WeekUpEvent weekUpEvent) {

    }
}
