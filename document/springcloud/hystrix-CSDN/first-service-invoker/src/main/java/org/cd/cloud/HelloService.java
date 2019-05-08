package org.cd.cloud;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @classname: HelloService
 * @description:
 * @author: Danny Chen
 * @create: 2019-04-03 22:50
 */
@Component
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;


    /*@HystrixCommand(fallbackMethod = "getMessageFallback", groupKey = "MyGroup",
            commandKey = "MyCommandKey", threadPoolKey = "MyCommandPool",
            commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")},
            threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "1")}
    )*/
    @HystrixCommand(fallbackMethod = "getMessageFallback")
    @CacheResult
    public String getMessage() {
        ResponseEntity<String> responseEntity = restTemplate
                .exchange("http://first-service-provider/message", HttpMethod.GET, null, String.class);
        String normalMessage = responseEntity.getBody();
        return normalMessage;
    }

    public String getMessageFallback() {
        return "default message";
    }
}
