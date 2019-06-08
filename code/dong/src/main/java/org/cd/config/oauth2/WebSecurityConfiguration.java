package org.cd.config.oauth2;

import org.cd.config.UserAccess;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @classname: WebSecurityConfiguration
 * @description:
 * @author: Danny Chen
 * @create: 2019-06-08 14:31
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("test1").password("test1").roles("USER").and()
                .withUser("test2").password("test2").roles("ADMIN");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/upteam/test/useraccess").hasRole("USER")
                .antMatchers("/upteam/test/aeminaccess").access("hasRole('ADMIN')")
                .anyRequest().permitAll();
    }

    @Bean("userAccess")
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserAccess userAccessBean() {
        return new UserAccess();
    }

}
