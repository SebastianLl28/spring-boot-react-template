package com.app.backend.presentation.controller;

import com.app.backend.persistence.entity.User;
import com.app.backend.persistence.repository.UserRepository;
import com.app.backend.presentation.dto.UserResponse;
import com.app.backend.util.AppConstant;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppConstant.SECURE_BASE_ENDPOINT + "/user")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public List<UserResponse> getAll() {
    return userRepository.findAll().stream().map(UserResponse::new).toList();
  }

  @GetMapping("/{id}")
  public UserResponse getById(@PathVariable Long id) {
    return userRepository.findById(id)
        .map(UserResponse::new)
        .orElseThrow(() -> new RuntimeException("User not found"));
  }

  @PostMapping
  public String create(@RequestBody User user) {
    user.setId(null);
    String password = new BCryptPasswordEncoder().encode(user.getPassword());
    user.setPassword(password);
    userRepository.save(user);
    return "User created";
  }

  @PutMapping("/{id}")
  public String update(@RequestBody User user, @PathVariable Long id) {
    Optional<User> existingUser = userRepository.findById(id);
    if (existingUser.isEmpty()) {
      throw new RuntimeException("User not found");
    }
    user.setId(id);
    userRepository.save(user);
    return "User updated";
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isEmpty()) {
      throw new RuntimeException("User not found");
    }
    userRepository.deleteById(id);
  }
}
