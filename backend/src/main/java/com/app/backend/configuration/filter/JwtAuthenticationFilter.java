package com.app.backend.configuration.filter;

import com.app.backend.persistence.entity.User;
import com.app.backend.persistence.repository.UserRepository;
import com.app.backend.util.JwtUtil;
import com.app.backend.util.AppConstant;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private UserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    String publicBaseEndpoint = AppConstant.PUBLIC_ENDPOINT_PREFIX;

    if (request.getRequestURI().startsWith(publicBaseEndpoint)) {
      filterChain.doFilter(request, response);
      return;
    }

    String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    String jwt = authHeader.split(" ")[1];

    String username;

    try {
      username = jwtUtil.extractUsername(jwt);
    } catch (Exception e) {
      filterChain.doFilter(request, response);
      return;
    }

    User user = userRepository.findByUsername(username).get();

    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());

    SecurityContextHolder.getContext().setAuthentication(authToken);

    filterChain.doFilter(request, response);

  }
}
