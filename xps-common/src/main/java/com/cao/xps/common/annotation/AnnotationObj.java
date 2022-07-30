package com.cao.xps.common.annotation;

import java.util.*;

/**
 * 注解测试对象
 */
public class AnnotationObj {
    @MyFieldAnno(value="姓名",order = 4)
    public String name;
    @MyFieldAnno(value="年龄",order = 3)
    public String age;
    @MyFieldAnno(value="性别",order = 2)
    public String sex;
    @MyFieldAnno(value="身份证号",order = 1)
    public String identify;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }
}

/**
 * 获取注解逻辑
 */
class TextMyFileAnno{
    public static void main(String[] args) {
        AnalyMyFieldAnno analyMyFieldAnno = new AnalyMyFieldAnno();
        List<MyFieldObj> annoName = analyMyFieldAnno.getAnnoName(AnnotationObj.class);
        for (MyFieldObj m:annoName){
            System.out.println(m.getValue());
        }
    }
}
class text{

    public static void main(String[] args) {
        int i = 0;
        while (i<10){
            try {
                Thread.sleep(1000);
                i++;
                System.out.println(System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
