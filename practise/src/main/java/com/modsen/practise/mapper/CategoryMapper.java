package com.modsen.practise.mapper;

import com.modsen.practise.dto.CategoryDTO;
import com.modsen.practise.entity.Category;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CategoryMapper {
    public CategoryDTO toDto(Category category);
    public Category toEntity(CategoryDTO categoryDTO);

}
