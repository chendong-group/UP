package org.cd.cloud;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @classname: MyContractConfig
 * @description:
 * @author: Danny Chen
 * @create: 2019-03-31 16:01
 */
@Configuration
public class MyContractConfig {

    @Bean
    public Contract myFeignContract() {
        return new MyContract();
    }
}
