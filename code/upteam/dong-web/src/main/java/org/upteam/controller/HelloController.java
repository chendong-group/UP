package org.upteam.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
//import org.upteam.service.HelloService;

/**
 * @classname:
 * @description:
 * @author: Danny Chen
 * @create: 2019-05-08 12:50
 */
@RestController
@RequestMapping(value = "/hello")
public class HelloController {

    //@Autowired
    //private HelloService helloService;

    @RequestMapping(value = StringUtils.EMPTY, method = RequestMethod.GET)
    public String hello() {
        return "Hello";
        //return helloService.getHello().getMessage();
    }

}
