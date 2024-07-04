package com.proto.linksaver.controller;

import com.proto.linksaver.dto.CategoryDto;
import com.proto.linksaver.payload.response.BaseResponse;
import com.proto.linksaver.payload.response.CategoryResponse;
import com.proto.linksaver.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<BaseResponse<CategoryResponse>> create(@RequestBody CategoryDto categoryDto) {
        CategoryResponse categoryResponse = categoryService.create(categoryDto);
        BaseResponse<CategoryResponse> response = new BaseResponse<>(categoryResponse);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<BaseResponse<List<CategoryResponse>>> getAll() {
        List<CategoryResponse> categoryResponse = categoryService.getAll();
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
    public ResponseEntity<Void> delete(@PathVariable String id) {
        categoryService.delete(id);
        return ResponseEntity
                .ok()
                .build();
    }
}
