package com.app.InventoryPro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.app.InventoryPro.model.Role;
import com.app.InventoryPro.model.User;
import com.app.InventoryPro.model.UserSettings;
import com.app.InventoryPro.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@inventorypro.com");
            admin.setPassword(passwordEncoder.encode("password"));
            admin.setRole(Role.ROLE_ADMIN);

            UserSettings settings = new UserSettings();
            settings.setUser(admin);
            admin.setUserSettings(settings);

            userRepository.save(admin);
            System.out.println(">>>> Created default admin user with password: password");
        }
    }
}
