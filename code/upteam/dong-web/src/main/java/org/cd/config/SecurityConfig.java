package org.cd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SpringBootWebSecurityConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @classname: SecurityConfig
 * @description:
 * @author: Danny Chen
 * @create: 2019-05-12 15:45
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler myHandleAccessDeniedException;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/**").permitAll()
        .and().exceptionHandling().accessDeniedHandler(myHandleAccessDeniedException);
    }

    @Bean("userAccess")
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserAccess userAccessBean() {
        return new UserAccess();
    }
}
