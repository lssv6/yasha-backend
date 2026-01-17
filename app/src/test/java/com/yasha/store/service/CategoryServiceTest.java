package com.yasha.store.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.given;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.yasha.store.dto.CategoryResponseDTO;
import com.yasha.store.dto.CategorySaveDTO;
import com.yasha.store.entity.Category;
import com.yasha.store.repository.CategoryRepository;

@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService service;

    @MockitoBean
    private CategoryRepository repository;
    
    private static Category entity;

    @BeforeAll static void setUp(){
        entity = new Category();
        entity.setName("Perfume");
        entity.setPath("/perfume");
    }

    @Test void shouldSaveCategory(){
        // Arrange
        given(repository.save(any())).willReturn(entity);

        // Act
        CategorySaveDTO categoryDTOSave = new CategorySaveDTO("Perfume", "/perfume");
        CategoryResponseDTO  categoryDTO = service.save(categoryDTOSave);

        // Assert
        assertEquals("Perfume", categoryDTO.name());
    }

    @Test void shouldFindCategory(){
        // Arrange
        given(repository.findById(555L)).willReturn(Optional.of(entity));
        // Act
        CategoryResponseDTO dto = service.findById(555L).orElseThrow();
        // Assert
        assertNotNull(dto);
        assertEquals("Perfume", dto.name());
    }

    @Test void shouldUpdateCategory(){
        // Arrange
        given(repository.save(any())).willAnswer(ans -> ans.getArgument(0));

        // Act
        CategorySaveDTO categoryDTOSave = new CategorySaveDTO("Perfumaria", "/perfumaria");
        CategoryResponseDTO dto = service.update(69L, categoryDTOSave);

        assertEquals("Perfumaria", dto.name());
        assertEquals("/perfumaria", dto.path());
    }
}

