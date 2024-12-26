package com.project.uber.Uber.services.implementations;

import com.project.uber.Uber.dto.DriverDto;
import com.project.uber.Uber.dto.SignupDto;
import com.project.uber.Uber.dto.UserDto;
import com.project.uber.Uber.entities.User;
import com.project.uber.Uber.entities.enums.Roles;
import com.project.uber.Uber.exceptions.RuntimeConflictException;
import com.project.uber.Uber.repositories.UserRepository;
import com.project.uber.Uber.services.AuthService;
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

    public AuthServiceImpl(ModelMapper modelMapper, UserRepository userRepository, RiderService riderService, WalletService walletService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.riderService = riderService;
        this.walletService = walletService;
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
    public DriverDto onboardNewDriver(Long userId) {
        return null;
    }
}
