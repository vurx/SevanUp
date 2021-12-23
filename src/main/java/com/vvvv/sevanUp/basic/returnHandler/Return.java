package com.vvvv.sevanUp.basic.returnHandler;

import com.vvvv.sevanUp.basic.constant.enums.ReturnInfoEnum;
import lombok.Data;

/**
 * @ClassName Return
 * @Description 返回类方法封装
 * @Author vvvv
 * @Date 2021/1/2 19:37
 * @Version V1.0
 */
@Data
public class Return<T> {
    private Integer code;
    private String msg;
    private T data;

    private Return(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Return(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Return<T> success() {
        return new Return<T>(ReturnInfoEnum.SUCCESS.getCode(), ReturnInfoEnum.SUCCESS.getMsg());
    }

    public static <T> Return<T> success(T data) {
        return new Return<>(ReturnInfoEnum.SUCCESS.getCode(), ReturnInfoEnum.SUCCESS.getMsg(), data);
    }

    public static <T> Return<T> error(Integer code, String msg) {
        return new Return<>(code, msg);
    }

}
