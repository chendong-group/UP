package org.cd.cloud;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @classname: MyFeignClient
 * @description:
 * @author: Danny Chen
 * @create: 2019-03-31 14:38
 */
@FeignClient("first-service-provider")
public interface MyFeignClient {

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    String message(@RequestParam("messageID") Long messageID);

    @MyRequestMapping(url = "/testMyFeign", method = "GET")
    String testMyFeign();

}
