package com.yasha.store.service;

import java.util.Optional;

import com.yasha.store.dto.CategoryResponseDTO;
import com.yasha.store.dto.CategorySaveDTO;

public interface CategoryService {
    public CategoryResponseDTO save(CategorySaveDTO c);
    public Optional<CategoryResponseDTO> findById(Long id);
}
