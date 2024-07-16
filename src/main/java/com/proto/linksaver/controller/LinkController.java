package com.proto.linksaver.controller;

import com.proto.linksaver.dto.LinkDto;
import com.proto.linksaver.payload.request.LinkRequest;
import com.proto.linksaver.payload.response.BaseResponse;
import com.proto.linksaver.payload.response.LinkResponse;
import com.proto.linksaver.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/links")
public class LinkController {
    private final LinkService linkService;

    @PostMapping
    public ResponseEntity<BaseResponse<LinkResponse>> create(@RequestBody LinkRequest linkRequest) {
        LinkResponse linkResponse = linkService.create(linkRequest);
        BaseResponse<LinkResponse> response = new BaseResponse<>(linkResponse);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<LinkResponse>>> getAll(@RequestParam String categoryId) {
        List<LinkResponse> linkResponse = linkService.getAll(categoryId);
        BaseResponse<List<LinkResponse>> response = new BaseResponse<>(linkResponse);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<LinkResponse>> get(@PathVariable String id) {
        LinkResponse linkResponse = linkService.getById(id);
        BaseResponse<LinkResponse> response = new BaseResponse<>(linkResponse);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse<LinkResponse>> update(@RequestBody LinkDto linkDto, @PathVariable String id) {
        LinkResponse linkResponse = linkService.update(id, linkDto);
        BaseResponse<LinkResponse> response = new BaseResponse<>(linkResponse);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id, @RequestParam String categoryId) {
        linkService.delete(categoryId, id);
        return ResponseEntity
                .ok()
                .build();
    }
}
