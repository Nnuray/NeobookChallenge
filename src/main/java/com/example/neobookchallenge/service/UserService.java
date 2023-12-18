package com.example.neobookchallenge.service;

import com.example.neobookchallenge.dto.user.RequestUserDto;
import com.example.neobookchallenge.dto.user.ResponseUserDto;
import com.example.neobookchallenge.entity.User;
import com.example.neobookchallenge.enums.Role;
import com.example.neobookchallenge.enums.Status;
import com.example.neobookchallenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.neobookchallenge.dto.user.ResponseUserDto.toResponseUserDto;
import static com.example.neobookchallenge.dto.user.ResponseUserDto.toResponseUserDtos;


@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseUserDto getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return toResponseUserDto(user);
    }
    public String createUser(RequestUserDto userDto) {
        User user = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .phoneNumber(userDto.getPhoneNumber())
                .status(Status.ACTIVE)
                .email(userDto.getEmail())
                .role(Role.USER)
                .build();
        userRepository.save(user);
        return "Пользователь создан";
    }

    public ResponseUserDto updateUser(int id, ResponseUserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь с таким айди не найден"));

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setEmail(userDto.getEmail());

        userRepository.save(user);
        return toResponseUserDto(user);
    }

    public void deleteUser(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setStatus(Status.DELETED);
        userRepository.save(user);
    }

    public List<ResponseUserDto> getAllUsers() {
        return toResponseUserDtos(userRepository.findAll());
    }


}