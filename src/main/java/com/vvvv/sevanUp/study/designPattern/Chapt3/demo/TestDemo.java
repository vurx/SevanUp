package com.vvvv.sevanUp.study.designPattern.Chapt3.demo;

import org.junit.Test;

public class TestDemo {
    @Test
    public void test() {
        ConcreteComponet c = new ConcreteComponet();
        ConcreteDecoratorA d1 = new ConcreteDecoratorA();
        ConcreteDecoratorB d2 = new ConcreteDecoratorB();
        d1.setComponent(c);
        d2.setComponent(d1);
        d2.operation();
    }
}
