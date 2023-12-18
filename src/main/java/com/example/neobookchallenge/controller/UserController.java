package com.example.neobookchallenge.controller;

import com.example.neobookchallenge.dto.user.RequestUserDto;
import com.example.neobookchallenge.dto.user.ResponseUserDto;
import com.example.neobookchallenge.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(
        name = "Контроллер для пользователя",
        description = "В этом контроллере есть возможности получить, создать, обновить, удалить пользователя"
)
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }


    @GetMapping("/{id}")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<ResponseUserDto> getUserById(@PathVariable int id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/createUser")
    @SecurityRequirement(name = "JWT")
    public void createUser(@RequestBody RequestUserDto userDto) {
        userService.createUser(userDto);
    }

    @PutMapping("/update/{id}")
    @SecurityRequirement(name = "JWT")
    public void updateUser(@PathVariable int id, @RequestBody ResponseUserDto responseUserDto, @PathVariable String updateById) {
            userService.updateUser(id, responseUserDto);
        }

    @DeleteMapping("/delete/{id}")
    @SecurityRequirement(name = "JWT")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @GetMapping("/getAllUsers")
    @SecurityRequirement(name = "JWT")
    public List<ResponseUserDto> getAllUsers() {
        return userService.getAllUsers();
    }



}

