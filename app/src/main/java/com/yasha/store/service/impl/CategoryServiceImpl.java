package com.yasha.store.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yasha.store.service.CategoryService;
import com.yasha.store.dto.CategoryResponseDTO;
import com.yasha.store.dto.CategorySaveDTO;
import com.yasha.store.entity.Category;
import com.yasha.store.mapping.CategoryMapper;
import com.yasha.store.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository repository;

    @Autowired
    private CategoryMapper mapper;

    @Override
    public CategoryResponseDTO save(CategorySaveDTO c){
        Category entity = mapper.toEntity(c);
        Category savedEntity = repository.save(entity);
        return mapper.toDTO(savedEntity);
    }

    @Override
    public Optional<CategoryResponseDTO> findById(Long id){
        Optional<Category> c = repository.findById(id);
        Optional<CategoryResponseDTO> dto = c.map(cat -> mapper.toDTO(cat));
        return dto;
        //return repository.findById(id).map(c -> mapper.toDTO(c));
    }
}

