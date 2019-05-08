package org.cd.cloud;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @classname: HelloService
 * @description:
 * @author: Danny Chen
 * @create: 2019-04-07 12:13
 */
@FeignClient("first-service-provider")
public interface HelloService {

    @RequestMapping("/message")
    String getMsg();
}
