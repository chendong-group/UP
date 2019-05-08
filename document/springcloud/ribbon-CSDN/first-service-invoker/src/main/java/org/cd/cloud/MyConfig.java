package org.cd.cloud;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @classname: MyConfig
 * @description:
 * @author: Danny Chen
 * @create: 2019-03-14 22:19
 */
//@Configuration
public class MyConfig {

    @Bean
    public IRule getRule() {
        return new MyRule();
    }

    @Bean
    public IPing getPing() {
        return new MyPing();
    }
}
