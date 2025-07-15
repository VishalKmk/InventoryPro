package com.app.InventoryPro.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.http.ResponseEntity;
import com.app.InventoryPro.service.AuthService;
import com.app.InventoryPro.dto.SignUpRequest;
import com.app.InventoryPro.dto.LoginRequest;
import com.app.InventoryPro.model.Role;
import com.app.InventoryPro.model.User;

public class AuthControllerTest {

    private AuthController MakeAuthControllerWithMockAuthService(AuthService mockAuthService) {
        AuthController controller = new AuthController();
        try {
            java.lang.reflect.Field field = AuthController.class.getDeclaredField("authService");
            field.setAccessible(true);
            field.set(controller, mockAuthService);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return controller;
    }

    private SignUpRequest MakeSignUpRequest() {
        SignUpRequest req = new SignUpRequest();
        req.setUsername("testuser");
        req.setPassword("testpass");
        req.setEmail("testuser@example.com");
        req.setRole(Role.ROLE_USER);
        return req;
    }

    private LoginRequest MakeLoginRequest() {
        LoginRequest req = new LoginRequest();
        // Set fields as needed for the test
        req.setUsername("testuser");
        req.setPassword("testpass");
        return req;
    }

    private User MakeUser() {
        User user = new User();
        // Set fields as needed for the test
        user.setId(1L);
        user.setUsername("testuser");
        return user;
    }

    @Test
    public void signUp_WithValidRequest_ReturnsUserInResponseEntity() {
        AuthService mockAuthService = mock(AuthService.class);
        AuthController controller = MakeAuthControllerWithMockAuthService(mockAuthService);
        SignUpRequest signUpRequest = MakeSignUpRequest();
        User expectedUser = MakeUser();
        when(mockAuthService.signUp(signUpRequest)).thenReturn(expectedUser);

        // Act: invoking the entry point
        ResponseEntity<User> response = controller.signUp(signUpRequest);

        // Assert: return-value check
        assertEquals(expectedUser, response.getBody());
    }

    @Test
    public void login_WithValidRequest_ReturnsUserInResponseEntity() {
        AuthService mockAuthService = mock(AuthService.class);
        AuthController controller = MakeAuthControllerWithMockAuthService(mockAuthService);
        LoginRequest loginRequest = MakeLoginRequest();
        User expectedUser = MakeUser();
        when(mockAuthService.login(loginRequest)).thenReturn(expectedUser);

        // Act: invoking the entry point
        ResponseEntity<User> response = controller.login(loginRequest);

        // Assert: return-value check
        assertEquals(expectedUser, response.getBody());
    }

    @Test
    public void signUp_InvokesAuthServiceSignUp_Once() {
        AuthService mockAuthService = mock(AuthService.class);
        AuthController controller = MakeAuthControllerWithMockAuthService(mockAuthService);
        SignUpRequest signUpRequest = MakeSignUpRequest();
        User user = MakeUser();
        when(mockAuthService.signUp(signUpRequest)).thenReturn(user);

        // Act: invoking the entry point
        controller.signUp(signUpRequest);

        // Assert: third-party interaction check
        verify(mockAuthService, times(1)).signUp(signUpRequest);
    }

    @Test
    public void login_InvokesAuthServiceLogin_Once() {
        AuthService mockAuthService = mock(AuthService.class);
        AuthController controller = MakeAuthControllerWithMockAuthService(mockAuthService);
        LoginRequest loginRequest = MakeLoginRequest();
        User user = MakeUser();
        when(mockAuthService.login(loginRequest)).thenReturn(user);

        // Act
        controller.login(loginRequest);

        // Assert
        verify(mockAuthService, times(1)).login(loginRequest);
    }

    @Test
    public void signUp_WhenAuthServiceReturnsNull_ResponseEntityBodyIsNull() {
        AuthService mockAuthService = mock(AuthService.class);
        AuthController controller = MakeAuthControllerWithMockAuthService(mockAuthService);
        SignUpRequest signUpRequest = MakeSignUpRequest();
        when(mockAuthService.signUp(signUpRequest)).thenReturn(null);

        ResponseEntity<User> response = controller.signUp(signUpRequest);

        assertNull(response.getBody());
    }

    @Test
    public void login_WhenAuthServiceReturnsNull_ResponseEntityBodyIsNull() {
        AuthService mockAuthService = mock(AuthService.class);
        AuthController controller = MakeAuthControllerWithMockAuthService(mockAuthService);
        LoginRequest loginRequest = MakeLoginRequest();
        when(mockAuthService.login(loginRequest)).thenReturn(null);

        ResponseEntity<User> response = controller.login(loginRequest);

        assertNull(response.getBody());
    }
}