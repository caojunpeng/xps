package com.text.java8.stream;

import com.text.java8.vo.TestObj;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * stream流
 */
public class TestStream {

    @Test
    public void test(){
        List<TestObj> testObjs = Arrays.asList(new TestObj("张三"),new TestObj("李四"),new TestObj("王五"),new TestObj("赵六"));
        //创建一个0-20的int数组
        //IntStream.range(0,20).forEach(s-> System.out.println(s));
        //过滤
       /* List<Integer> collect = Stream.of("1", "2","4","-2","0").map(Integer::new).filter(s -> s > 0).collect(Collectors.toList());
        System.out.println(collect.size());*/
        //给对象赋值
        /*List<TestObj> testObjs = Arrays.asList(new TestObj("张三"),new TestObj("李四"),new TestObj("王五"));
        testObjs.stream().forEach(s->s.setAge(18));*/

        List<TestObj> collect = IntStream.range(0, 3).mapToObj(s -> testObjs.get(s)).collect(Collectors.toList());
        collect.stream().forEach(s-> System.out.println(s.getName()));
    }
}
