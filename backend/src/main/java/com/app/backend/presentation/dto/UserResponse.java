package com.app.backend.presentation.dto;

import com.app.backend.persistence.entity.User;

public class UserResponse {

  private Long id;
  private String username;

  public UserResponse() {
  }

  public UserResponse(User user) {
    this.id = user.getId();
    this.username = user.getUsername();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
