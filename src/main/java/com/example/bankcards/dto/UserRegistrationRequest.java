package com.example.bankcards.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequest {
    private String username;
    private String password;
}
