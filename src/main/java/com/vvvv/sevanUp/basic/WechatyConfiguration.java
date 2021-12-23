package com.vvvv.sevanUp.basic;

import io.github.wechaty.Wechaty;
import io.github.wechaty.WechatyOptions;
import io.github.wechaty.schemas.PuppetOptions;
import io.github.wechaty.user.Contact;
import io.github.wechaty.user.Room;
import io.github.wechaty.utils.QrcodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class WechatyConfiguration {

    public WechatyConfiguration() {
        PuppetOptions po = new PuppetOptions();
        po.setEndPoint("192.168.31.179:9003");
        po.setToken("vvvv");
        WechatyOptions wo = new WechatyOptions();
        wo.setPuppetOptions(po);
        Wechaty bot = Wechaty.instance(wo);
        bot.onScan((qrcode, statusScanStatus, data) -> {
            assert qrcode != null;
            log.info(QrcodeUtils.getQr(qrcode));
        });
        bot.onMessage(t -> {
            // 排除自己发的消息
            if (t.self()) {
                return;
            }
            // 获取群聊（如果是个人发送，room为null）
            Room room = t.room();
            // 拿到发送者
            Contact from = t.from();
            System.out.println(from.getId());
            String type = t.type().toString();

            // 群聊的文本消息
            if (null != room) {
                try {
                    //群聊的名字
                    String roomName = room.getTopic().get();
                    if (("Text").equals(type)) {
                        log.info("收到来自群聊【{}】的[{}]的消息:{}", roomName, from.name(), t.content());
                    }
                } catch (Exception e) {
                    log.error("", e);
                }
            }
            // 非群聊的文本消息
            if (null == room) {
                if (("Text").equals(type)) {
                    log.info("收到来自[{}]的消息:{}", t.from().name(), t.content());
                    if (t.text().equals("牛逼")) {
                        for (int i = 0; i < 10; i++) {
                            from.say("这是机器人回复x" + i);
                        }
                    }
                }
            }
        });
        bot.start(true);
        Contact contact = new Contact(bot, "@8ad86be29b898bc811ecf302310b3bbf");
        bot.say("test").say("test", contact);
    }


}
