package com.yolotech.defapi.security;

import com.google.common.collect.ImmutableList;
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
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

  private final PasswordEncoder passwordEncoder;
  private final ApplicationUserService applicationUserService;
  private final SecretKey secretKey;
  private final JwtConfig jwtConfig;
  //  private Environment env;

  @Autowired
  public ApplicationSecurityConfig(
      PasswordEncoder passwordEncoder,
      ApplicationUserService applicationUserService,
      SecretKey secretKey,
      JwtConfig jwtConfig
      //          Environment env
      ) {
    this.passwordEncoder = passwordEncoder;
    this.applicationUserService = applicationUserService;
    this.secretKey = secretKey;
    this.jwtConfig = jwtConfig;
    //    this.env = env;
  }

  // Configurações gerais da aplicação
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.cors()
        .and()
        .csrf()
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

        // Autorizar requests
        .authorizeRequests()

        // Configurar algumas páginas ou conteúdos como whitelist - não necessita de autenticação
//        .antMatchers("/", "index", "/css/*", "/js/*")
//        .permitAll()

        // Qualquer request deve ser autenticado
        .anyRequest()
                    .permitAll();
//        .authenticated();
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

  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("http://localhost:3000");
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }

//  @Bean
//  public CorsConfigurationSource corsConfigurationSource() {
//    final CorsConfiguration configuration = new CorsConfiguration();
//    configuration.setAllowedOrigins(ImmutableList.of("*"));
//    configuration.setAllowedMethods(ImmutableList.of("HEAD",
//            "GET", "POST", "PUT", "DELETE", "PATCH"));
//    // setAllowCredentials(true) is important, otherwise:
//    // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
//    configuration.setAllowCredentials(true);
//    // setAllowedHeaders is important! Without it, OPTIONS preflight request
//    // will fail with 403 Invalid CORS request
//    configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
//    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//    source.registerCorsConfiguration("/**", configuration);
//    return source;
//  }




  //    CorsConfigurationSource corsConfigurationSource() {
  //      final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
  //
  //      CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
  //      source.registerCorsConfiguration("/**", corsConfiguration);
  //
  //      return source;
  //    }

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
