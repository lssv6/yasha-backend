package com.yasha.store.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static org.mockito.BDDMockito.given;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.web.context.WebApplicationContext;

import com.yasha.store.dto.CategoryResponseDTO;
import com.yasha.store.dto.CategorySaveDTO;
import com.yasha.store.service.CategoryService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

@SpringBootTest
public class CategoryControllerTest {
    @MockitoBean
    private CategoryService service;
    @InjectMocks
    private CategoryController controller;

    private RestTestClient client;
    
    @BeforeEach void setUp(WebApplicationContext applicationContext){
        client =  RestTestClient.bindToApplicationContext(applicationContext).build();
    }

    @Test void testGet(){
        // Arrange
        given(service.findById(69L))
            .willReturn(Optional.of(new CategoryResponseDTO(69L, "Perfume", "/perfume")));
            
        // Act
        client.get().uri("/category/69")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody(CategoryResponseDTO.class)
            .consumeWith(response ->{
                // Assert
                var res = response.getResponseBody();
                assertEquals("Perfume", res.name());
                assertEquals("/perfume", res.path());
            });
    }

    @Test void testPost(){
        // Arrange
        CategorySaveDTO categorySaveDTO = new CategorySaveDTO("Perfume", "/perfume");
        given(service.save(categorySaveDTO))
            .willReturn(new CategoryResponseDTO(69L, "Perfume", "/perfume"));
        // Act
        client.post().uri("/category").accept(MediaType.APPLICATION_JSON)
            .body(categorySaveDTO)
            .exchange()
            .expectStatus().isCreated()
            .expectBody(CategoryResponseDTO.class)
            .consumeWith(response -> {
                // Assert
                var res = response.getResponseBody();
                assertEquals(69L, res.id());
                assertEquals("Perfume", res.name());
            });
    }

    @Test void testPut(){
        // Arrange
        CategorySaveDTO categoryUpdateDTO = new CategorySaveDTO("Perfumaria", "/perfumaria");

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO(69L, "Perfumaria", "/perfumaria");
        given(service.update(69L, categoryUpdateDTO)).willReturn(categoryResponseDTO);
    
        // Act
        client.put().uri("/category/69").accept(MediaType.APPLICATION_JSON)
            .body(categoryUpdateDTO)
            .exchange()
            .expectStatus().isOk()
            .expectBody(CategoryResponseDTO.class)
            .consumeWith(response -> {
                // Arrange
                var res = response.getResponseBody();
                assertNotNull(res);
                assertEquals("Perfumaria", res.name());
                assertEquals("/perfumaria", res.path());
            });
    }
}
