package com.text.java8.functionalInterface;

import com.cao.xps.common.echart.EchartsVo;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 函数式接口
 */
public class TextFunctionInterface {

    //无参又返回值
    public String getSupplier(Supplier<String> s){
        return s.get();
    }
    //有参数无返回值
    public void getConsumer(Consumer<String> c,String str){
        c.accept(str);
    }
    //有参数无返回值
    public void getFuncton(Integer s,Function<Integer,Integer> f){
       f.apply(s);
    }

    @Test
    public void test1(){
        String s = getSupplier(()->{return "123";});
        System.out.println(s);
        getConsumer(a-> System.out.println(a),"159");

        getFuncton(999,str-> {
            System.out.println(++str);
            return str++;
        });

    }
}
