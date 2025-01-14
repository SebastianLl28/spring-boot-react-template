package com.app.backend.presentation.dto;

public class VerifyTokenDto {

  private String username;

  public VerifyTokenDto() {
  }

  public VerifyTokenDto(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

}
