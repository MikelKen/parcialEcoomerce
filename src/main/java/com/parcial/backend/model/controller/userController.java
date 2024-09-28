package com.parcial.backend.model.controller;



import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.parcial.backend.model.DTO.UserDTO;
import com.parcial.backend.model.entity.Users;
import com.parcial.backend.model.service.UserService;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class userController {

    private final UserService userService;
    
    @GetMapping("/user-details")
    public ResponseEntity<UserDTO> userDetails(HttpServletRequest request){        
      // String email = (String) request.getAttribute("email");
        return ResponseEntity.ok(userService.userDetails(request));
    }
    
    @GetMapping("/userLogout")
    public ResponseEntity<UserDTO> userLogout(){        
      
        return ResponseEntity.ok(userService.userLogout());
    }

    @GetMapping("/all-user")
    public ResponseEntity<UserDTO> allUsers(){        
      
        return ResponseEntity.ok(userService.allUsers());
    }

    @PostMapping("/update-user")
    public ResponseEntity<UserDTO> updateUser(@RequestBody Users usersUpdate, HttpServletRequest request ) {
        String email = (String) request.getAttribute("email");
        return ResponseEntity.ok(userService.updateUser(email,usersUpdate));
    }

    

   
}
