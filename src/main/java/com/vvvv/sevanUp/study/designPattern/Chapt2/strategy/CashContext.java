package com.vvvv.sevanUp.study.designPattern.Chapt2.strategy;

/**
 * 策略上下文类
 */
public class CashContext {
    /**
     * 抽象策略
     */
    CashSuper cs;

    /**
     * 抽象策略的具体策略实现
     * @param cs 具体的策略实例
     */
    public CashContext(CashSuper cs) {
        this.cs = cs;
    }

    /**
     * 调用具体策略实例的discount方法
     * @param totalMoney
     * @return
     */
    public Double getResult(Double totalMoney) {
        return cs.discount(totalMoney);
    }
}
