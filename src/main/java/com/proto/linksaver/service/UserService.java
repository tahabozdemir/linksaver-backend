package com.proto.linksaver.service;

import com.proto.linksaver.dto.UserDto;
import com.proto.linksaver.mapper.UserMapper;
import com.proto.linksaver.model.User;
import com.proto.linksaver.payload.response.LinkResponse;
import com.proto.linksaver.payload.response.UserResponse;
import com.proto.linksaver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final LinkService linkService;

    public UserResponse create(UserDto userDto) {
        User user = new User(userDto.cognitoId(), userDto.email());
        userRepository.save(user);
        return UserMapper.INSTANCE.userToUserResponse(user);
    }

    public List<LinkResponse> getAllLinksByUserId(String userId) {
        return linkService.getAllByUserId(userId);
    }

    public List<LinkResponse> getFavoriteLinksByUserId(String userId) {
        return linkService.getFavoritesByUserId(userId);
    }

    public List<LinkResponse> searchLinksByTitle(String userId, String title) {
        return linkService.searchByTitle(userId, title);
    }

    public List<LinkResponse> searchFavoriteLinksByTitle(String userId, String title) {
        return linkService.searchFavoriteByTitle(userId, title);
    }
}
