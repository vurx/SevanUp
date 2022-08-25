package com.vvvv.sevanUp.study.designPattern.Chapt1;

import com.vvvv.sevanUp.basic.constant.enums.ReturnInfoEnum;
import com.vvvv.sevanUp.basic.exception.VurxException;

/**
 * 操作类：/
 */
public class OperationDiv extends Operation {
    @Override
    public double getResult() {
        if (numberB == 0) {
            throw new VurxException(ReturnInfoEnum.ERROR);
        }
        return numberA / numberB;
    }
}