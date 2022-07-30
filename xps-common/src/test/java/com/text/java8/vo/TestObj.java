package com.text.java8.vo;

public class TestObj {

    private String name;
    private Integer age;
    private Integer size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public TestObj() {
    }

    public TestObj(String name) {
        this.name = name;
    }

    public TestObj(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public TestObj(String name, Integer age, Integer size) {
        this.name = name;
        this.age = age;
        this.size = size;
    }
}
