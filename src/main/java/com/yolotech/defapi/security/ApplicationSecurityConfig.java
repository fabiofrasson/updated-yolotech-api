package com.yolotech.defapi.security;

import com.yolotech.defapi.auth.ApplicationUserService;
import com.yolotech.defapi.jwt.JwtConfig;
import com.yolotech.defapi.jwt.JwtTokenVerifier;
import com.yolotech.defapi.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

  private final PasswordEncoder passwordEncoder;
  private final ApplicationUserService applicationUserService;
  private final SecretKey secretKey;
  private final JwtConfig jwtConfig;

  @Autowired
  public ApplicationSecurityConfig(
      PasswordEncoder passwordEncoder,
      ApplicationUserService applicationUserService,
      SecretKey secretKey,
      JwtConfig jwtConfig) {
    this.passwordEncoder = passwordEncoder;
    this.applicationUserService = applicationUserService;
    this.secretKey = secretKey;
    this.jwtConfig = jwtConfig;
  }

  // Configurações gerais da aplicação
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilter(
            new JwtUsernameAndPasswordAuthenticationFilter(
                authenticationManager(), jwtConfig, secretKey))
        .addFilterAfter(
            new JwtTokenVerifier(secretKey, jwtConfig),
            JwtUsernameAndPasswordAuthenticationFilter.class)
        // .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        //            .and()
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
        .authenticated();
    //        .and()
    // Mecanismo de autenticação
    // .httpBasic()
    //        .formLogin()
    //        .loginPage("/login")
    //        .permitAll()
    //        .defaultSuccessUrl("/welcome", true)
    //        .passwordParameter("password")
    //        .usernameParameter("username")
    //        .and()
    // Ativar a função remember me
    //        .rememberMe() // default: 2 weeks
    // Definir expiração do remember me para 21 dias
    //        .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
    // Key para o token remember me
    //        .key("A jack of all trades is a master of none, but oftentimes better than a master of
    // one")
    //        .rememberMeParameter("remember-me")
    //        .and()
    // Configurações de logout
    //        .logout()
    //        .logoutUrl("/logout")
    // É boa prática deixar o método Http como POST quando o CSRF está ligado (padrão do Spring
    // Security)
    //        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
    //        .clearAuthentication(true)
    //        .invalidateHttpSession(true)
    //        .deleteCookies("JSESSIONID", "remember-me")
    //        .logoutSuccessUrl("/login");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(daoAuthenticationProvider());
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder);
    provider.setUserDetailsService(applicationUserService);
    return provider;
  }

  // Buscar infos de users no banco de dados
  //  @Override
  //  @Bean
  //  protected UserDetailsService userDetailsService() {
  //    UserDetails annaSmithUser =
  //        User.builder()
  //            .username("student")
  //            .password(passwordEncoder.encode("password"))
  //            //            .roles(STUDENT.name()) // ROLE_STUDENT
  //            .authorities(STUDENT.getGrantedAuthorities())
  //            .build();
  //
  //    UserDetails lindaUser =
  //        User.builder()
  //            .username("admin")
  //            .password(passwordEncoder.encode("password"))
  //            //            .roles(ADMIN.name()) // ROLE_ADMIN
  //            .authorities(ADMIN.getGrantedAuthorities())
  //            .build();
  //
  //    UserDetails adamUser =
  //        User.builder()
  //            .username("cadmin")
  //            .password(passwordEncoder.encode("password"))
  //            //            .roles(COMPANYADMIN.name()) // ROLE_COMPANYADMIN
  //            .authorities(COMPANYADMIN.getGrantedAuthorities())
  //            .build();
  //
  //    return new InMemoryUserDetailsManager(annaSmithUser, lindaUser, adamUser);
  //  }
}
