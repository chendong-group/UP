package org.cd.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @classname: TestController
 * @description:
 * @author: Danny Chen
 * @create: 2019-03-25 22:54
 */
@RestController
public class TestController {

    @Autowired
    private MyStreamClient myStreamClient;

    @RequestMapping("/sendmsg")
    public String sendMessage() {
        Message message = MessageBuilder.withPayload("This is a test message").build();
        myStreamClient.output().send(message);
        return "SEND SUCCESSFUL";
    }
}
