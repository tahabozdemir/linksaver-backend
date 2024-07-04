package com.proto.linksaver.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UnauthorizedException extends RuntimeException {
    private final UnauthorizedExceptionCodeEnum errorCode;

    public enum UnauthorizedExceptionCodeEnum {
        INVALID_CREDENTIALS,
        TOKEN_EXPIRED,
        INSUFFICIENT_PERMISSIONS,
        ACCOUNT_DISABLED,
        IP_ADDRESS_BLOCKED,
        TWO_FACTOR_AUTHENTICATION_REQUIRED
    }
}
