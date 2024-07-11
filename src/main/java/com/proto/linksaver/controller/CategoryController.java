package com.proto.linksaver.controller;

import com.proto.linksaver.dto.CategoryDto;
import com.proto.linksaver.payload.request.CategoryRequest;
import com.proto.linksaver.payload.response.BaseResponse;
import com.proto.linksaver.payload.response.CategoryResponse;
import com.proto.linksaver.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<BaseResponse<CategoryResponse>> create(@RequestBody CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = categoryService.create(categoryRequest);
        BaseResponse<CategoryResponse> response = new BaseResponse<>(categoryResponse);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<CategoryResponse>>> getAll(@RequestParam String userId) {
        List<CategoryResponse> categoryResponse = categoryService.getAll(userId);
        BaseResponse<List<CategoryResponse>> response = new BaseResponse<>(categoryResponse);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<CategoryResponse>> get(@PathVariable String id) {
        CategoryResponse categoryResponse = categoryService.getById(id);
        BaseResponse<CategoryResponse> response = new BaseResponse<>(categoryResponse);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse<CategoryResponse>> update(@PathVariable String id, @RequestBody CategoryDto categoryDto) {
        CategoryResponse categoryResponse = categoryService.update(id, categoryDto);
        BaseResponse<CategoryResponse> response = new BaseResponse<>(categoryResponse);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id, @RequestBody CategoryRequest categoryRequest) {
        categoryService.delete(categoryRequest.userId(), id);
        return ResponseEntity
                .ok()
                .build();
    }
}
