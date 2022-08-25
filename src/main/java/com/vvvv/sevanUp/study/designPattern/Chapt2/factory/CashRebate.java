package com.vvvv.sevanUp.study.designPattern.Chapt2.factory;

public class CashRebate extends CashSuper{
    public CashRebate(double rebate) {
        this.rebate = rebate;
    }

    private double rebate;
    @Override
    public Double discount(Double totalMoney) {
        return totalMoney * rebate;
    }
}
