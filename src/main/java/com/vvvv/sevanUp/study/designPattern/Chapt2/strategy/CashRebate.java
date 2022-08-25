package com.vvvv.sevanUp.study.designPattern.Chapt2.strategy;

public class CashRebate extends CashSuper {
    public CashRebate(Double rebate) {
        this.rebate = rebate;
    }

    private Double rebate;
    @Override
    public Double discount(Double totalMoney) {
        return totalMoney * rebate;
    }
}
