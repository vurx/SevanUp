package com.vvvv.sevanUp.study.designPattern.Chapt2.factory;

import com.vvvv.sevanUp.basic.constant.enums.ReturnInfoEnum;
import com.vvvv.sevanUp.basic.exception.VurxException;

/**
 * 操作类的简单工厂
 */
public class CashFactory {

    public static CashSuper createCashAccept(String flag) {
        CashSuper cashSuper;
        switch (flag) {
            case "0":
                cashSuper = new CashNormal();
                break;
            case "1":
                cashSuper = new CashRebate(0.8d);
                break;
            case "2":
                cashSuper = new CashReturn(300d, 100d);
                break;
            default:
                throw new VurxException(ReturnInfoEnum.ERROR);
        }
        return cashSuper;
    }
}
