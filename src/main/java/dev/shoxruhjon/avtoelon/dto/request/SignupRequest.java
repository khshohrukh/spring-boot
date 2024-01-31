package dev.shoxruhjon.avtoelon.dto.request;

import lombok.Data;

@Data
public class SignupRequest {
    private String name;
    private String email;
    private String password;
}
