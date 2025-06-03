package com.odiase.library_management_system.modules.user.dto.response;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
}
