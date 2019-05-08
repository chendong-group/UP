package org.cd.cloud;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @classname: MyStreamClient
 * @description:
 * @author: Danny Chen
 * @create: 2019-03-25 22:43
 */
public interface MyStreamClient {

    String INPUT = "input";
    String OUTPUT = "output";

    @Input(INPUT)
    SubscribableChannel input();

    @Output(OUTPUT)
    MessageChannel output();
}
