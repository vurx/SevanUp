package com.vvvv.sevanUp.study.designPattern.Chapt1;

public class Test {
    public static void main(String[] args) {
        Operation operate = OperationFactory.createOperate("+");
        operate.numberA = 20d;
        operate.numberB = 10d;
        System.out.println("operate.getResult() = " + operate.getResult());

        operate = OperationFactory.createOperate("-");
        operate.numberA = 20d;
        operate.numberB = 10d;
        System.out.println("operate.getResult() = " + operate.getResult());

        operate = OperationFactory.createOperate("*");
        operate.numberA = 20d;
        operate.numberB = 10d;
        System.out.println("operate.getResult() = " + operate.getResult());

        operate = OperationFactory.createOperate("/");
        operate.numberA = 20d;
        operate.numberB = 10d;
        System.out.println("operate.getResult() = " + operate.getResult());

        operate = OperationFactory.createOperate("x");
        operate.numberA = 20d;
        operate.numberB = 10d;
        System.out.println("operate.getResult() = " + operate.getResult());
    }
}
