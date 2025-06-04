package com.odiase.library_management_system.modules.user.service;

import com.odiase.library_management_system.modules.user.dto.request.RegisterUserRequestDto;
import com.odiase.library_management_system.modules.user.dto.request.UpdateUserRequestDto;
import com.odiase.library_management_system.modules.user.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUser();
    UserResponseDto getUserById(Long userId);
    UserResponseDto registerUser(RegisterUserRequestDto registerUserRequest);
    UserResponseDto updateUser(Long userId, UpdateUserRequestDto updateUserRequest);
    void deleteUserById(Long userId);
}
