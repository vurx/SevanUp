package com.vvvv.sevanUp.study.designPattern.Chapt2.factory;

import org.junit.Test;

public class TestDemo {
    @Test
    public void test() {
        CashSuper cash = CashFactory.createCashAccept("0");
        System.out.println(cash.discount(1000d));
        cash = CashFactory.createCashAccept("1");
        System.out.println(cash.discount(1000d));
        cash = CashFactory.createCashAccept("2");
        System.out.println(cash.discount(1000d));
    }
}
