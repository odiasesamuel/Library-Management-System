package com.odiase.library_management_system.modules.user.service;

import com.odiase.library_management_system.modules.user.dto.request.RegisterUserRequestDto;
import com.odiase.library_management_system.modules.user.dto.request.UpdateUserRequestDto;
import com.odiase.library_management_system.modules.user.dto.response.UserResponseDto;
import com.odiase.library_management_system.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public UserResponseDto getUserById(Long userId) {
        return null;
    }

    @Override
    public List<UserResponseDto> getAllUser() {
        return List.of();
    }

    @Override
    public UserResponseDto registerUser(RegisterUserRequestDto registerUserRequest) {
        return null;
    }

    @Override
    public UserResponseDto updateUser(UpdateUserRequestDto updateUserRequest) {
        return null;
    }

    @Override
    public void deleteUserById(Long userId) {

    }
}
