package com.ticketing.eventbooking.auth.service;

import com.ticketing.eventbooking.auth.dto.RegisterUserRequest;
import com.ticketing.eventbooking.auth.dto.RegisterUserResponse;
import com.ticketing.eventbooking.user.model.Role;
import com.ticketing.eventbooking.user.model.RoleType;
import com.ticketing.eventbooking.user.model.User;
import com.ticketing.eventbooking.user.repository.RoleRepository;
import com.ticketing.eventbooking.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public RegisterUserResponse register(RegisterUserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }

        Role userRole = roleRepository.findByName(RoleType.USER)
                .orElseGet(() -> roleRepository.save(new Role(RoleType.USER)));

        User user = new User(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getFullName()
        );

        if (request.getPhone() != null) {
            user.addRole(userRole);
        } else {
            user.addRole(userRole);
        }

        User savedUser = userRepository.save(user);

        return new RegisterUserResponse(savedUser.getId(), savedUser.getEmail());
    }
}
