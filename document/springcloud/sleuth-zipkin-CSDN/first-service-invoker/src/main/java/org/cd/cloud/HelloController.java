package org.cd.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @classname: HelloController
 * @description:
 * @author: Danny Chen
 * @create: 2019-04-03 22:55
 */
@RestController
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/test")
    private List<String> test() {
        List<String> list = new ArrayList<String>();
        //调用第一个服务
        ResponseEntity<String> responseEntity = restTemplate
                .exchange("http://first-service-provider/message", HttpMethod.GET, null, String.class);
        list.add(responseEntity.getBody());

        //调用第二个微服务
        ResponseEntity<String> responseEntity2 = restTemplate
                .exchange("http://second-service-provider/message2", HttpMethod.GET, null, String.class);
        list.add(responseEntity2.getBody());

        return list;
    }

}
