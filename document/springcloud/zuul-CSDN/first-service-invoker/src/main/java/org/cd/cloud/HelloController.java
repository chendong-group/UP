package org.cd.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @classname: HelloController
 * @description:
 * @author: Danny Chen
 * @create: 2019-04-03 22:55
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/test")
    private String hello() {
        return helloService.getMsg();
    }
}
