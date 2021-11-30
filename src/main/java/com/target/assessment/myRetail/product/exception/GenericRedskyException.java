package com.target.assessment.myRetail.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom Exception for generic error while calling Redsky API,
 *
 * @author ashutosh gupta
 */
@ResponseStatus(value = HttpStatus.FAILED_DEPENDENCY)
public class GenericRedskyException extends RuntimeException {

    public GenericRedskyException() {
        super();
    }
    public GenericRedskyException(String message, Throwable cause) {
        super(message, cause);
    }
    public GenericRedskyException(String message) {
        super(message);
    }
    public GenericRedskyException(Throwable cause) {
        super(cause);
    }
}
