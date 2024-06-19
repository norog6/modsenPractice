package com.modsen.practise.service.impl;

import com.modsen.practise.dto.ProductDTO;
import com.modsen.practise.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public List<ProductDTO> getAllProducts() {
        throw new UnsupportedOperationException("getAllProducts is not supported");
    }

    @Override
    public ProductDTO getProductById(Long id) {
        throw new UnsupportedOperationException("getProductById is not supported");
    }

    @Override
    public List<ProductDTO> getProductsByCategory(Long categoryId) {
        throw new UnsupportedOperationException("getProductsByCategory is not supported");
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        throw new UnsupportedOperationException("createProduct is not supported");
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        throw new UnsupportedOperationException("updateProduct is not supported");
    }

    @Override
    public void deleteProduct(Long id) {
        throw new UnsupportedOperationException("deleteProduct is not supported");
    }
}