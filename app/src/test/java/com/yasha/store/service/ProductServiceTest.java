package com.yasha.store.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.Mapper;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.yasha.store.dto.ProductResponseDTO;
import com.yasha.store.dto.ProductSaveDTO;
import com.yasha.store.entity.Product;
import com.yasha.store.mapping.ProductMapper;
import com.yasha.store.repository.ProductRepository;

@SpringBootTest
public class ProductServiceTest {
    @MockitoBean
    private ProductRepository repository;
    
    @InjectMocks
    private ProductService service;

    @InjectMocks
    private ProductMapper mapper;

    static Product parfum;

    @BeforeAll
    static void setUp() {
        parfum = new Product();
        parfum.setId(9L);
        parfum.setName("Perfume Dora Aventureira");
        parfum.setPrice(4500L);
        parfum.setOldPrice(9889L);
        parfum.setTagName("perfume-infantil");
        parfum.setDescription(
            "O perfume da Dora, a aventureira, prepara você para as maiores aventuras");
    }

    @Test void shouldFindById(){
        // Arrange
        Product p = new Product();
        p.setId(99L);
        p.setName("Perfume do cebolinha");
        p.setPrice(4999L);
        p.setOldPrice(9999L);
        p.setTagName("perfume-do-cebolinha");
        p.setDescription("Descricao");

        given(repository.findById(99L)).willReturn(Optional.of(p));

        // Act
        ProductResponseDTO productResponseDTO = service.findById(99L).get();

        // Assert
        assertEquals(99L, productResponseDTO.id());
        assertEquals("Descricao", productResponseDTO.description());
    }

    @Test void shouldUpdate(){
        // Arrange
        given(repository.findById(9L)).willReturn(Optional.of(parfum));

        // Act
        ProductSaveDTO prodToSave = new ProductSaveDTO("Perfume do Cascão", 4999L, 9999L, "Descricao");
        ProductResponseDTO responseDTO = service.update(9L, prodToSave);

        // Assert
        assertEquals(service.findById(9L).get().name(), responseDTO.name());
    }
    
}
