package com.vvvv.sevanUp.study.designPattern.Chapt2.combination;

import org.junit.Test;

public class TestDemo {
    @Test
    public void test() {
        CashContext cc;
        cc = new CashContext("normal");
        System.out.println(cc.getResult(1000d));
        cc = new CashContext("rebate");
        System.out.println(cc.getResult(1000d));
        cc = new CashContext("return");
        System.out.println(cc.getResult(1000d));
    }
}
