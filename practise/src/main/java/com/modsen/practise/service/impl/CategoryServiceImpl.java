package com.modsen.practise.service.impl;

import com.modsen.practise.dto.RequestCategoryDTO;
import com.modsen.practise.entity.Category;
import com.modsen.practise.mapper.CategoryMapper;
import com.modsen.practise.repository.CategoryRepository;
import com.modsen.practise.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @Override
    public List<RequestCategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new ResourceNotFoundException("No categories found");
        }
        return categories.stream()
                .map(categoryMapper::toREQDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<RequestCategoryDTO> getAllCategoriesByPage(PageRequest pageRequest) {
        Page<Category> categoryPage = categoryRepository.findAll(pageRequest);
        if (categoryPage.isEmpty()) {
            throw new ResourceNotFoundException("No categories found.");
        }
        return categoryPage.map(categoryMapper::toREQDto);
    }

    @Override
    public RequestCategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        return categoryMapper.toREQDto(category);
    }
    @Override
    public RequestCategoryDTO createCategory(RequestCategoryDTO requestCategoryDTO) {
        Category category = categoryMapper.toEntity(requestCategoryDTO);
        if (categoryRepository.existsByName(category.getName())) {
            throw new IllegalArgumentException("Category with this name already exists");
        }
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toREQDto(savedCategory);
    }

    @Override
    public RequestCategoryDTO updateCategory(Long id, RequestCategoryDTO requestCategoryDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        category.setName(requestCategoryDTO.getName());
        Category updatedCategory = categoryRepository.save(category);
        return categoryMapper.toREQDto(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        categoryRepository.delete(category);
    }

}