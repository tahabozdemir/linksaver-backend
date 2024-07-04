package com.proto.linksaver.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LargePayloadException extends RuntimeException {
    private final LargePayloadExceptionCodeEnum errorCode;

    public enum LargePayloadExceptionCodeEnum {
        FILE_SIZE_EXCEEDED,
        REQUEST_BODY_TOO_LARGE,
        PAYLOAD_LIMIT_EXCEEDED
    }
}

