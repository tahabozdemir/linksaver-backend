package com.proto.linksaver.migrations;

import com.proto.linksaver.exception.ResourceNotFoundException;
import com.proto.linksaver.migrations.properties.CognitoProperties;
import com.proto.linksaver.model.User;
import com.proto.linksaver.payload.request.CategoryRequest;
import com.proto.linksaver.payload.response.CategoryResponse;
import com.proto.linksaver.repository.UserRepository;
import com.proto.linksaver.service.CategoryService;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;

@ChangeUnit(id = "createTestCategory", order = "2", author = "Taha Bozdemir")
@RequiredArgsConstructor
public class TestCategoryChangeUnit {
    private final CognitoProperties cognitoProperties;
    private final UserRepository userRepository;
    private final CategoryService categoryService;
    private String categoryId;
    private String userId;

    @Execution
    public void createTestCategory() {
        String email = cognitoProperties.getTestUserEmail();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.ResourceNotFoundExceptionCodeEnum.ACCOUNT_NOT_FOUND));
        CategoryResponse category = categoryService.create(new CategoryRequest(user.getId(),"Home","home"));
        categoryId = category.id();
        userId = user.getId();
    }

    @RollbackExecution
    public void rollbackCreateTestUser() {
        if (categoryId != null) {
            categoryService.delete(userId, categoryId);
        }
    }
}
