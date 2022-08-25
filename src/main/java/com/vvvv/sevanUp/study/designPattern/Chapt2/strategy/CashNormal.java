package com.vvvv.sevanUp.study.designPattern.Chapt2.strategy;

public class CashNormal extends CashSuper {
    @Override
    public Double discount(Double totalMoney) {
        return totalMoney;
    }
}
