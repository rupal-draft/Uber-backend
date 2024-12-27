package com.project.uber.Uber.services;

import com.project.uber.Uber.dto.*;

public interface AuthService {

    String[] login(LoginRequestDto loginRequestDto);
    UserDto signup(SignupDto signupDto);
    DriverDto onboardNewDriver(Long userId, OnboardDriverDto onboardDriverDto);

    LoginResponseDto refreshToken(String refreshToken);
}
