package com.target.assessment.myRetail.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

/**
 * Custom Exception for data not found in system,
 *
 * @author ashutosh gupta
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException implements Supplier<DataNotFoundException> {

    public DataNotFoundException() {
        super();
    }
    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public DataNotFoundException(String message) {
        super(message);
    }
    public DataNotFoundException(Throwable cause) {
        super(cause);
    }

    @Override
    public DataNotFoundException get() {
        return this;
    }
}
