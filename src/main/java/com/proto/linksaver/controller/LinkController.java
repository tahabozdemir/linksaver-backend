package com.proto.linksaver.controller;

import com.proto.linksaver.dto.LinkDto;
import com.proto.linksaver.payload.request.LinkRequest;
import com.proto.linksaver.payload.response.BaseResponse;
import com.proto.linksaver.payload.response.LinkResponse;
import com.proto.linksaver.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/links")
public class LinkController {
    private final LinkService linkService;

    @PostMapping
    @PreAuthorize("#linkRequest.userId == authentication.name")
    public ResponseEntity<BaseResponse<LinkResponse>> create(@RequestBody LinkRequest linkRequest) {
        LinkResponse linkResponse = linkService.create(linkRequest);
        BaseResponse<LinkResponse> response = new BaseResponse<>(linkResponse);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("userId == authentication.name")
    public ResponseEntity<BaseResponse<LinkResponse>> get(@PathVariable String id, @RequestParam String userId) {
        LinkResponse linkResponse = linkService.getById(id);
        BaseResponse<LinkResponse> response = new BaseResponse<>(linkResponse);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("#linkDto.userId() == authentication.name")
    public ResponseEntity<BaseResponse<LinkResponse>> update(@RequestBody LinkDto linkDto, @PathVariable String id) {
        LinkResponse linkResponse = linkService.update(id, linkDto);
        BaseResponse<LinkResponse> response = new BaseResponse<>(linkResponse);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("#userId == authentication.name")
    public ResponseEntity<Void> delete(@PathVariable String id, @RequestParam String userId) {
        linkService.delete(id);
        return ResponseEntity
                .ok()
                .build();
    }
}
