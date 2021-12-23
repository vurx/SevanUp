package com.vvvv.sevanUp.basic.returnHandler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName RenturnHandlerAdapter
 * @Description 接口返回统一处理配置类
 * @Author vvvv
 * @Date 2021/1/2 19:41
 * @Version V1.0
 */
@Configuration
@RequiredArgsConstructor
public class RenturnHandlerAdapter implements InitializingBean {
    private final RequestMappingHandlerAdapter handlerAdapter;

    @Override
    public void afterPropertiesSet() {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = handlerAdapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> newList = new ArrayList<>();
        Objects.requireNonNull(returnValueHandlers).forEach(
                t -> {
                    if (t instanceof RequestResponseBodyMethodProcessor) newList.add(new ReturnHandlerAdaptProxy(t));
                    else newList.add(t);
                }
        );
        handlerAdapter.setReturnValueHandlers(newList);
    }
}
