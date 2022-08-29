package com.vvvv.sevanUp.study.designPattern.Chapt3.instance;

/**
 * ConcreteComponet 定义了一个具体的对象，也可给这个对象添加一些职责
 */
public class Person implements Component{
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void show(){
        System.out.println("装扮的" + name);
    }
}
