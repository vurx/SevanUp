package com.vvvv.sevanUp.controller;

import com.vvvv.sevanUp.basic.constant.enums.ReturnInfoEnum;
import com.vvvv.sevanUp.basic.exception.VurxException;
import com.vvvv.sevanUp.instance.wechat.messageRobot.AgentParking;
import com.vvvv.sevanUp.service.MessageRobotService;
import com.vvvv.sevanUp.util.DateUtil;
import com.vvvv.sevanUp.util.aes.AesException;
import com.vvvv.sevanUp.util.aes.WXBizMsgCrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@RestController
@Slf4j
public class MessageRobotController {

    @Autowired
    public MessageRobotService messageRobotService;

    /**
     * 企业微信回调使用
     * @param msg_signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
/*    @RequestMapping("/accept")
    public String accept(String msg_signature,String timestamp,String nonce,String echostr) {
        String sToken = "hEbqyyjxwnt3lzB";
        String sCorpID = "ww99d788dc16c357a8";
        String sEncodingAESKey = "r2hiRpQ1BlGZxDFNcKckCw8TCuoPeDGLJ87ZCPTrWhd";
        WXBizMsgCrypt wxcpt = null;
        String s = "";
        try {
            wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
            s = wxcpt.VerifyURL(msg_signature, timestamp, nonce, echostr);
            log.info("s={}", s);
        } catch (AesException e) {
            e.printStackTrace();
        }
        return s;
    }*/

    /**
     * 企业微信-agentID-1000002 消息监听方法
     *
     * @param msg_signature 校验
     * @param timestamp 时间戳
     * @param nonce 随机值
     * @param xmlParam 消息主题
     * @return 需要回复的消息体
     */
    @RequestMapping(value = "/accept", produces = {"application/xml;charset = utf-8"})
    public String accept(String msg_signature, String timestamp, String nonce, @RequestBody String xmlParam) {
        WXBizMsgCrypt wxcpt = new AgentParking().createAgentMsgCrypt();
        try {
            String sMsg = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, xmlParam);
            log.info("解密入参：{}", sMsg);
            String sRespData = messageRobotService.handleMsg(sMsg);
            return wxcpt.EncryptMsg(sRespData, DateUtil.currentTimeSeconds(), nonce);
        } catch (AesException | SAXException | IOException | ParserConfigurationException e) {
            throw new VurxException(ReturnInfoEnum.QY_WECHAT_MSGCRYPT_ERROR);
        }
    }
}
