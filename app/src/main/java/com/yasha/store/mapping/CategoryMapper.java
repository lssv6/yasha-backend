package com.yasha.store.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

import com.yasha.store.dto.CategoryResponseDTO;
import com.yasha.store.dto.CategorySaveDTO;
import com.yasha.store.entity.Category;

@Mapper(componentModel = ComponentModel.SPRING)
public interface CategoryMapper {
    public CategoryResponseDTO toDTO(Category c);
    public Category toEntity(CategorySaveDTO c);
}
