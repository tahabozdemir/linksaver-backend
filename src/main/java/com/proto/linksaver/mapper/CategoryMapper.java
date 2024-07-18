package com.proto.linksaver.mapper;

import com.proto.linksaver.dto.CategoryDto;
import com.proto.linksaver.model.Category;
import com.proto.linksaver.payload.response.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryResponse categoryToCategoryResponse(Category category);
    void updateCategoryFromDto(CategoryDto categoryDto, @MappingTarget Category category);
}
