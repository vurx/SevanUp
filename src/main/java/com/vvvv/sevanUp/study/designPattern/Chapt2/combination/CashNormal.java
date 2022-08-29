package com.vvvv.sevanUp.study.designPattern.Chapt2.combination;

public class CashNormal extends CashSuper {
    @Override
    public Double discount(Double totalMoney) {
        return totalMoney;
    }
}
