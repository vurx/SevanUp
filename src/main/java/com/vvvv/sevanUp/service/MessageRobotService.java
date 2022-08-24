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


    public String handleMsg(String msg) throws ParserConfigurationException, IOException, SAXException {
        // 1.解析xml --> 发送人 内容
        String[] args = analysisXml(msg);
        // 2.根据内容解析
        judgeSelect(args);
        // 3.构造回复消息主体
        return "";
    }

    /*
    <xml>
        <ToUserName>
            <![CDATA[ww99d788dc16c357a8]]>
        </ToUserName>
        <FromUserName>
            <![CDATA[WuSuHuan]]>
        </FromUserName>
        <CreateTime>1661306454</CreateTime>
        <MsgType>
            <![CDATA[text]]>
        </MsgType>
        <Content>
            <![CDATA[5]]>
        </Content>
        <MsgId>7135256889666861590</MsgId>
        <AgentID>1000002</AgentID>
    </xml>
     */

    /**
     * 分析接受的消息的xml
     * @param sMsg 入参的xml
     * @return String[fromUserName, content]
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public String[] analysisXml(String sMsg) throws ParserConfigurationException, IOException, SAXException {
        // 解析xml
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        StringReader sr = new StringReader(sMsg);
        InputSource is = new InputSource(sr);
        Document document = db.parse(is);
        Element root = document.getDocumentElement();
        NodeList contentNode = root.getElementsByTagName("Content");
        NodeList fromNode = root.getElementsByTagName("FromUserName");
        String[] args = new String[2];
        String content = contentNode.item(0).getTextContent();
        String fromUserName = fromNode.item(0).getTextContent();
        args[0] = fromUserName;
        args[1] = content;
        return args;
    }

    public String judgeSelect(String... args) {
        String fromUserName = args[0];
        String content = args[1];
        String msg;
        if (content.contains("功能") || content.contains("查看")) {
            msg = "1.备忘录\n2.聊天机器人\n3.天气\n4.";
        } else if(content.contains("备忘录")){
            msg = "功能开发中....";
        } else if(content.contains("聊天") || content.contains("机器人")){
            msg = "功能开发中....";
        } else {
            msg = "请输入【功能查看】....";
        }
        return reply(msg);
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
