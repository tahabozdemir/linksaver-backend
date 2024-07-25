package com.proto.linksaver.config;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

public class SpaWebResourceResolver extends PathResourceResolver {

    @Override
    protected Resource getResource(String resourcePath, Resource location) throws IOException {
        Resource requestedResource = location.createRelative(resourcePath);
        return requestedResource.exists() && requestedResource.isReadable() ? requestedResource : new ClassPathResource("/static/index.html");
    }
}
