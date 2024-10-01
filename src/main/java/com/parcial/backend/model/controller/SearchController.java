package com.parcial.backend.model.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parcial.backend.model.DTO.FilterDTO;
import com.parcial.backend.model.DTO.UserDTO;
import com.parcial.backend.model.service.SearchService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class SearchController {
    
    private final SearchService searchService;

    @PostMapping("/filter-product")
    public ResponseEntity<FilterDTO> filterProduct(@RequestBody Map<String, List<String>>  requestBody){
        List<String> categoryList = requestBody.get("category");
        return ResponseEntity.ok(searchService.filterProduct(categoryList));
    }

    @GetMapping("/search")
    public ResponseEntity<UserDTO> searchProduct(@RequestParam(name = "q") String query){
        return ResponseEntity.ok(searchService.search(query));
    }

}
