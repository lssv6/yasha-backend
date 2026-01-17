package com.yasha.store.service;

import java.util.Optional;

import com.yasha.store.dto.ProductResponseDTO;
import com.yasha.store.dto.ProductSaveDTO;

public interface ProductService{
    public Optional<ProductResponseDTO> findById(Long id);
    public ProductResponseDTO save(ProductSaveDTO productSaveDTO);
    
    ProductResponseDTO update(Long id, ProductSaveDTO toSave);
    void delete(Long id);
    
}
