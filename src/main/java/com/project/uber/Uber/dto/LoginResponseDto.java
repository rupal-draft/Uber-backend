package com.project.uber.Uber.dto;

public class LoginResponseDto {

    private String accessToken;

    public LoginResponseDto() {
    }

    public LoginResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
