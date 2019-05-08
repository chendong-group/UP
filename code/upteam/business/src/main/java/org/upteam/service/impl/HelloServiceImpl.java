package org.upteam.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.upteam.model.Hello;
import org.upteam.service.HelloService;
import org.upteam.utils.HelloUtils;

/**
 * @classname: HelloServiceImpl
 * @description:
 * @author: Danny Chen
 * @create: 2019-05-08 13:23
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Value("${up.hello.message}")
    private String helloMessage;

    @Override
    public Hello getHello() {
        Hello hello = new Hello();
        hello.setMessage(HelloUtils.trimValue(helloMessage));
        return hello;
    }
}
