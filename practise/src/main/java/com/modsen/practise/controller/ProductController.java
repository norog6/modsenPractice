package com.modsen.practise.controller;

import com.modsen.practise.dto.RequestProductDTO;
import com.modsen.practise.service.ProductService;
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
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<RequestProductDTO>> getAllProducts() {
        List<RequestProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/page")
    public ResponseEntity<List<RequestProductDTO>> getAllProductsByPage(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                                                        @RequestParam(value = "limit", defaultValue = "10") @Min(1) @Max(100) Integer limit,
                                                                        @RequestParam(value = "sort", required = false) String sortField,
                                                                        @RequestParam(value = "direction", defaultValue = "asc") String sortDirection){
        Sort.Direction direction = Sort.Direction.fromString(sortDirection.toLowerCase());
        Page<RequestProductDTO> page = productService.getAllProductsByPage(PageRequest.of(offset, limit, Sort.by(direction, sortField)));
        List<RequestProductDTO> products = page.getContent();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestProductDTO> getProductById(@PathVariable @Min(0) Long id) {
        RequestProductDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<RequestProductDTO>> getProductsByCategory(@PathVariable @Min(0) Long categoryId) {
        List<RequestProductDTO> products = productService.getProductsByCategory(categoryId);
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequestProductDTO> updateProduct(@PathVariable @Min(0) Long id, @RequestBody @Valid RequestProductDTO requestProductDTO) {
        RequestProductDTO updatedProduct = productService.updateProduct(id, requestProductDTO);
        return updatedProduct != null ? ResponseEntity.ok(updatedProduct) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<RequestProductDTO> createProduct(@RequestBody @Valid RequestProductDTO requestProductDTO) {
        RequestProductDTO createdProduct = productService.createProduct(requestProductDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable @Min(0) Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}