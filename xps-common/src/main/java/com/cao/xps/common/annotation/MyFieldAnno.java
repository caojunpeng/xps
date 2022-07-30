package com.cao.xps.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 自定义注解-属性
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyFieldAnno {
    String value() default "未知";
    int order() default 99;
}

/**
 * 解析注解
 */
class AnalyMyFieldAnno{
    public static List<MyFieldObj> getAnnoName(Class<?> obj){
        Field[] fields = obj.getFields();
        List<MyFieldObj> list=new ArrayList<>();
        for (Field field : fields) {
            MyFieldAnno annotation = field.getAnnotation(MyFieldAnno.class);
            String name = field.getName();
            String value = annotation.value();
            int order = annotation.order();
            MyFieldObj myFieldObj = new MyFieldObj();
            myFieldObj.setName(name);
            myFieldObj.setOrder(order);
            myFieldObj.setValue(value);
            list.add(myFieldObj);
        }
        list.sort(Comparator.comparingInt(MyFieldObj::getOrder));
        return list;
    }
}

/**
 * 解析返回值
 */
class MyFieldObj{
    String name;
    String value;
    int order;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}