package com.yasha.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yasha.store.dto.CategoryResponseDTO;
import com.yasha.store.dto.CategorySaveDTO;
import com.yasha.store.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategory(@PathVariable Long id){
        return ResponseEntity.of(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> postCategory(@RequestBody CategorySaveDTO saveDTO){
        CategoryResponseDTO response = service.save(saveDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> putMapping(
        @RequestBody CategorySaveDTO saveDTO,
        @PathVariable Long id
    ){
        CategoryResponseDTO response = service.update(id, saveDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

