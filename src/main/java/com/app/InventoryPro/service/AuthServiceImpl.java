package com.app.InventoryPro.service;

import com.app.InventoryPro.dto.LoginRequest;
import com.app.InventoryPro.dto.LoginResponse;
import com.app.InventoryPro.dto.SignUpRequest;
import com.app.InventoryPro.model.Role;
import com.app.InventoryPro.model.User;
import com.app.InventoryPro.repository.UserRepository;
import com.app.InventoryPro.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public User signUp(SignUpRequest signUpRequest) {
        User user = new User();
        user.setUsername(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(signUpRequest.getRole() != null ? signUpRequest.getRole() : Role.ROLE_USER);
        return userRepository.save(user);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        final User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with email: " + loginRequest.getEmail()));

        return LoginResponse.builder()
                .jwt(jwt)
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getUsername())
                .role(user.getRole().name())
                .build();
    }
}
