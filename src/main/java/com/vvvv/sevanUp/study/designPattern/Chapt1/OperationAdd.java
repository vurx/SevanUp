package com.vvvv.sevanUp.study.designPattern.Chapt1;

/**
 * 操作类：+
 */
public class OperationAdd extends Operation {
    @Override
    public double getResult() {
        return numberA + numberB;
    }
}