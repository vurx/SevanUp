package com.vvvv.sevanUp.study.designPattern.Chapt1;


import com.vvvv.sevanUp.basic.constant.enums.ReturnInfoEnum;
import com.vvvv.sevanUp.basic.exception.VurxException;

/**
 * 操作类的简单工厂
 */
public class OperationFactory {
    public static Operation createOperate(String operator) {
        Operation oper;
        switch (operator) {
            case "+":
                oper = new OperationAdd();
                break;
            case "-":
                oper = new OperationSub();
                break;
            case "*":
                oper = new OperationMul();
                break;
            case "/":
                oper = new OperationDiv();
                break;
            default:
                throw new VurxException(ReturnInfoEnum.ERROR);
        }
        return oper;
    }
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
