package com.app.InventoryPro.service;

import com.app.InventoryPro.dto.LoginRequest;
import com.app.InventoryPro.dto.SignUpRequest;
import com.app.InventoryPro.model.User;

public interface AuthService {
    User signUp(SignUpRequest signUpRequest);
    User login(LoginRequest loginRequest);
}
