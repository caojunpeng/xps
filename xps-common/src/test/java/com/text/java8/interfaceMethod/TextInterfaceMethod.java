package com.text.java8.interfaceMethod;

import org.junit.Test;

import java.time.Instant;
import java.util.UUID;

/**
 * 接口添加静态方法和默认方法
 */
public class TextInterfaceMethod {

    @Test
    public void text(){
        DefaultClass d = new DefaultClass();
        System.out.println(d.getTime());
        System.out.println(DefaultInterface.getUUId());
    }
}
interface DefaultInterface {
    /**
     * 默认方法
     * @return
     */
    public default String getTime(){
        return Instant.now().toString();
    }

    public static  String getUUId(){
        return  UUID.randomUUID().toString();
    }
}

class DefaultClass implements DefaultInterface{

}