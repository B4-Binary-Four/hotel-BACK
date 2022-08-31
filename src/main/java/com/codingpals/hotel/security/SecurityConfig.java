package com.codingpals.hotel.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
        .antMatchers(HttpMethod.GET,"/").authenticated()
        .antMatchers(HttpMethod.GET, "/ping").permitAll()
        .antMatchers(HttpMethod.GET, "/rooms").permitAll()
        .antMatchers(HttpMethod.POST,"/bookings").permitAll()
        .antMatchers(HttpMethod.PUT,"/bookings").permitAll()
        .antMatchers(HttpMethod.GET).authenticated()
        .antMatchers(HttpMethod.PUT).authenticated()
        .antMatchers(HttpMethod.DELETE).authenticated()
        .and()
        .cors()
        .and()
        .formLogin().disable()
        .logout().disable()
        .csrf().disable()
        .httpBasic();
  }
}
