package com.vvvv.sevanUp.basic.returnHandler;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @ClassName ReturnHandlerAdaptProxy
 * @Description 返回类统一适配器
 * @Author vvvv
 * @Date 2021/1/2 19:49
 * @Version V1.0
 */
public class ReturnHandlerAdaptProxy implements HandlerMethodReturnValueHandler {
    private final HandlerMethodReturnValueHandler proxyObject;

    public ReturnHandlerAdaptProxy(HandlerMethodReturnValueHandler proxyObject) {
        this.proxyObject = proxyObject;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return proxyObject.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {
        RequestFacade nativeRequest = (RequestFacade) webRequest.getNativeRequest();
        // TODO 部分请求不需要包装回参
        Object result;
        if (!"/accept".equals(nativeRequest.getServletPath())){
            result = returnValue == null ? Return.success() : Return.success(returnValue);
        } else {
            result = returnValue;
        }
        proxyObject.handleReturnValue(result, returnType, mavContainer, webRequest);
    }
}
