package com.proto.linksaver.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
    private final ResourceNotFoundExceptionCodeEnum errorCode;

    public enum ResourceNotFoundExceptionCodeEnum {
        CATEGORY_NOT_FOUND,
        LINK_NOT_FOUND,
        ACCOUNT_NOT_FOUND
    }
}
