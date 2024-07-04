package com.proto.linksaver.controller;

import com.proto.linksaver.dto.LinkDto;
import com.proto.linksaver.payload.response.BaseResponse;
import com.proto.linksaver.payload.response.LinkResponse;
import com.proto.linksaver.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class LinkController {
    private final LinkService linkService;

    @PostMapping("/{categoryId}/link")
    public ResponseEntity<BaseResponse<LinkResponse>> create(@PathVariable String categoryId, @RequestBody LinkDto linkDto) {
        LinkResponse linkResponse = linkService.create(categoryId, linkDto);
        BaseResponse<LinkResponse> response = new BaseResponse<>(linkResponse);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/links")
    public ResponseEntity<BaseResponse<List<LinkResponse>>> getAll() {
        List<LinkResponse> linkResponse = linkService.getAll();
        BaseResponse<List<LinkResponse>> response = new BaseResponse<>(linkResponse);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/link/{id}")
    public ResponseEntity<BaseResponse<LinkResponse>> get(@PathVariable String id) {
        LinkResponse linkResponse = linkService.getById(id);
        BaseResponse<LinkResponse> response = new BaseResponse<>(linkResponse);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @PatchMapping("/{categoryId}/link/{id}")
    public ResponseEntity<BaseResponse<LinkResponse>> update(@RequestBody LinkDto linkDto, @PathVariable String id, @PathVariable String categoryId) {
        LinkResponse linkResponse = linkService.update(categoryId, id, linkDto);
        BaseResponse<LinkResponse> response = new BaseResponse<>(linkResponse);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @DeleteMapping("/{categoryId}/link/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id, @PathVariable String categoryId) {
        linkService.delete(categoryId, id);
        return ResponseEntity
                .ok()
                .build();
    }
}
