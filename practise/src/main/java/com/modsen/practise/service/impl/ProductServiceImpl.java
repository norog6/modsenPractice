package com.modsen.practise.service.impl;

import com.modsen.practise.dto.CategoryDTO;
import com.modsen.practise.dto.ProductDTO;
import com.modsen.practise.entity.Category;
import com.modsen.practise.entity.Product;
import com.modsen.practise.mapper.CategoryMapper;
import com.modsen.practise.mapper.ProductMapper;
import com.modsen.practise.repository.CategoryRepository;
import com.modsen.practise.repository.ProductRepository;
import com.modsen.practise.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("No products found");
        }
        return products.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductDTO> getAllProductsByPage(PageRequest pageRequest) {
        Page<Product> productPage = productRepository.findAll(pageRequest);
        if (productPage.isEmpty()) {
            throw new ResourceNotFoundException("No products found.");
        }
        return productPage.map(productMapper::toDto);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductDTO> getProductsByCategory(Long categoryId) {
        Optional<List<Product>> optionalProducts = productRepository.findByCategoryId(categoryId);
        List<Product> products = optionalProducts.orElseThrow(() -> new ResourceNotFoundException("No products found for category with id: " + categoryId));
        return products.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        CategoryDTO categoryDTO = productDTO.getCategory();
        Category category = categoryRepository.findByName(categoryDTO.getName())
                .orElseGet(() -> {
                    Category newCategory = categoryMapper.toEntity(categoryDTO);
                    return categoryRepository.save(newCategory);
                });
        Product product = productMapper.toEntity(productDTO);
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());

        CategoryDTO categoryDTO = productDTO.getCategory();
        if (categoryDTO != null && categoryDTO.getId() != existingProduct.getCategory().getId()) {
            Category category = categoryRepository.findById(categoryDTO.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryDTO.getId()));
            existingProduct.setCategory(category);
        }

        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
    }

}