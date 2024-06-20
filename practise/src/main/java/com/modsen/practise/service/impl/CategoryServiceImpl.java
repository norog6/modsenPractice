package com.modsen.practise.service.impl;

import com.modsen.practise.dto.CategoryDTO;
import com.modsen.practise.service.CategoryService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<CategoryDTO> getAllCategories() {
        throw new UnsupportedOperationException("getAllCategories is not supported");
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        throw new UnsupportedOperationException("getCategoryById is not supported");
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        throw new UnsupportedOperationException("createCategory is not supported");
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        throw new UnsupportedOperationException("updateCategory is not supported");
    }

    @Override
    public void deleteCategory(Long id) {
        throw new UnsupportedOperationException("deleteCategory is not supported");
    }
}