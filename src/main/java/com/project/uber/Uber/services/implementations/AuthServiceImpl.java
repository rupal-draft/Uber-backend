package com.project.uber.Uber.services.implementations;

import com.project.uber.Uber.dto.*;
import com.project.uber.Uber.entities.Driver;
import com.project.uber.Uber.entities.User;
import com.project.uber.Uber.entities.enums.Roles;
import com.project.uber.Uber.exceptions.ResourceNotFoundException;
import com.project.uber.Uber.exceptions.RuntimeConflictException;
import com.project.uber.Uber.repositories.UserRepository;
import com.project.uber.Uber.security.JwtService;
import com.project.uber.Uber.services.*;
import com.project.uber.Uber.utils.GeometryUtil;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;
    private final WalletService walletService;
    private final DriverService driverService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthServiceImpl(ModelMapper modelMapper, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserService userService, JwtService jwtService, UserRepository userRepository, DriverService driverService, RiderService riderService, WalletService walletService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.riderService = riderService;
        this.walletService = walletService;
        this.driverService = driverService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    public String[] login(LoginRequestDto loginRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(),loginRequestDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        String accessToken = jwtService.getAccessJwtToken(user);
        String refreshToken = jwtService.getRefreshJwtToken(user);

        return new String[]{accessToken, refreshToken};
    }

    @Override
    @Transactional
    public UserDto signup(SignupDto signupDto) {
        Optional<User> user = userRepository.findByEmail(signupDto.getEmail());
        if(user.isPresent()){
            throw new RuntimeConflictException("User already exists with that email!");
        }
        User mappedUser = modelMapper.map(signupDto,User.class);
        mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
        mappedUser.setRoles(Set.of(Roles.RIDER));
        User savedUser = userRepository.save(mappedUser);
        riderService.createNewRider(savedUser);
        walletService.createNewWallet(savedUser);
        return modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long userId, OnboardDriverDto onboardDriverDto) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("No user was found with ID: "+userId));

        if(user.getRoles().contains(Roles.DRIVER)){
            throw new RuntimeConflictException("User is already a driver!");
        }

        Point currentLocation = GeometryUtil.createPoint(onboardDriverDto.getCurrentLocation());
        Driver createDriver = new Driver
                .DriverBuilder()
                .available(true)
                .rating(0.0)
                .user(user)
                .vehicleId(onboardDriverDto.getVehicleId())
                .currentLocation(currentLocation)
                .build();

        user.getRoles().add(Roles.DRIVER);
        userRepository.save(user);
        Driver savedDriver = driverService.createNewDriver(createDriver);

        return modelMapper.map(savedDriver, DriverDto.class);
    }

    @Override
    public LoginResponseDto refreshToken(String refreshToken) {

        Long userId = jwtService.getUserId(refreshToken);
        User user = userService.getUserFromId(userId);
        if(user == null) throw new AuthenticationCredentialsNotFoundException("User not found");

        String accessToken = jwtService.getAccessJwtToken(user);
        return new LoginResponseDto(accessToken);
    }
}
