package com.project.uber.Uber.services.implementations;

import com.project.uber.Uber.entities.User;
import com.project.uber.Uber.repositories.UserRepository;
import com.project.uber.Uber.services.UserService;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User getUserFromId(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(()-> new AuthenticationCredentialsNotFoundException("User not found!"));
    }
}
