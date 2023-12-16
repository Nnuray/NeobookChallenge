package com.example.neobookchallenge.service;


import com.example.neobookchallenge.dto.auth.AuthenticationRequest;
import com.example.neobookchallenge.dto.auth.AuthenticationResponse;
import com.example.neobookchallenge.dto.auth.RegisterRequest;
import com.example.neobookchallenge.entity.User;
import com.example.neobookchallenge.enums.Role;
import com.example.neobookchallenge.enums.Status;
import com.example.neobookchallenge.jwt.JwtService;
import com.example.neobookchallenge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        if(repository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("Already exists");
        }

        var user = User.builder()
                .firstName(request.getFirst_name())
                .lastName(request.getLast_name())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
               // .role(Role.USER)
                .status(Status.ACTIVE)
                .build();
      //  repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

//    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//        var user = repository.findByEmail(request.getEmail())
//                .orElseThrow();
//     //   var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }
}
