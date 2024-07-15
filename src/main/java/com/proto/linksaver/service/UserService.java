package com.proto.linksaver.service;

import com.proto.linksaver.dto.UserDto;
import com.proto.linksaver.mapper.UserMapper;
import com.proto.linksaver.model.User;
import com.proto.linksaver.payload.response.UserResponse;
import com.proto.linksaver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public UserResponse create(UserDto userDto) {
        User user = new User(userDto.cognitoId(), userDto.email());
        userRepository.save(user);
        return UserMapper.INSTANCE.userToUserResponse(user);
    }
}
