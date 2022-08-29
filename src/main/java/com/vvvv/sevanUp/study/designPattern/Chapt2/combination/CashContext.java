package com.vvvv.sevanUp.study.designPattern.Chapt2.combination;

import com.vvvv.sevanUp.basic.constant.enums.ReturnInfoEnum;
import com.vvvv.sevanUp.basic.exception.VurxException;

/**
 * 策略与简单工厂结合类
 */
public class CashContext {
    /**
     * 抽象类
     */
    CashSuper cs;

    /**
     * 抽象类的实现初始化
     * @param type 传入的类型
     */
    public CashContext(String type) {
        switch (type) {
            case "normal":
                cs = new CashNormal();
                break;
            case "rebate":
                cs = new CashRebate(0.77d);
                break;
            case "return":
                cs = new CashReturn(300d,100d);
                break;
            default:
                throw new VurxException(ReturnInfoEnum.ERROR);
        }
    }

    public Double getResult(Double totalMoney) {
        return cs.discount(totalMoney);
    }
}
