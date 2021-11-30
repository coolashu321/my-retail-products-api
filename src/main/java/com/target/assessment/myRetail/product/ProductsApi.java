package com.target.assessment.myRetail.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Spring boot application main class.
 * @author ashutosh gupta
 */
@SpringBootApplication
@EnableFeignClients
public class ProductsApi {

	public static void main(String[] args) {
		SpringApplication.run(ProductsApi.class, args);
	}

}
