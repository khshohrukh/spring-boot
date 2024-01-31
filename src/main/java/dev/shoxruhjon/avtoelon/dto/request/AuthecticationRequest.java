package dev.shoxruhjon.avtoelon.dto.request;

import lombok.Data;

@Data
public class AuthecticationRequest {
    private String email;
    private String password;
}
