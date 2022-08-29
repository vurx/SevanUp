package com.vvvv.sevanUp.study.designPattern.Chapt2.combination;

public class CashReturn extends CashSuper {
    private Double moneyTarget;
    private Double moneyReturn;
    public CashReturn(Double moneyTarget, Double moneyReturn) {
        this.moneyReturn = moneyReturn;
        this.moneyTarget = moneyTarget;
    }

    @Override
    public Double discount(Double totalMoney) {
        if (totalMoney >= moneyTarget)
            return totalMoney - Math.floor(totalMoney / moneyTarget) * moneyReturn;
        return totalMoney;
    }
}
