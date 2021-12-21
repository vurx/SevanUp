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
            Contact from = t.from();
            Room room = t.room();
            if (null != room && t.type().toString().equals("Text")) {
                try {
                    log.info("收到来自群聊【{}】的[{}]的消息:{}", room.getTopic().get(), t.from().name(), t.content());
                } catch (Exception e) {
                    log.error("", e);
                }
            }
            if (null == room && !t.from().name().equals("VVVV")) {
                log.info("收到来自[{}]的消息:{}", t.from().name(), t.content());
                if (t.text().equals("牛逼")) {
                    for (int i = 0; i < 10; i++) {
                        from.say("这是机器人回复x" + i);
                    }
                }
            }

        });
        bot.start(true);
    }


}
