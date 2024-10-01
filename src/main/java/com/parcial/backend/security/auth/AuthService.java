package com.parcial.backend.security.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.parcial.backend.model.entity.Users;
import com.parcial.backend.model.repository.UsersRepository;
import com.parcial.backend.security.jwt.JwtService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

    private final UsersRepository usersRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {       

        try {
            authenticationManager.authenticate((new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())));
        Users user = usersRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.getToken(user);
        System.out.println("tooooken: "+token);
        return AuthResponse.builder()
            .data(token)
            .success(true)
            .error(false)
            .message("Login successfully")
            .build();
        } catch (Exception e) {
            return AuthResponse.builder()
            .data(null)
            .success(false)
            .error(true)
            .message(e.getMessage())
            .build();
        }
    }

    public AuthResponse register(RegisterRequest request) {
        try {
           
        
            if(request.getEmail()==null || request.getEmail().isEmpty()){
                throw new Exception("Please provide email");
            }
            if(request.getPassword() == null || request.getPassword().isEmpty()){
                throw new Exception("Please provide password"); 
            }
            if(request.getName()==null || request.getName().isEmpty()){
                throw new Exception("Please provide name");
            }
        Users user = Users.builder()
            .name(request.getName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .profilePic(request.getProfilePic())
            .role(Role.USER)
            .build();
        
        usersRepository.save(user);    
        
        return AuthResponse.builder()
            .data(user)
            .success(true)
            .error(false)
            .message("User created Successfully!!")
            .build();
        //return null;
        } catch (Exception e) {
           return AuthResponse.builder()
            .data(null)
            .success(false)
            .error(true)
            .message(e.getMessage())
            .build();
        }
        
    }

}
