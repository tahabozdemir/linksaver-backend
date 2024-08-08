package com.proto.linksaver.processor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.util.FileCopyUtils;

public class DockerSecretProcessor implements EnvironmentPostProcessor {
    private static final String DOCKER_SECRET_BIND_PATH_PROPERTY = "docker-secret.bind-path";
    private static final String DOCKER_SECRET_PREFIX = "docker-secret-";
    private static final String DOCKER_SECRETS_PROPERTY_SOURCE_NAME = "docker-secrets";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String bindPathProperty = environment.getProperty(DOCKER_SECRET_BIND_PATH_PROPERTY);

        if (bindPathProperty != null) {
            Path bindPath = Paths.get(bindPathProperty);
            if (Files.isDirectory(bindPath)) {
                try {
                    Map<String, Object> dockerSecrets = loadDockerSecrets(bindPath);
                    addDockerSecretsToEnvironment(environment, dockerSecrets);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to load Docker secrets", e);
                }
            }
        }
    }

    private Map<String, Object> loadDockerSecrets(Path bindPath) throws IOException {
        return Files.list(bindPath)
                .collect(Collectors.toMap(
                        path -> DOCKER_SECRET_PREFIX + path.getFileName().toString(),
                        this::readFileContent
                ));
    }

    private String readFileContent(Path path) {
        try {
            byte[] content = FileCopyUtils.copyToByteArray(path.toFile());
            return new String(content);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read Docker secret file: " + path, e);
        }
    }


    private void addDockerSecretsToEnvironment(ConfigurableEnvironment environment, Map<String, Object> dockerSecrets) {
        MapPropertySource propertySource = new MapPropertySource(DOCKER_SECRETS_PROPERTY_SOURCE_NAME, dockerSecrets);
        environment.getPropertySources().addLast(propertySource);
    }
}
