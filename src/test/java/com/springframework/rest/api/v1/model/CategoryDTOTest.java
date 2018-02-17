package com.springframework.rest.api.v1.model;

import com.springframework.rest.api.v1.mapper.CategoryMapper;
import com.springframework.rest.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

public class CategoryDTOTest {

    public static final String NAME = "Category";
    public static final long ID = 2L;
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTOtest() {

        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        assertNotNull(categoryDTO);
        assertEquals(Long.valueOf(2L), categoryDTO.getId());
        assertEquals("Category", categoryDTO.getName());
    }
}