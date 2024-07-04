package com.proto.linksaver.exception;

import com.proto.linksaver.payload.response.BaseResponse;
import com.proto.linksaver.service.I18NService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final I18NService i18NService;

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseResponse handleCustomException(ResourceNotFoundException ex) {
        String errorMessage = i18NService.getMessage(ex.getErrorCode());
        final ExceptionInfo exceptionInfo = ExceptionInfo.builder()
                .errorCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .type("NOT_FOUND")
                .message(errorMessage)
                .build();
        return new BaseResponse(false, exceptionInfo);
    }

    @ExceptionHandler(LargePayloadException.class)
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    public BaseResponse handleLargePayloadException(LargePayloadException ex) {
        String errorMessage = i18NService.getMessage(ex.getErrorCode());
        final ExceptionInfo exceptionInfo = ExceptionInfo.builder()
                .errorCode(String.valueOf(HttpStatus.PAYLOAD_TOO_LARGE.value()))
                .message(errorMessage)
                .build();
        return new BaseResponse(false, exceptionInfo);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public BaseResponse handleBadRequestException(UnauthorizedException ex) {
        String errorMessage = i18NService.getMessage(ex.getErrorCode());
        final ExceptionInfo exceptionInfo = ExceptionInfo.builder()
                .errorCode(String.valueOf(HttpStatus.UNAUTHORIZED.value()))
                .message(errorMessage)
                .build();
        return new BaseResponse(false, exceptionInfo);
    }
}