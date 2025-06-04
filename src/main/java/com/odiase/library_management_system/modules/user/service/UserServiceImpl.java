package com.odiase.library_management_system.modules.user.service;

import com.odiase.library_management_system.common.exception.AlreadyExistsException;
import com.odiase.library_management_system.common.exception.ResourceNotFoundException;
import com.odiase.library_management_system.modules.user.dto.request.RegisterUserRequestDto;
import com.odiase.library_management_system.modules.user.dto.request.UpdateUserRequestDto;
import com.odiase.library_management_system.modules.user.dto.response.UserResponseDto;
import com.odiase.library_management_system.modules.user.entity.User;
import com.odiase.library_management_system.modules.user.mapper.UserMapper;
import com.odiase.library_management_system.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponseDto> getAllUser() {
        List<User> users = userRepository.findAll();
        return userMapper.toResponseDtoList(users);
    }

    @Override
    public UserResponseDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
        return userMapper.toResponseDto(user);
    }

    @Override
    public UserResponseDto registerUser(RegisterUserRequestDto registerUserRequest) {
        if (userRepository.existsByEmail(registerUserRequest.getEmail())) throw new AlreadyExistsException("Oops! " + registerUserRequest.getEmail() + " already exists!");
        User user = userMapper.toEntity(registerUserRequest);
        /* Encode Password before saving to db */
       User savedUser = userRepository.save(user);
        return userMapper.toResponseDto(savedUser);
    }

    @Override
    public UserResponseDto updateUser(Long userId, UpdateUserRequestDto updateUserRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
        userMapper.updateUserFromDto(updateUserRequest, user);

        User savedUser = userRepository.save(user);
        return userMapper.toResponseDto(savedUser);
    }

    @Override
    public void deleteUserById(Long userId) {
        if(!userRepository.existsById(userId)) throw new ResourceNotFoundException("User with ID " + userId + " not found");

        userRepository.deleteById(userId);
    }
}
