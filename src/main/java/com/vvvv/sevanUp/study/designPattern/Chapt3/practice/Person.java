package com.vvvv.sevanUp.study.designPattern.Chapt3.practice;

public class Person implements Component {
    private String name;

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public void show() {
        System.out.println("装扮的" + name);
    }
}
