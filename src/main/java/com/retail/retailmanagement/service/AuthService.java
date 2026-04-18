package com.retail.retailmanagement.service;

import com.retail.retailmanagement.dto.request.AuthRequest;
import com.retail.retailmanagement.dto.request.RegisterRequest;
import com.retail.retailmanagement.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse login(AuthRequest request);

    AuthResponse register(RegisterRequest request);
}
