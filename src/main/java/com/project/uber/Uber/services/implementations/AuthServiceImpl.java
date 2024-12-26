package com.project.uber.Uber.services.implementations;

import com.project.uber.Uber.dto.DriverDto;
import com.project.uber.Uber.dto.OnboardDriverDto;
import com.project.uber.Uber.dto.SignupDto;
import com.project.uber.Uber.dto.UserDto;
import com.project.uber.Uber.entities.Driver;
import com.project.uber.Uber.entities.User;
import com.project.uber.Uber.entities.enums.Roles;
import com.project.uber.Uber.exceptions.ResourceNotFoundException;
import com.project.uber.Uber.exceptions.RuntimeConflictException;
import com.project.uber.Uber.repositories.UserRepository;
import com.project.uber.Uber.services.AuthService;
import com.project.uber.Uber.services.DriverService;
import com.project.uber.Uber.services.RiderService;
import com.project.uber.Uber.services.WalletService;
import org.modelmapper.ModelMapper;
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

    public AuthServiceImpl(ModelMapper modelMapper, UserRepository userRepository, DriverService driverService, RiderService riderService, WalletService walletService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.riderService = riderService;
        this.walletService = walletService;
        this.driverService = driverService;
    }

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    @Transactional
    public UserDto signup(SignupDto signupDto) {
        Optional<User> user = userRepository.findByEmail(signupDto.getEmail());
        if(user.isPresent()){
            throw new RuntimeConflictException("User already exists with that email!");
        }
        User mappedUser = modelMapper.map(signupDto,User.class);
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

        Driver createDriver = new Driver
                .DriverBuilder()
                .available(true)
                .rating(0.0)
                .user(user)
                .vehicleId(onboardDriverDto.getVehicleId())
                .build();

        user.getRoles().add(Roles.DRIVER);
        userRepository.save(user);
        Driver savedDriver = driverService.createNewDriver(createDriver);

        return modelMapper.map(savedDriver, DriverDto.class);
    }
}
