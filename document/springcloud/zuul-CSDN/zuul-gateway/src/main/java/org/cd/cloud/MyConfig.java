package org.cd.cloud;

import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @classname: MyConfig
 * @description:
 * @author: Danny Chen
 * @create: 2019-04-07 14:16
 */
@Configuration
public class MyConfig {

    @Bean
    public PatternServiceRouteMapper patternServiceRouteMapper() {
        return new PatternServiceRouteMapper("(zuul)-(?<module>.+)-(service)",
                "${module}/**");
    }
}
