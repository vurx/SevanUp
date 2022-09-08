package com.vvvv.sevanUp.instance.wechat.messageRobot;

import com.vvvv.sevanUp.util.aes.AesException;
import com.vvvv.sevanUp.util.aes.WXBizMsgCrypt;
import lombok.extern.slf4j.Slf4j;

/**
 * 企业微信中的停车应用
 */
@Slf4j
public class AgentParking extends AgentFactory{
    /**
     * 停车应用的消息监听的token
     */
    String sToken;
    /**
     * 停车应用的消息监听的的aes key
     */
    String sEncodingAESKey;


    // TODO 从配置文件中获取
    public AgentParking() {
        this.sToken = "hEbqyyjxwnt3lzB";
        this.sEncodingAESKey = "r2hiRpQ1BlGZxDFNcKckCw8TCuoPeDGLJ87ZCPTrWhd";
    }


    @Override
    public WXBizMsgCrypt createAgentMsgCrypt() {
        WXBizMsgCrypt result = null;
        try {
            result = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
        } catch (AesException e) {
            log.error("构建企业应用解密类失败");
        }
        return result;
    }
}
