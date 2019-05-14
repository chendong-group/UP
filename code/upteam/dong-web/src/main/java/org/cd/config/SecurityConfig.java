package org.cd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SpringBootWebSecurityConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
        http.csrf().disable().authorizeRequests()
                .antMatchers("/upteam/test/useraccess").hasRole("USER")
                .antMatchers("/upteam/test/aeminaccess").access("hasRole('ADMIN')")
                .anyRequest().permitAll()
        .and().exceptionHandling().accessDeniedHandler(myHandleAccessDeniedException);
    }

    @Bean("userAccess")
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserAccess userAccessBean() {
        return new UserAccess();
    }


    //配置用户角色（权限） 需要注入AuthenticationManagerBuilder
    //可以根据自己的实际情况配置用户角色，比如：
    //1. 像下面这样直接初始化到内存中
    //2. 从数据库获取用户角色权限，需要加入Repository的Bean来获取数据库信息
    //3. 从OAuth2 token中解析用户的角色权限，需要加入一个UserDetailsService的实现类的bean，
    //   里面有个方法：loadByName(Name) 可以获取用户的信息，包括权限信息
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("test").password("123456").roles("USER")
                .and()
                .withUser("upteam").password("123456").roles("ADMIN");
    }
}
