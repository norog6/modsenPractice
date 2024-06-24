package com.modsen.practise.mapper;

import com.modsen.practise.dto.ProductDTO;
import com.modsen.practise.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface ProductMapper {
    public ProductDTO toDto(Product product);

    public Product toEntity(ProductDTO productDTO);
}
