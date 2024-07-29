package com.proto.linksaver.migrations.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "aws.cognito")
public class CognitoProperties {
    private String clientId;
    private String testUserEmail;
    private String testUserTemporaryPassword;
}
