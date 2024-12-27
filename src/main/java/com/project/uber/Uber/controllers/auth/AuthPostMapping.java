package com.project.uber.Uber.controllers.auth;


import com.project.uber.Uber.dto.*;
import com.project.uber.Uber.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
public class AuthPostMapping {

    @Value("${deploy.env}")
    private String deployment;
    private final AuthService authService;

    public AuthPostMapping(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupDto signupDto){
        UserDto user = authService.signup(signupDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping(path = "/onboardDriver/{userId}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<DriverDto> onboardDriver(@PathVariable Long userId, @RequestBody OnboardDriverDto onboardDriverDto){
        DriverDto driver = authService.onboardNewDriver(userId, onboardDriverDto);
        return new ResponseEntity<>(driver, HttpStatus.CREATED);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse httpServletResponse){
        String[] tokens = authService.login(loginRequestDto);
        Cookie cookie = new Cookie("refreshToken", tokens[1]);
        cookie.setHttpOnly(true);
        cookie.setSecure(deployment.equals("production"));
        cookie.setPath("/auth/refresh");
        httpServletResponse.addCookie(cookie);

        return ResponseEntity.ok(new LoginResponseDto(tokens[0]));
    }

    @PostMapping(path = "/refresh")
    public ResponseEntity<LoginResponseDto> refreshToken(HttpServletRequest httpServletRequest){
        String refreshToken = Arrays
                .stream(httpServletRequest
                        .getCookies())
                        .filter(cookie -> cookie.getName().equals("refreshToken"))
                        .findFirst()
                        .map(cookie -> cookie.getValue())
                        .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("Refresh token not found"));
        return ResponseEntity.ok(authService.refreshToken(refreshToken));
    }
}
