package com.target.assessment.myRetail.product.client;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Client Configuration for Redsky Feign client to add custom error decoder.
 *
 * @author ashutosh gupta
 */
@Configuration
public class RedskyClientConfiguration {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new RedskyErrorDecoder();
    }
}

