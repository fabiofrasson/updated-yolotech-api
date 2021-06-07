package com.yolotech.defapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.yolotech.defapi.security.ApplicationUserPermission.*;
import static com.yolotech.defapi.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  // Configurações gerais da aplicação
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        // Autorizar requests
        .authorizeRequests()

        // Configurar algumas páginas ou conteúdos como whitelist - não necessita de autenticação
        .antMatchers("/", "index", "/css/*", "/js/*")
        .permitAll()

        // Autenticação baseada em roles
//        .antMatchers("/accounts", "/accounts/*")
//        .hasRole(ADMIN.name())
//        .antMatchers("/categories", "/categories/*")
//        .hasRole(ADMIN.name())
//        .antMatchers("/courses", "/courses/*")
//        .hasRole(COMPANYADMIN.name())

        // Primeira forma de implementar autorização por permissões, via antMatchers
//        .antMatchers(HttpMethod.POST, "/accounts", "/accounts/**")
//        .hasAuthority(ACCOUNT_WRITE.getPermission())
//        .antMatchers(HttpMethod.PUT, "/accounts", "/accounts/**")
//        .hasAuthority(ACCOUNT_WRITE.getPermission())
//        .antMatchers(HttpMethod.DELETE, "/accounts", "/accounts/**")
//        .hasAuthority(ACCOUNT_WRITE.getPermission())
//        .antMatchers(HttpMethod.POST, "/categories", "/categories/**")
//        .hasAuthority(CATEGORY_WRITE.getPermission())
//        .antMatchers(HttpMethod.PUT, "/categories", "/categories/**")
//        .hasAuthority(CATEGORY_WRITE.getPermission())
//        .antMatchers(HttpMethod.DELETE, "/categories", "/categories/**")
//        .hasAuthority(CATEGORY_WRITE.getPermission())
//        .antMatchers(HttpMethod.POST, "/reviews", "/reviews/**")
//        .hasAuthority(REVIEW_WRITE.getPermission())
//        .antMatchers(HttpMethod.PUT, "/reviews", "/reviews/**")
//        .hasAuthority(REVIEW_WRITE.getPermission())
//        .antMatchers(HttpMethod.DELETE, "/reviews", "/reviews/**")
//        .hasAuthority(REVIEW_WRITE.getPermission())
//        .antMatchers(HttpMethod.POST, "/courses", "/courses/**")
//        .hasAuthority(COURSE_WRITE.getPermission())
//        .antMatchers(HttpMethod.POST, "/courses", "/courses/**")
//        .hasAuthority(COURSE_CATEGORY_WRITE.getPermission())
//        .antMatchers(HttpMethod.PUT, "/courses", "/courses/**")
//        .hasAuthority(COURSE_WRITE.getPermission())
//        .antMatchers(HttpMethod.DELETE, "/courses", "/courses/**")
//        .hasAuthority(COURSE_WRITE.getPermission())
//        .antMatchers(HttpMethod.GET, "/courses", "/courses/**")
//        .hasAnyRole(ADMIN.name(), COMPANYADMIN.name(), STUDENT.name())
//        .antMatchers(HttpMethod.GET, "/accounts", "/accounts/**")
//        .hasAuthority(ACCOUNT_READ.getPermission())
//        .antMatchers(HttpMethod.GET, "/reviews", "/reviews/**")
//        .hasAnyRole(ADMIN.name(), COMPANYADMIN.name(), STUDENT.name())
//        .antMatchers(HttpMethod.GET, "/categories", "/categories/**")
//        .hasAuthority(CATEGORY_READ.getPermission())

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
            .username("student")
            .password(passwordEncoder.encode("password"))
            //            .roles(STUDENT.name()) // ROLE_STUDENT
            .authorities(STUDENT.getGrantedAuthorities())
            .build();

    UserDetails lindaUser =
        User.builder()
            .username("admin")
            .password(passwordEncoder.encode("password"))
            //            .roles(ADMIN.name()) // ROLE_ADMIN
            .authorities(ADMIN.getGrantedAuthorities())
            .build();

    UserDetails adamUser =
        User.builder()
            .username("cadmin")
            .password(passwordEncoder.encode("password"))
            //            .roles(COMPANYADMIN.name()) // ROLE_COMPANYADMIN
            .authorities(COMPANYADMIN.getGrantedAuthorities())
            .build();

    return new InMemoryUserDetailsManager(annaSmithUser, lindaUser, adamUser);
  }
}
