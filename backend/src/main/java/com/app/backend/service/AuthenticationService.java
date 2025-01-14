package com.app.backend.service;

import com.app.backend.persistence.entity.User;
import com.app.backend.persistence.repository.UserRepository;
import com.app.backend.presentation.dto.AuthenticationRequest;
import com.app.backend.presentation.dto.AuthenticationResponse;
import com.app.backend.util.JwtUtil;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtUtil;

  public AuthenticationResponse login(AuthenticationRequest authenticationRequest){
    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
        authenticationRequest.getUsername(), authenticationRequest.getPassword()
    );

    authenticationManager.authenticate(token);

    User user = userRepository.findByUsername(authenticationRequest.getUsername()).get();

    String jwt = jwtUtil.generateToken(user, generateExtraClaims(user));

    return new AuthenticationResponse(jwt);
  }

  private Map<String, Object> generateExtraClaims(User user) {
    Map<String, Object> extraClaims = new HashMap<>();
    extraClaims.put("username", user.getUsername());
    return extraClaims;
  }

}