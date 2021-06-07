package com.yolotech.defapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.yolotech.defapi.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  // Configurações gerais da aplicação
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        // Autorizar requests
        .authorizeRequests()
        // Configurar algumas páginas ou conteúdos como whitelist - não necessita de autenticação
        .antMatchers("/", "index", "/css/*", "/js/*")
        .permitAll()
        .antMatchers("/accounts", "/accounts/*")
        .hasRole(ADMIN.name())
        .antMatchers("/categories", "/categories/*")
        .hasRole(ADMIN.name())
        .antMatchers("/courses", "/courses/*")
        .hasRole(COMPANYADMIN.name())
        // Qualquer request deve ser autenticado
        .anyRequest()
        .authenticated()
        .and()
        // Mecanismo de autenticação
        .httpBasic();
  }

  // Buscar infos de users no banco de dados
  @Override
  @Bean
  protected UserDetailsService userDetailsService() {
    UserDetails annaSmithUser =
        User.builder()
            .username("annasmith")
            .password(passwordEncoder.encode("password"))
            .roles(STUDENT.name()) // ROLE_STUDENT
            .build();

    UserDetails lindaUser =
        User.builder()
            .username("linda")
            .password(passwordEncoder.encode("password123"))
            .roles(ADMIN.name())
            .build();

    return new InMemoryUserDetailsManager(annaSmithUser, lindaUser);
  }
}
