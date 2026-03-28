package com.retail.retailmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.retailmanagement.dto.request.AuthRequest;
import com.retail.retailmanagement.dto.response.AuthResponse;
import com.retail.retailmanagement.helper.ResponseUtil;
import com.retail.retailmanagement.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AuthRequest request) {

        AuthResponse result = authService.register(request);

        ResponseEntity<?> response = ResponseUtil.success(result, "Register success");

        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest request) {

        AuthResponse authResponse = authService.login(request);

        ResponseEntity<?> response = ResponseUtil.success(authResponse, "Login success");

        return response;
    }
}
