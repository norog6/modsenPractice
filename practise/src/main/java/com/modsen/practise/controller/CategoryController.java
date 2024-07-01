package com.modsen.practise.controller;

import com.modsen.practise.dto.RequestCategoryDTO;
import com.modsen.practise.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<RequestCategoryDTO>> getAllCategories() {
        List<RequestCategoryDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
    @GetMapping("/page")
    public ResponseEntity<List<RequestCategoryDTO>> getAllCategoriesByPage(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                                                           @RequestParam(value = "limit", defaultValue = "10") @Min(1) @Max(100) Integer limit,
                                                                           @RequestParam(value = "sort", required = false) String sortField,
                                                                           @RequestParam(value = "direction", defaultValue = "asc") String sortDirection){
        Sort.Direction direction = Sort.Direction.fromString(sortDirection.toLowerCase());
        Page<RequestCategoryDTO> page = categoryService.getAllCategoriesByPage(
                PageRequest.of(offset, limit, Sort.by(direction, sortField))
        );
        List<RequestCategoryDTO> categories = page.getContent();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestCategoryDTO> getCategoryById(@PathVariable @Min(0) Long id) {
        RequestCategoryDTO category = categoryService.getCategoryById(id);
        return  category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<RequestCategoryDTO> createCategory(@RequestBody @Valid RequestCategoryDTO requestCategoryDTO) {
        RequestCategoryDTO createdCategory = categoryService.createCategory(requestCategoryDTO);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequestCategoryDTO> updateCategory(@PathVariable @Min(0) Long id, @RequestBody @Valid RequestCategoryDTO requestCategoryDTO) {
        RequestCategoryDTO updatedCategory = categoryService.updateCategory(id, requestCategoryDTO);
        return updatedCategory != null ? ResponseEntity.ok(updatedCategory) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable @Min(0) Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}