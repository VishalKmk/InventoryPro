package com.app.InventoryPro.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginResponse {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
