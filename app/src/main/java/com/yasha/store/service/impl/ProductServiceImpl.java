package com.yasha.store.service.impl;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.yasha.store.dto.ProductResponseDTO;
import com.yasha.store.dto.ProductSaveDTO;
import com.yasha.store.entity.Product;
import com.yasha.store.mapping.ProductMapper;
import com.yasha.store.repository.ProductRepository;
import com.yasha.store.service.ProductService;

public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository repository;
    @Autowired
    private ProductMapper mapper;

    @Override
    public Optional<ProductResponseDTO> findById(Long id){
        return repository.findById(id).map(prod -> mapper.toDTO(prod));

    }
    @Override
    public ProductResponseDTO save(ProductSaveDTO productSaveDTO){
        Product saved = repository.save(mapper.toEntity(productSaveDTO));
        return mapper.toDTO(saved);
    }

    public ProductResponseDTO update(Long id, ProductSaveDTO saveDTO) {
        Product entity = mapper.toEntity(saveDTO);
        entity.setId(id);
        Product saved = repository.save(entity);
        return mapper.toDTO(saved);
    }
    
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
