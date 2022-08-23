package com.vvvv.sevanUp.instance.MessageRobot;

import com.vvvv.sevanUp.util.aes.WXBizMsgCrypt;

public abstract class AgentFactory {
    /**
     * 企业id
     */
    String sCorpID;

    public AgentFactory() {
        this.sCorpID = "ww99d788dc16c357a8";
    }

    /**
     * 每个企业应用需要实现该方法
     * 因为每个应用的Token以及AESKey不一致
     * @return 每个企业应用对应的解密类
     */
    public abstract WXBizMsgCrypt createAgentMsgCrypt();


}
