package com.modsen.practise.mapper;

import com.modsen.practise.dto.RequestCategoryDTO;
import com.modsen.practise.dto.ResponseCategoryDTO;
import com.modsen.practise.entity.Category;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CategoryMapper {
    public RequestCategoryDTO toREQDto(Category category);
    public ResponseCategoryDTO toRESDto(Category category);
    public Category toEntity(RequestCategoryDTO requestCategoryDTO);
    public Category toEntity(ResponseCategoryDTO requestCategoryDTO);

}
