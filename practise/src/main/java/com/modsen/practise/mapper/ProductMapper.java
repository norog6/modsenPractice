package com.modsen.practise.mapper;

import com.modsen.practise.dto.RequestProductDTO;
import com.modsen.practise.dto.ResponseProductDTO;
import com.modsen.practise.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface ProductMapper {
    public RequestProductDTO toREQDto(Product product);
    public ResponseProductDTO toDto(Product product);
    public Product toEntity(RequestProductDTO requestProductDTO);
    public Product toEntity(ResponseProductDTO requestProductDTO);
}
