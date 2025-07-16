package com.app.InventoryPro.dto;

import com.app.InventoryPro.model.Role;
import lombok.Data;

@Data
public class SignUpRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
}
