package com.vvvv.sevanUp.study.designPattern.Chapt3.demo;

public class ConcreteDecoratorA extends Decorator{
    private String addedState;

    @Override
    public void operation() {
        super.operation();
        addedState = "new State";
        System.out.println("ConcreteDecoratorA");
    }
}
