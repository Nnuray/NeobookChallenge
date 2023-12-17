package com.example.neobookchallenge.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(
        name = "Контроллер для авторизации/регистрации",
        description = "В этом контроллере есть возможности авторизации и регистрации"
) // Название и Описание контроллера в сваггере
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    @Operation(
            summary = "Регистрация нового аккаунта"
    ) // Описание метода в сваггере
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    @Operation(
            summary = "Авторизация"
    )
    public ResponseEntity<AuthenticationResponse> register (
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));

    }
}
