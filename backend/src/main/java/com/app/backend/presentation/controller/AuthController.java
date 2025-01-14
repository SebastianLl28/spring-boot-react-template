package com.app.backend.presentation.controller;

import com.app.backend.persistence.entity.User;
import com.app.backend.persistence.repository.UserRepository;
import com.app.backend.presentation.dto.AuthenticationRequest;
import com.app.backend.presentation.dto.AuthenticationResponse;
import com.app.backend.presentation.dto.UserResponse;
import com.app.backend.presentation.dto.VerifyTokenDto;
import com.app.backend.service.AuthenticationService;
import com.app.backend.service.TestDataPopulatingService;
import com.app.backend.service.TestService;
import com.app.backend.util.AppConstant;
import java.security.Principal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  @Autowired
  private AuthenticationService authenticationService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TestService testService;

  @Autowired
  private TestDataPopulatingService testDataPopulatingService;

  @PostMapping(AppConstant.PUBLIC_BASE_ENDPOINT + "/auth/login")
  public AuthenticationResponse login(@RequestBody AuthenticationRequest authRequest) {
    return authenticationService.login(authRequest);
  }

  @GetMapping(AppConstant.SECURE_BASE_ENDPOINT + "/auth/verify")
  public VerifyTokenDto verify(Principal principal) {
    Optional<User> user = userRepository.findByUsername(principal.getName());
    if (user.isEmpty()) {
      throw new RuntimeException("User not found");
    }

    return new VerifyTokenDto();
  }

  @GetMapping(AppConstant.SECURE_BASE_ENDPOINT + "/auth/verify-token")
  public UserResponse verifyToken(Principal principal) {
    Optional<User> user = userRepository.findByUsername(principal.getName());
    if (user.isEmpty()) {
      throw new RuntimeException("User not found");
    }
    return new UserResponse(user.get());
  }

  @PostMapping(AppConstant.PUBLIC_BASE_ENDPOINT + "/auth/register")
  public AuthenticationResponse register(@RequestBody User user) {

    Optional<User> findUser = userRepository.findByUsername(user.getUsername());

    if (findUser.isPresent()) {
      throw new RuntimeException("User already exists");
    }

    String passwordEncoded = new BCryptPasswordEncoder().encode(user.getPassword());
    String oldPassword = user.getPassword();

    user.setId(null);
    user.setPassword(passwordEncoded);

    user = userRepository.save(user);

    return authenticationService.login(new AuthenticationRequest(user.getUsername(), oldPassword));
  }


  @Profile({"dev", "test"})
  @DeleteMapping(AppConstant.PUBLIC_BASE_ENDPOINT + "/auth/actions/clean-database")
  public void cleanDatabase() {
    testService.cleanDB();
    testDataPopulatingService.populateTestData();
  }
}
