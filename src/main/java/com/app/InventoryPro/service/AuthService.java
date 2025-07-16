package com.app.InventoryPro.service;

import com.app.InventoryPro.dto.LoginRequest;
import com.app.InventoryPro.dto.LoginResponse;
import com.app.InventoryPro.dto.SignUpRequest;
import com.app.InventoryPro.model.User;

public interface AuthService {
    User signUp(SignUpRequest signUpRequest);
    LoginResponse login(LoginRequest loginRequest);
}
