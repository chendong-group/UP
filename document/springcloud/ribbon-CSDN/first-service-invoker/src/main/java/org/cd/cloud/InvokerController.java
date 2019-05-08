package org.cd.cloud;

import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * @classname: InvokerController
 * @description:
 * @author: Danny Chen
 * @create: 2019-03-14 22:24
 */
@RestController
@Configuration
public class InvokerController {

    //==================Test Annotation LoadBalance==========================
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/test")
    public String getMsg() {
        RestTemplate restTemplate = getRestTemplate();
        ResponseEntity<String> result = restTemplate.exchange("http://first-service-provider/message", HttpMethod.GET, null, String.class);
        return result.getBody();
    }

    //==================Test Spring LoadBalancerClient==========================
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping(value = "/testLbClient", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceInstance testLbClient() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("first-service-provider");
        return serviceInstance;
    }

    //==================Test Origin Ribbon API==========================
    @Autowired
    private SpringClientFactory springClientFactory;

    @RequestMapping(value = "/testLbApi")
    public String testLbApi() {
        System.out.println("=======输出默认配置：");
        //获取默认配置
        ZoneAwareLoadBalancer zalb = (ZoneAwareLoadBalancer) springClientFactory.getLoadBalancer("default");
        System.out.println("IClientConfig: " + springClientFactory.getLoadBalancer("default").getClass().getName());
        System.out.println("IRule: " + zalb.getRule().getClass().getName());
        System.out.println("IPing: " + zalb.getPing().getClass().getName());
        System.out.println("ServerList: " + zalb.getServerListImpl().getClass().getName());

        System.out.println("ServerListFilter: " + zalb.getFilter().getClass().getName());
        System.out.println("ILoadBalancer: " + zalb.getClass().getName());
        System.out.println("PingInterval: " + zalb.getPingInterval());

        System.out.println("=======输出first-service-provider配置：");
        //获取service-provider的配置
        ZoneAwareLoadBalancer zalb2 = (ZoneAwareLoadBalancer) springClientFactory.getLoadBalancer("first-service-provider");
        System.out.println("IClientConfig: " + springClientFactory.getLoadBalancer("first-service-provider").getClass().getName());
        System.out.println("IRule: " + zalb2.getRule().getClass().getName());
        System.out.println("IPing: " + zalb2.getPing().getClass().getName());
        System.out.println("ServerList: " + zalb2.getServerListImpl().getClass().getName());

        System.out.println("ServerListFilter: " + zalb2.getFilter().getClass().getName());
        System.out.println("ILoadBalancer: " + zalb2.getClass().getName());
        System.out.println("PingInterval: " + zalb2.getPingInterval());

        return "Success";
    }

}
