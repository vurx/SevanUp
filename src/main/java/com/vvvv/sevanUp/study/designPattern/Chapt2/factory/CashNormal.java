package com.vvvv.sevanUp.study.designPattern.Chapt2.factory;

/**
 * 操作类：正常收银
 */
public class CashNormal extends CashSuper {
    @Override
    public Double discount(Double totalMoney) {
        return totalMoney;
    }
}
