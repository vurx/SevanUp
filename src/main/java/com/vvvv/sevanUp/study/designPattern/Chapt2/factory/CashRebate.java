package com.vvvv.sevanUp.study.designPattern.Chapt2.factory;

/**
 * 操作类：打折收银
 */
public class CashRebate extends CashSuper{
    /**
     * 含参构造器，初始化打折利率
     * @param rebate
     */
    public CashRebate(double rebate) {
        this.rebate = rebate;
    }

    private double rebate;
    @Override
    public Double discount(Double totalMoney) {
        return totalMoney * rebate;
    }
}
