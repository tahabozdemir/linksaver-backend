package com.proto.linksaver.migration;

import com.amazonaws.services.cognitoidp.model.SignUpResult;
import com.amazonaws.services.cognitoidp.model.UsernameExistsException;
import com.proto.linksaver.migration.properties.CognitoProperties;
import com.proto.linksaver.model.User;
import com.proto.linksaver.repository.UserRepository;
import com.proto.linksaver.service.CognitoService;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChangeUnit(id = "createTestUser", order = "1", author = "Taha Bozdemir")
@RequiredArgsConstructor
public class TestUserChangeUnit {
    private final CognitoProperties cognitoProperties;
    private String userSub;
    private final UserRepository userRepository;
    private final CognitoService cognitoService;

    @Execution
    public void createTestUser() {
        String email = cognitoProperties.getTestUserEmail();
        String tempPassword = cognitoProperties.getTestUserTemporaryPassword();
        try {
            SignUpResult result = cognitoService.createUser(email, tempPassword);
            userSub = result.getUserSub();
            userRepository.save(new User(userSub, email));
            log.info("Test user created successfully with userSub: {}", userSub);
        } catch (UsernameExistsException e) {
            log.warn("User with email {} already exists: {}", email, e.getMessage());
        } catch (Exception e) {
            log.error("Error creating test user with email {}: {}", email, e.getMessage(), e);
            throw e;
        }
    }

    @RollbackExecution
    public void rollbackCreateTestUser() {
        if (userSub != null) {
            try {
                userRepository.deleteById(userSub);
                log.info("Test user with userSub {} deleted successfully", userSub);
            } catch (Exception e) {
                log.error("Error deleting test user with userSub {}: {}", userSub, e.getMessage(), e);
                throw e;
            }
        } else {
            log.warn("No userSub found, skipping rollback deletion.");
        }
    }
}
