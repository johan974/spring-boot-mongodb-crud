package com.djamware.angular.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.djamware.angular.service.CustomerUserDetailsService;

@Configuration
@EnableWebSecurity
public class CustomUserDetailsSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsService userDetailsService = mongoUserDetails();
        auth.userDetailsService(userDetailsService);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers("/contact**/**").hasRole( "USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                  .permitAll()
                  .defaultSuccessUrl( "/")
                .and()
                .httpBasic();
    }
    @Bean
    public UserDetailsService mongoUserDetails() {
        return new CustomerUserDetailsService();
    }
}