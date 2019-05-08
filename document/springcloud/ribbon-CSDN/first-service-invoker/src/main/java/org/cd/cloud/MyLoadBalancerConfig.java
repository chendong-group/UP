package org.cd.cloud;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @classname: MyLoadBalancerConfig
 * @description:
 * @author: Danny Chen
 * @create: 2019-03-17 19:51
 */
@Configuration
public class MyLoadBalancerConfig {

    @Autowired(required = false)
    @MyLoadBalanced
    private List<RestTemplate> myTemplates = Collections.emptyList();

    public SmartInitializingSingleton myLoadBalancedRestTemplate() {
        return new SmartInitializingSingleton() {
            @Override
            public void afterSingletonsInstantiated() {
                for (RestTemplate restTemplate : myTemplates) {
                    MyInterceptor myInterceptor = new MyInterceptor();
                    List list = new ArrayList(restTemplate.getInterceptors());
                    list.add(myInterceptor);
                    restTemplate.setInterceptors(list);
                }
            }
        };
    }
}
