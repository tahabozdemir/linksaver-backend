package com.proto.linksaver.service;

import com.proto.linksaver.dto.CategoryDto;
import com.proto.linksaver.exception.ResourceNotFoundException;
import com.proto.linksaver.model.Category;
import com.proto.linksaver.model.User;
import com.proto.linksaver.payload.request.CategoryRequest;
import com.proto.linksaver.payload.response.CategoryResponse;
import com.proto.linksaver.repository.CategoryRepository;
import com.proto.linksaver.mapper.CategoryMapper;
import com.proto.linksaver.repository.LinkRepository;
import com.proto.linksaver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final LinkRepository linkRepository;

    public CategoryResponse create(CategoryRequest categoryRequest) {
        Category category = new Category(categoryRequest.title(), categoryRequest.emoji());
        User user = userRepository.findById(categoryRequest.userId())
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.ResourceNotFoundExceptionCodeEnum.ACCOUNT_NOT_FOUND));

        ArrayList<String> categories = user.getCategories();
        categoryRepository.save(category);
        categories.add(category.getId());
        user.setCategories(categories);
        userRepository.save(user);
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

    public void delete(String userId, String id) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.ResourceNotFoundExceptionCodeEnum.ACCOUNT_NOT_FOUND));

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.ResourceNotFoundExceptionCodeEnum.CATEGORY_NOT_FOUND));

        ArrayList<String> links = category.getLinks();
        user.getCategories().removeIf(categoryId -> categoryId.equals(id));
        linkRepository.deleteAllById(links);
        categoryRepository.delete(category);
    }

    public List<CategoryResponse> getAll(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.ResourceNotFoundExceptionCodeEnum.ACCOUNT_NOT_FOUND));

        List<String> categoryIds = user.getCategories();
        List<Category> categories = categoryRepository.findAllById(categoryIds);
        List<CategoryResponse> categoryResponseList = categories
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
