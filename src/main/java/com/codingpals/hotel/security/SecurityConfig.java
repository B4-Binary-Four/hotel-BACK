package com.codingpals.hotel.security;

import com.codingpals.hotel.security.model.Role;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private CustomAuthenticationProvider authProvider ;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    log.warn("pass for test = "+new BCryptPasswordEncoder().encode("test"));
    auth.authenticationProvider(authProvider) ;
  }

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
        .antMatchers(HttpMethod.GET).hasAnyRole(Role.ADMIN.getRole() , Role.CLIENT.getRole())
        .antMatchers(HttpMethod.POST, "/users").hasAnyRole(Role.ADMIN.getRole())
        .antMatchers(HttpMethod.PUT).hasAnyRole(Role.ADMIN.getRole())
        .and()
        .cors()
        .and()
        .formLogin().disable()
        .logout().disable()
        .csrf().disable()
        .httpBasic();
  }
}
