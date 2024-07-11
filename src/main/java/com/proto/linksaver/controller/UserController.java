package com.proto.linksaver.controller;

import com.proto.linksaver.dto.UserDto;
import com.proto.linksaver.payload.response.BaseResponse;
import com.proto.linksaver.payload.response.UserResponse;
import com.proto.linksaver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<BaseResponse<UserResponse>> create(@RequestBody UserDto userDto) {
        UserResponse userResponse = userService.create(userDto);
        BaseResponse<UserResponse> response = new BaseResponse<>(userResponse);
        return ResponseEntity
                .ok()
                .body(response);
    }
}
