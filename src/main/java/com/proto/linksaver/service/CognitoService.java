package com.proto.linksaver.service;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CognitoService {
    private final AWSCognitoIdentityProvider cognitoClient;
    private final String clientId;

    public CognitoService(@Value("${aws.cognito.client-id}") String clientId,
                          @Value("${aws.region}") String region) {
        this.clientId = clientId;
        this.cognitoClient = AWSCognitoIdentityProviderClientBuilder.standard()
                .withRegion(region)
                .build();
    }

    public SignUpResult createUser(String email, String password) {
        SignUpRequest request = new SignUpRequest()
                .withClientId(clientId)
                .withUsername(email)
                .withPassword(password);

        return cognitoClient.signUp(request);
    }
}
