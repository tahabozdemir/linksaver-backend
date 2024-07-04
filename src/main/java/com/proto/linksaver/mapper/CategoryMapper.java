package com.proto.linksaver.mapper;

import com.proto.linksaver.model.Category;
import com.proto.linksaver.payload.response.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryResponse categoryToCategoryResponse(Category category);
}
