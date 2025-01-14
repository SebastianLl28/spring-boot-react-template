package com.app.backend.configuration.security;

import com.app.backend.configuration.filter.JwtAuthenticationFilter;
import com.app.backend.util.AppConstant;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class HttpSecurityConfig {

  @Autowired
  private AuthenticationProvider authenticationProvider;

  @Autowired
  private JwtAuthenticationFilter authenticationFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(AbstractHttpConfigurer::disable)
        .cors(Customizer.withDefaults())
        .sessionManagement(
            sessionManagementConfigure -> sessionManagementConfigure.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS))
        .authenticationProvider(authenticationProvider)
        .authorizeHttpRequests(auth -> {
          auth.requestMatchers(AppConstant.PUBLIC_BASE_ENDPOINT + "/**").permitAll();
          auth.anyRequest().authenticated();
        })
        .exceptionHandling(exceptionHandling ->
            exceptionHandling.authenticationEntryPoint(
                (request, response, authException) -> response.sendError(
                    HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"))
        )
        .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class).build();
  }
}
