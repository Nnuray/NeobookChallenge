package com.example.neobookchallenge.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String status;
}
