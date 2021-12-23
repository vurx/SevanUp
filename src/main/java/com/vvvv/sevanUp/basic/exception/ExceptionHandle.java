package com.vvvv.sevanUp.basic.exception;


import com.vvvv.sevanUp.basic.constant.enums.ReturnInfoEnum;
import com.vvvv.sevanUp.basic.returnHandler.Return;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Return<Object> handle(Exception e) {
        log.error("======ERROR=====START====================================================================", e);
        log.error("======ERROR=====END====================================================================");
        if (e instanceof VurxException) {
            VurxException vurxException = (VurxException) e;
            return Return.error(vurxException.getCode(), vurxException.getMessage());
        }
        return Return.error(ReturnInfoEnum.UNKNOWN_ERROR.getCode(), e.toString());
    }
}
