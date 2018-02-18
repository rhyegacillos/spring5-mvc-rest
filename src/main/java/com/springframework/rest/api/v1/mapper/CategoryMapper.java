package com.springframework.rest.api.v1.mapper;

import com.springframework.rest.api.v1.model.CategoryDTO;
import com.springframework.rest.domain.Category;
import com.springframework.rest.service.CategoryService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring", uses = CategoryService.class)
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    CategoryDTO categoryToCategoryDTO(Category category);
}
