package org.csu.jpetstore.exception;

import org.springframework.http.HttpStatus;

public class ApiRequestException extends RuntimeException {
    private HttpStatus status;

    public ApiRequestException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }

    public ApiRequestException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpStatus getStatus() {
        return status;
    }
}
