package com.wujie.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.annotation.Resource;

@Configuration
public class SecurityConfiguration {
    @Resource
    private AppAutheticationSuccessHandler appAutheticationSuccessHandler;

    @Resource
    private AppAccessDenyHandler appAccessDenyHandler;

    @Resource
    private AppAuthenticationFailHandler appAuthenticationFailHandler;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/user/login")
                .permitAll()
                .antMatchers("/user/add").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin()
                .successHandler(appAutheticationSuccessHandler)
                .failureHandler(appAuthenticationFailHandler)
                .permitAll()
                .and().exceptionHandling().accessDeniedHandler(appAccessDenyHandler)
                .and().csrf().disable();
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
//        return NoOpPasswordEncoder.getInstance();
    }
}
