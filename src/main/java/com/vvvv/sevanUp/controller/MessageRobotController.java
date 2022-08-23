package com.vvvv.sevanUp.controller;

import com.vvvv.sevanUp.instance.MessageRobot.AgentParking;
import com.vvvv.sevanUp.instance.park.Parking;
import com.vvvv.sevanUp.mapper.excel.SubsinstSynTempMapper;
import com.vvvv.sevanUp.util.aes.AesException;
import com.vvvv.sevanUp.util.aes.WXBizMsgCrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

@RestController
@Slf4j
public class MessageRobotController {

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
     * @param msg_signature
     * @param timestamp
     * @param nonce
     * @param xmlParam
     * @return
     */
    @RequestMapping(value = "/accept",produces = {"application/xml;charset = utf-8"})
    public String accept(String msg_signature, String timestamp, String nonce, @RequestBody String xmlParam) {
        WXBizMsgCrypt wxcpt = new AgentParking().createAgentMsgCrypt();
        try {
            String sMsg = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, xmlParam);
            log.info("解密入参：{}", sMsg);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            StringReader sr = new StringReader(sMsg);
            InputSource is = new InputSource(sr);
            Document document = db.parse(is);

            Element root = document.getDocumentElement();
            NodeList nodelist1 = root.getElementsByTagName("Content");
            String Content = nodelist1.item(0).getTextContent();


            log.info("接收到信息：{}", Content);

            String sRespData =
                    "<xml>" +
                    "<ToUserName><![CDATA[ww99d788dc16c357a8]]></ToUserName>" +
                    "<FromUserName><![CDATA[WuSuHuan]]></FromUserName>" +
                    "<CreateTime>1348831860</CreateTime>" +
                    "<MsgType><![CDATA[text]]></MsgType>" +
                    "<Content><![CDATA[this is a test]]></Content>" +
                    "<MsgId>1234567890123456</MsgId>" +
                    "<AgentID>1000002</AgentID>" +
                    "</xml>";
            String sEncryptMsg = wxcpt.EncryptMsg(sRespData, (System.currentTimeMillis()+"").substring(0,10), "1372623149");
            return sEncryptMsg;
        } catch (AesException | ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return "";
    }
}
