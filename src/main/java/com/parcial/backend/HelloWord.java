package com.parcial.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")

public class HelloWord {
    @GetMapping
    public String holaMundo() {
        return "Hello Word";
    }

}
