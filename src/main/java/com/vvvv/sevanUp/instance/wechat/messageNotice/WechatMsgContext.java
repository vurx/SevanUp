package com.vvvv.sevanUp.instance.wechat.messageNotice;

import com.vvvv.sevanUp.basic.constant.enums.ReturnInfoEnum;
import com.vvvv.sevanUp.basic.exception.VurxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 微信发送消息的策略模式
 */
@Component
public class WechatMsgContext {
    Map<String, WechatMsgSuper> wechats;

    /**
     * 自动装配全部实现的子类
     *
     * @param wechatList
     */
    @Autowired
    public WechatMsgContext(List<WechatMsgSuper> wechatList) {
        wechats = wechatList.stream().collect(
                Collectors.toMap(
                        // 抽象类的实现子类的注解 --> key
                        k -> Objects.requireNonNull(AnnotationUtils.findAnnotation(k.getClass(), WechatMsgType.class)).source(),
                        // 抽象类的实现子类 --> value
                        Function.identity()
                )
        );
    }

    /**
     * 根据param中的msgType选择对应构建消息主题的子类
     *
     * @param param 消息体
     */
    public void sendMsg(Map<String, String> param) {
        WechatMsgSuper wechat;
        switch (param.get("msgType")) {
            case "news":
                wechat = wechats.get("news");
                break;
            case "text":
                wechat = wechats.get("text");
                break;
            default:
                throw new VurxException(ReturnInfoEnum.UNABLE_FIND_EXTENDERS_ERROR);

        }
        // 多肽构建消息主体
        HashMap<String, Object> callParam = wechat.paramConstruct(param);
        // 发送消息
        wechat.sendNews(callParam);
    }
}
