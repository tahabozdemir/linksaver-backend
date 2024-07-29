package com.proto.linksaver.migrations;

import com.proto.linksaver.exception.ResourceNotFoundException;
import com.proto.linksaver.migrations.properties.CognitoProperties;
import com.proto.linksaver.model.User;
import com.proto.linksaver.payload.request.LinkRequest;
import com.proto.linksaver.payload.response.CategoryResponse;
import com.proto.linksaver.payload.response.LinkResponse;
import com.proto.linksaver.repository.UserRepository;
import com.proto.linksaver.service.CategoryService;
import com.proto.linksaver.service.LinkService;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ChangeUnit(id = "createTestLink", order = "3", author = "Taha Bozdemir")
@RequiredArgsConstructor
public class TestLinkChangeUnit {
    private final UserRepository userRepository;
    private final LinkService linkService;
    private final CategoryService categoryService;
    private String linkId;
    private final CognitoProperties cognitoProperties;

    @Execution
    public void createTestLink() {
        String email = cognitoProperties.getTestUserEmail();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.ResourceNotFoundExceptionCodeEnum.ACCOUNT_NOT_FOUND));
        String userId = user.getId();
        List<CategoryResponse> categoryResponses = categoryService.getAll(userId);
        CategoryResponse categoryResponse = categoryResponses.get(0);
        LinkResponse linkResponse = linkService.create(new LinkRequest(userId, categoryResponse.id(), "Website", "https://www.bozdemir.net", false));
        linkId = linkResponse.id();
    }

    @RollbackExecution
    public void rollbackCreateTestUser() {
        if (linkId != null) {
            linkService.delete(linkId);
        }
    }
}
