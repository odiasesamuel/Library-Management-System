package com.odiase.library_management_system.modules.user.service;

import com.odiase.library_management_system.modules.user.dto.request.RegisterUserRequestDto;
import com.odiase.library_management_system.modules.user.dto.request.UpdateUserRequestDto;
import com.odiase.library_management_system.modules.user.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto getUserById(Long userId);
    List<UserResponseDto> getAllUser();
    UserResponseDto registerUser(RegisterUserRequestDto registerUserRequest);
    UserResponseDto updateUser(UpdateUserRequestDto updateUserRequest);
    void deleteUserById(Long userId);
}
