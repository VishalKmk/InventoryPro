package com.app.InventoryPro.security;

import com.app.InventoryPro.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("userSecurity")
public class UserSecurity {
    public boolean hasUserId(Authentication authentication, Long userId) {
        User user = (User) authentication.getPrincipal();
        return user.getId().equals(userId);
    }
}
