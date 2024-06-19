package org.modsen.practise.service.impl;

import org.modsen.practise.dto.ProductDTO;
import org.modsen.practise.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    private Map<Long, ProductDTO> products = new HashMap<>();
    private long nextId = 1;

    public List<ProductDTO> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public ProductDTO getProductById(Long id) {
        return products.get(id);
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        productDTO.setId(nextId++);
        products.put(productDTO.getId(), productDTO);
        return productDTO;
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        productDTO.setId(id);
        products.put(id, productDTO);
        return productDTO;
    }

    public void deleteProduct(Long id) {
        products.remove(id);
    }
}