package com.example.neobookchallenge.dto.user;

import com.example.neobookchallenge.entity.User;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseUserDto {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String status;
    private String role;

    public static ResponseUserDto toResponseUserDto(User user) {//конвертировать одного юсера
        return ResponseUserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .role(user.getRole().name())
                .status(user.getStatus().name())
                .build();
    }

    public static List<ResponseUserDto> toResponseUserDtos(List<User> users) {
        return users.stream().map(ResponseUserDto::toResponseUserDto).toList();
    }
}
