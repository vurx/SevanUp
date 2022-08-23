package com.vvvv.sevanUp.service;

import com.vvvv.sevanUp.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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

@Service
@Slf4j
public class MessageRobotService {

    public String analysisXml(String sMsg) throws ParserConfigurationException, IOException, SAXException {
        // 解析xml
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        StringReader sr = new StringReader(sMsg);
        InputSource is = new InputSource(sr);
        Document document = db.parse(is);
        Element root = document.getDocumentElement();
        NodeList nodelist1 = root.getElementsByTagName("Content");
        String content = nodelist1.item(0).getTextContent();
        return content;
    }

    public String jugeSelect(String content) {
        if (content.contains("功能") && content.contains("查看")) {
            String msg = "备忘录\n聊天机器人";
            return reply(msg);
        } else {
            String msg = "请输入”功能查看“...";
            return reply(msg);
        }
    }


    /*
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
     */
    public String reply(String msg){
        String sRespData =
                    "<xml>" +
                        "<ToUserName><![CDATA[ww99d788dc16c357a8]]></ToUserName>" +
                        "<CreateTime>"+ DateUtil.currentTimeSeconds()+"</CreateTime>" +
                        "<MsgType><![CDATA[text]]></MsgType>" +
                        "<Content><![CDATA["+msg+"]]></Content>" +
                        "<AgentID>1000002</AgentID>" +
                    "</xml>";
        return sRespData;
    }
}
