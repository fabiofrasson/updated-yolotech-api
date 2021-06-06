package com.yolotech.defapi.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests((requests) -> requests.anyRequest().authenticated());
    http.httpBasic();
    http.csrf().disable();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    auth
        // enable in memory based authentication with a user named
        // "user" and "admin"
        .inMemoryAuthentication()
        .withUser("user")
        .password(encoder.encode("user"))
        .roles("USER")
        .and()
        .withUser("fabio")
        .password(encoder.encode("frasson"))
        .roles("USER", "ADMIN");
  }
}
