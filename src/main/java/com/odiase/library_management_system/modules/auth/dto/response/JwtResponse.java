package com.odiase.library_management_system.modules.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    private Long userId;
    private String token;
}
