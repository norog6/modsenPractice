package com.modsen.practise.service;

import com.modsen.practise.dto.RequestProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProductService {
    List<RequestProductDTO> getAllProducts();
    Page<RequestProductDTO> getAllProductsByPage(PageRequest pageRequest);
    RequestProductDTO getProductById(Long id);
    RequestProductDTO createProduct(RequestProductDTO requestProductDTO);
    RequestProductDTO updateProduct(Long id, RequestProductDTO requestProductDTO);
    void deleteProduct(Long id);
    List<RequestProductDTO> getProductsByCategory(Long categoryId);
}