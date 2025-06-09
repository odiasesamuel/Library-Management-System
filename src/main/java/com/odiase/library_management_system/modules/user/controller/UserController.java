package com.odiase.library_management_system.modules.user.controller;

import com.odiase.library_management_system.common.response.ApiResponse;
import com.odiase.library_management_system.modules.user.dto.request.RegisterUserRequestDto;
import com.odiase.library_management_system.modules.user.dto.request.UpdateUserRequestDto;
import com.odiase.library_management_system.modules.user.dto.response.UserResponseDto;
import com.odiase.library_management_system.modules.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/users")
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public ResponseEntity<ApiResponse> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUser();
        return ResponseEntity.ok(new ApiResponse("success", users));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or #userId == authentication.principal.id")
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long userId) {
        UserResponseDto user = userService.getUserById(userId);
        return ResponseEntity.ok(new ApiResponse("Success", user));
    }

    @PostMapping()
    public ResponseEntity<ApiResponse> registerUser (@Valid @RequestBody RegisterUserRequestDto registerUserRequest) {
        UserResponseDto user = userService.registerUser(registerUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Successfully registered user", user));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or #userId == authentication.principal.id")
    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long userId, @Valid @RequestBody UpdateUserRequestDto updateUserRequest) {
        UserResponseDto user = userService.updateUser(userId, updateUserRequest);
        return ResponseEntity.ok(new ApiResponse("Successfully updated user details", user));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok(new ApiResponse("Successfully deleted user account", null));
    }
}
