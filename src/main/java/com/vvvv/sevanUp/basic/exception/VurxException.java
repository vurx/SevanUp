package com.vvvv.sevanUp.basic.exception;

import com.vvvv.sevanUp.basic.constant.enums.ReturnInfoEnum;
import lombok.Getter;

@Getter
public class VurxException extends RuntimeException {
    private final Integer code;

    public VurxException(ReturnInfoEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
