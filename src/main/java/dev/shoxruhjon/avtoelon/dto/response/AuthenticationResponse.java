package dev.shoxruhjon.avtoelon.dto.response;

import dev.shoxruhjon.avtoelon.entity.enums.Role;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;
    private Role role;
    private Long userId;
}
