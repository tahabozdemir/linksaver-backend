package com.proto.linksaver.migrations;

import com.amazonaws.services.cognitoidp.model.SignUpResult;
import com.proto.linksaver.migrations.properties.CognitoProperties;
import com.proto.linksaver.model.User;
import com.proto.linksaver.repository.UserRepository;
import com.proto.linksaver.service.CognitoService;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;

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
        SignUpResult result = cognitoService.createUser(email, tempPassword);
        userSub = result.getUserSub();
        userRepository.save(new User(result.getUserSub(), email));
    }

    @RollbackExecution
    public void rollbackCreateTestUser() {
        if (userSub != null) {
            userRepository.deleteById(userSub);
        }
    }
}


