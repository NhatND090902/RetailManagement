package com.retail.retailmanagement.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.retail.retailmanagement.customException.UserAlreadyExistsException;
import com.retail.retailmanagement.dto.request.AuthRequest;
import com.retail.retailmanagement.dto.request.RegisterRequest;
import com.retail.retailmanagement.dto.response.AuthResponse;
import com.retail.retailmanagement.entity.User;
import com.retail.retailmanagement.repository.UserRepository;
import com.retail.retailmanagement.security.jwt.JwtService;
import com.retail.retailmanagement.service.AuthService;

import jakarta.transaction.Transactional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));

        String token = jwtService.generateToken(request.getUsername());
        return new AuthResponse(token);
    }

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {

        String email = request.getEmail().toLowerCase();
        String username = request.getUsername().toLowerCase();

        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("Email", email);
        }

        if (userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistsException("username", username);
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("ROLE_USER");
        user.setStatus("PENDING");
        userRepository.save(user);

        String token = jwtService.generateToken(user.getUsername());
        return new AuthResponse(token);
        // TODO:
        // - generate OTP
        // - save verification code
        // - send email
    }
}
