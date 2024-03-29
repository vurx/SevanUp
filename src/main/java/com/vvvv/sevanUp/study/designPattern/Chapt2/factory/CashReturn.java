package com.vvvv.sevanUp.study.designPattern.Chapt2.factory;

/**
 * 操作类：返现收银
 */
public class CashReturn extends CashSuper {
    /**
     * 返现目标
     */
    private Double moneyTarget;
    /**
     * 返现金额
     */
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
