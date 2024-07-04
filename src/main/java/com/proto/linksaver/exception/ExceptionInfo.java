package com.proto.linksaver.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionInfo {
    private String type;
    private String errorCode;
    private String message;
}