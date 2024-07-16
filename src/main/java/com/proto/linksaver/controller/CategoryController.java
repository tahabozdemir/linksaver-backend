package com.proto.linksaver.controller;

import com.proto.linksaver.dto.CategoryDto;
import com.proto.linksaver.payload.request.CategoryRequest;
import com.proto.linksaver.payload.response.BaseResponse;
import com.proto.linksaver.payload.response.CategoryResponse;
import com.proto.linksaver.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    @PreAuthorize("#categoryRequest.userId() == authentication.name")
    public ResponseEntity<BaseResponse<CategoryResponse>> create(@RequestBody CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = categoryService.create(categoryRequest);
        BaseResponse<CategoryResponse> response = new BaseResponse<>(categoryResponse);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping
    @PreAuthorize("#userId == authentication.name")
    public ResponseEntity<BaseResponse<List<CategoryResponse>>> getAll(@RequestParam String userId) {
        List<CategoryResponse> categoryResponse = categoryService.getAll(userId);
        BaseResponse<List<CategoryResponse>> response = new BaseResponse<>(categoryResponse);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("#userId == authentication.name")
    public ResponseEntity<BaseResponse<CategoryResponse>> get(@RequestParam String userId, @PathVariable String id) {
        CategoryResponse categoryResponse = categoryService.getById(id);
        BaseResponse<CategoryResponse> response = new BaseResponse<>(categoryResponse);
        return ResponseEntity
                .ok()
                .body(response);
    }


    @PatchMapping("/{id}")
    @PreAuthorize("#userId == authentication.name")
    public ResponseEntity<BaseResponse<CategoryResponse>> update(@RequestParam String userId, @PathVariable String id, @RequestBody CategoryDto categoryDto) {
        CategoryResponse categoryResponse = categoryService.update(id, categoryDto);
        BaseResponse<CategoryResponse> response = new BaseResponse<>(categoryResponse);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("#userId == authentication.name")
    public ResponseEntity<Void> delete( @RequestParam String userId, @PathVariable String id) {
        categoryService.delete(userId, id);
        return ResponseEntity
                .ok()
                .build();
    }




}
