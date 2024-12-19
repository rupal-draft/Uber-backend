package com.project.uber.Uber.services.implementations;

import com.project.uber.Uber.dto.DriverDto;
import com.project.uber.Uber.dto.SignupDto;
import com.project.uber.Uber.dto.UserDto;
import com.project.uber.Uber.services.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public UserDto signup(SignupDto signupDto) {
        return null;
    }

    @Override
    public DriverDto onboardNewDriver(Long userId) {
        return null;
    }
}
