package com.app.InventoryPro.controller;

import com.app.InventoryPro.dto.SignUpRequest;
import com.app.InventoryPro.model.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void signUpEndpoint_ReturnsOk() throws Exception {
        SignUpRequest request = new SignUpRequest();
        request.setUsername("testuser");
        request.setPassword("testpass");
        request.setEmail("testuser@example.com");
        request.setRole(Role.ROLE_USER);
        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void loginEndpoint_ReturnsOk() throws Exception {
        String json = "{\"username\":\"testuser\",\"password\":\"testpass\"}";
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

}
