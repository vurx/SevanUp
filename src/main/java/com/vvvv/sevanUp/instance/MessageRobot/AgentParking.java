package com.vvvv.sevanUp.instance.MessageRobot;

import com.vvvv.sevanUp.util.aes.AesException;
import com.vvvv.sevanUp.util.aes.WXBizMsgCrypt;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AgentParking extends AgentFactory{
    String sToken;
    String sEncodingAESKey;


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
