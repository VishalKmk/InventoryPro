package com.app.InventoryPro.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private String jwt;
    private Long id;
    private String email;
    private String name;
    private String role;
}

