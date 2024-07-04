package com.proto.linksaver.payload.response;

import com.proto.linksaver.exception.ExceptionInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private T data;
    private boolean success = true;
    private ExceptionInfo error;

    public BaseResponse(T data) {
        this.data = data;
    }

    public BaseResponse(T data, boolean success) {
        this.data = data;
        this.success = success;
    }

    public BaseResponse(boolean success, ExceptionInfo error) {
        this.success = success;
        this.error = error;
    }
}