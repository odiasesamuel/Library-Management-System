package com.odiase.library_management_system.modules.user.mapper;

import com.odiase.library_management_system.modules.user.dto.request.RegisterUserRequestDto;
import com.odiase.library_management_system.modules.user.dto.response.UserResponseDto;
import com.odiase.library_management_system.modules.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {


    UserResponseDto toResponseDto(User user);

    List<UserResponseDto> toResponseDtoList(List<User> users);

//    User toEntity(RegisterUserRequestDto dto);

}
