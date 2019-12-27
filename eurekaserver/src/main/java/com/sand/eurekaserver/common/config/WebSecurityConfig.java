package com.sand.eurekaserver.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    /*开启服务器密码后  无法注册  需要设置以下内容*/
    protected void configure(HttpSecurity http) throws Exception {
         http.csrf().disable(); // 关闭csrf
         http.authorizeRequests().anyRequest().authenticated().and().httpBasic(); // 开启认证
    }
}
