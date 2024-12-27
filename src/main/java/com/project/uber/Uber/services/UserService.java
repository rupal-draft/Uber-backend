package com.project.uber.Uber.services;

import com.project.uber.Uber.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getUserFromId(Long userId);
}
