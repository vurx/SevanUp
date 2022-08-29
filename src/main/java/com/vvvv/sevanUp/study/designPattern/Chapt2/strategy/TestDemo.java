package com.vvvv.sevanUp.study.designPattern.Chapt2.strategy;

import com.vvvv.sevanUp.basic.constant.enums.ReturnInfoEnum;
import com.vvvv.sevanUp.basic.exception.VurxException;
import org.junit.Test;

public class TestDemo {
    @Test
    public void test() {
        CashContext cc;
        switch ("flag") {
            case "0":
                cc = new CashContext(new CashNormal());
                break;
            case "1":
                cc = new CashContext(new CashRebate(0.77d));
                break;
            case "2":
                cc = new CashContext(new CashReturn(300d, 100d));
                break;
            default:
                throw new VurxException(ReturnInfoEnum.ERROR);
        }
        System.out.println(cc.getResult(1000d));
    }
}
