package com.proto.linksaver.service;

import com.proto.linksaver.dto.CategoryDto;
import com.proto.linksaver.exception.ResourceNotFoundException;
import com.proto.linksaver.model.Category;
import com.proto.linksaver.payload.response.CategoryResponse;
import com.proto.linksaver.repository.CategoryRepository;
import com.proto.linksaver.mapper.CategoryMapper;
import com.proto.linksaver.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final LinkRepository linkRepository;

    public CategoryResponse create(CategoryDto categoryDto) {
        Category category = new Category(categoryDto.title(), categoryDto.emoji());
        categoryRepository.save(category);
        return CategoryMapper.INSTANCE.categoryToCategoryResponse(category);
    }

    public CategoryResponse update(String id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.ResourceNotFoundExceptionCodeEnum.CATEGORY_NOT_FOUND));
        category.setTitle(categoryDto.title());
        category.setEmoji(categoryDto.emoji());
        category.setLinks(categoryDto.links());
        categoryRepository.save(category);
        return CategoryMapper.INSTANCE.categoryToCategoryResponse(category);
    }

    public void delete(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.ResourceNotFoundExceptionCodeEnum.CATEGORY_NOT_FOUND));
        linkRepository.deleteAllByCategoryId(category.getId());
        categoryRepository.delete(category);
    }

    public List<CategoryResponse> getAll() {
        List<CategoryResponse> categoryResponseList = categoryRepository
                .findAll()
                .stream()
                .map(CategoryMapper.INSTANCE::categoryToCategoryResponse)
                .toList();

        return categoryResponseList;
    }

    public CategoryResponse getById(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.ResourceNotFoundExceptionCodeEnum.CATEGORY_NOT_FOUND));
        return CategoryMapper.INSTANCE.categoryToCategoryResponse(category);
    }
}
