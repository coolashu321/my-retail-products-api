package com.target.assessment.myRetail.product.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.assessment.myRetail.product.exception.DataNotFoundException;
import com.target.assessment.myRetail.product.exception.GenericRedskyException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Custom error decoder for Redsky Feign client.
 *
 * @author ashutosh gupta
 */
public class RedskyErrorDecoder implements ErrorDecoder {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()){
            case 404:
                return new DataNotFoundException("Product Details are not found in Redsky API.");
            default:
                return new GenericRedskyException("Call to Redsky API failed Actual Response is : "+ response.reason());
        }
    }
}