package com.modsen.practise.service.impl;

import com.modsen.practise.dto.RequestCategoryDTO;
import com.modsen.practise.dto.RequestProductDTO;
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
    public List<RequestProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("No products found");
        }
        return products.stream()
                .map(productMapper::toREQDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<RequestProductDTO> getAllProductsByPage(PageRequest pageRequest) {
        Page<Product> productPage = productRepository.findAll(pageRequest);
        if (productPage.isEmpty()) {
            throw new ResourceNotFoundException("No products found.");
        }
        return productPage.map(productMapper::toREQDto);
    }

    @Override
    public RequestProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return productMapper.toREQDto(product);
    }

    @Override
    public List<RequestProductDTO> getProductsByCategory(Long categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
       if (products.isEmpty())
        {new ResourceNotFoundException("No products found for category with id: " + categoryId);}
        return products.stream()
                .map(productMapper::toREQDto)
                .collect(Collectors.toList());
    }

    @Override
    public RequestProductDTO createProduct(RequestProductDTO requestProductDTO) {
        RequestCategoryDTO requestCategoryDTO = requestProductDTO.getCategory();
        Category category = categoryRepository.findByName(requestCategoryDTO.getName()).get();
        Product product = productMapper.toEntity(requestProductDTO);
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        return productMapper.toREQDto(savedProduct);
    }

    @Override
    public RequestProductDTO updateProduct(Long id, RequestProductDTO requestProductDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        existingProduct.setName(requestProductDTO.getName());
        existingProduct.setDescription(requestProductDTO.getDescription());
        existingProduct.setPrice(requestProductDTO.getPrice());

        RequestCategoryDTO requestCategoryDTO = requestProductDTO.getCategory();
        if (requestCategoryDTO != null && requestCategoryDTO.getId() != existingProduct.getCategory().getId()) {
            Category category = categoryRepository.findById(requestCategoryDTO.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + requestCategoryDTO.getId()));
            existingProduct.setCategory(category);
        }

        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toREQDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
    }

}