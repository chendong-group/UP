package org.cd.cloud;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * @classname: MyStreamReceiver
 * @description:
 * @author: Danny Chen
 * @create: 2019-03-25 22:49
 */
@Component
@EnableBinding(MyStreamClient.class)
public class MyStreamReceiver {

    @StreamListener(MyStreamClient.INPUT)
    public void receiver(String message) {
        System.out.println("接收到的消息：" + message);
    }
}
