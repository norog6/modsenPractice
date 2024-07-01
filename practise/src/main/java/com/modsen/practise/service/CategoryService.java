package com.modsen.practise.service;

import com.modsen.practise.dto.RequestCategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CategoryService {
    List<RequestCategoryDTO> getAllCategories();
    Page<RequestCategoryDTO> getAllCategoriesByPage(PageRequest pageRequest);
    RequestCategoryDTO getCategoryById(Long id);
    RequestCategoryDTO createCategory(RequestCategoryDTO requestCategoryDTO);
    RequestCategoryDTO updateCategory(Long id, RequestCategoryDTO requestCategoryDTO);
    void deleteCategory(Long id);

}