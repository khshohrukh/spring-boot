package dev.shoxruhjon.avtoelon.dto.response;

import dev.shoxruhjon.avtoelon.entity.enums.Role;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private Role role;
}
