package com.proto.linksaver.controller;

import com.proto.linksaver.dto.UserDto;
import com.proto.linksaver.payload.response.BaseResponse;
import com.proto.linksaver.payload.response.LinkResponse;
import com.proto.linksaver.payload.response.UserResponse;
import com.proto.linksaver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Value("${secret.lambda.api-key}")
    private String API_KEY;

    @PostMapping
    public ResponseEntity<BaseResponse<UserResponse>> create(@RequestHeader("X-API-Key") String apiKey, @RequestBody UserDto userDto) {
        if (apiKey.equals(API_KEY)) {
            UserResponse userResponse = userService.create(userDto);
            BaseResponse<UserResponse> response = new BaseResponse<>(userResponse);
            return ResponseEntity
                    .ok()
                    .body(response);
        }
        return ResponseEntity
                .badRequest().build();
    }

    @GetMapping("/{userId}/links")
    @PreAuthorize("#userId == authentication.name")
    public ResponseEntity<BaseResponse<List<LinkResponse>>> getAllLinks(@PathVariable String userId)  {
        List<LinkResponse> linkResponse = userService.getAllLinksByUserId(userId);
        BaseResponse<List<LinkResponse>> response = new BaseResponse<>(linkResponse);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/{userId}/links/search")
    @PreAuthorize("#userId == authentication.name")
    public ResponseEntity<BaseResponse<List<LinkResponse>>> searchLinks(@PathVariable String userId, @RequestParam String title) {
        List<LinkResponse> linkResponse = userService.searchLinksByTitle(userId, title);
        BaseResponse<List<LinkResponse>> response = new BaseResponse<>(linkResponse);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/{userId}/favorites")
    @PreAuthorize("#userId == authentication.name")
    public ResponseEntity<BaseResponse<List<LinkResponse>>> getFavoriteLinks(@PathVariable String userId) {
        List<LinkResponse> linkResponse = userService.getFavoriteLinksByUserId(userId);
        BaseResponse<List<LinkResponse>> response = new BaseResponse<>(linkResponse);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/{userId}/favorites/search")
    @PreAuthorize("#userId == authentication.name")
    public ResponseEntity<BaseResponse<List<LinkResponse>>> searchFavoriteLinks(@PathVariable String userId, @RequestParam String title) {
        List<LinkResponse> linkResponse = userService.searchFavoriteLinksByTitle(userId, title);
        BaseResponse<List<LinkResponse>> response = new BaseResponse<>(linkResponse);
        return ResponseEntity
                .ok()
                .body(response);
    }
}
