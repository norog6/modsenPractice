package org.modsen.practise.service.impl;

import org.modsen.practise.dto.CategoryDTO;
import org.modsen.practise.service.CategoryService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    private Map<Long, CategoryDTO> categories = new HashMap<>();
    private long nextId = 1;

    public List<CategoryDTO> getAllCategories() {
        return new ArrayList<>(categories.values());
    }

    public CategoryDTO getCategoryById(Long id) {
        return categories.get(id);
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        categoryDTO.setId(nextId++);
        categories.put(categoryDTO.getId(), categoryDTO);
        return categoryDTO;
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        if (!categories.containsKey(id)) {
            return null;
        }
        categoryDTO.setId(id);
        categories.put(id, categoryDTO);
        return categoryDTO;
    }

    public void deleteCategory(Long id) {
        categories.remove(id);
    }
}