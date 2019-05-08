package org.cd.cloud;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @classname: SourceController
 * @description:
 * @author: Danny Chen
 * @create: 2019-04-07 13:50
 */
@RestController
public class SourceController {

    @RequestMapping("/test/sayhello")
    public String hello() {
        return "hello cool man";
    }
}
