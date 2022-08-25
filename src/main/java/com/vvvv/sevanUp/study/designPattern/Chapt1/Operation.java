package com.vvvv.sevanUp.study.designPattern.Chapt1;

/**
 * 操作抽象类（需要实际预算法则实现该类）
 */
public abstract class Operation {
    /**
     * 计算参数a
     */
    Double numberA = 0d;

    /**
     * 计算参数b
     */
    Double numberB = 0d;

    public abstract double getResult();
}
