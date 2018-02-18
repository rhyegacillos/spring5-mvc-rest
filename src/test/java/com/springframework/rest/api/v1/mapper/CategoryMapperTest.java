package com.springframework.rest.api.v1.mapper;

import com.springframework.rest.api.v1.model.CategoryDTO;
import com.springframework.rest.domain.Category;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryMapperTest {

    private static final String NAME = "rhye";

    CategoryMapper mapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO() {
        Category category = new Category();
        category.setName(NAME);

        CategoryDTO categoryDTO = mapper.categoryToCategoryDTO(category);

        assertEquals(category.getName(), categoryDTO.getName());
    }
}