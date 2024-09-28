package com.parcial.backend.model.service;


import java.util.List;

import org.springframework.stereotype.Service;
import com.parcial.backend.model.DTO.UserDTO;
import com.parcial.backend.model.entity.Users;
import com.parcial.backend.model.repository.UsersRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;

    public UserDTO userDetails(HttpServletRequest request) {  
        try {
            String email = (String) request.getAttribute("email");
           
          
            Users user = usersRepository.findByEmail(email).orElseThrow(()->new Exception("User not found"));

            return UserDTO.builder()
            .data(user)
            .success(true)
            .error(false)
            .message("User created Successfully!!")
            .build();
        } catch (Exception e) {
           return UserDTO.builder()
            .data(null)
            .success(false)
            .error(true)
            .message(e.getMessage())
            .build();
        }     
    
    }

    public UserDTO userLogout() {  
        try {
           
            return UserDTO.builder()
            .data(null)
            .success(true)
            .error(false)
            .message("Logged out Successfully!!")
            .build();
        } catch (Exception e) {
           return UserDTO.builder()
            .data(null)
            .success(false)
            .error(true)
            .message(e.getMessage())
            .build();
        }     
    
    }

    public UserDTO allUsers() {  
        try {
        
           List<Users> allUsers = usersRepository.findAll();
           
            return UserDTO.builder()
            .data(allUsers)
            .success(true)
            .error(false)
            .message("All users retrieved Successfully!!")
            .build();
        } catch (Exception e) {
           return UserDTO.builder()
            .data(null)
            .success(false)
            .error(true)
            .message(e.getMessage())
            .build();
        }     
    
    }

    public UserDTO updateUser(String email, Users usersUpdates) {  
        try {

            System.out.println("para actualizar ::::: "+email);
            System.out.println("para actualizar ::::: "+usersUpdates);
           Users user = usersRepository.findByEmail(email).orElseThrow(()-> new Exception("User not found"));

           if(usersUpdates.getEmail()!=null) user.setEmail(usersUpdates.getEmail());
           if(usersUpdates.getName()!=null) user.setName(usersUpdates.getName());
           if(usersUpdates.getRole()!=null) user.setRole(usersUpdates.getRole());

           Users updateUser = usersRepository.save(user);
            return UserDTO.builder()
            .data(updateUser)
            .success(true)
            .error(false)
            .message("Logged out Successfully!!")
            .build();
        } catch (Exception e) {
           return UserDTO.builder()
            .data(null)
            .success(false)
            .error(true)
            .message(e.getMessage())
            .build();
        }     
    
    }

}
