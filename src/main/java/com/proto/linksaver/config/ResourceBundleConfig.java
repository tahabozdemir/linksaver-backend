package com.proto.linksaver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ResourceBundle;

@Configuration
public class ResourceBundleConfig {
    @Bean
    public ResourceBundle messageBundle() {
        return ResourceBundle.getBundle("errors");
    }
}
