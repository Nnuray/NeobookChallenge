package com.example.neobookchallenge.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RegisterRequest {
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    public String phone_number;

}
