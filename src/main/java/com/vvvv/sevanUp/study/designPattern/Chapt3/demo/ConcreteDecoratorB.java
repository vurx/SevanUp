package com.vvvv.sevanUp.study.designPattern.Chapt3.demo;

public class ConcreteDecoratorB extends Decorator{

    @Override
    public void operation() {
        super.operation();
        addBehavior();
        System.out.println("ConcreteDecoratorB");
    }

    private void addBehavior() {
        System.out.println("addBehavior");
    }
}
