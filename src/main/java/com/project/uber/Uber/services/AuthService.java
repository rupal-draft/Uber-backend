package com.project.uber.Uber.services;

import com.project.uber.Uber.dto.DriverDto;
import com.project.uber.Uber.dto.SignupDto;
import com.project.uber.Uber.dto.UserDto;

public interface AuthService {

    String login(String email , String password);
    UserDto signup(SignupDto signupDto);
    DriverDto onboardNewDriver(Long userId);
}
