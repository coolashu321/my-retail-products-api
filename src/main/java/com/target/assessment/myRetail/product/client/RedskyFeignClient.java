package com.target.assessment.myRetail.product.client;

import com.target.assessment.myRetail.product.dto.redsky.RedskyProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * OpenFeign client to connect to Redsky API.
 * url and apiKey of Redsky api is pulled from properties file(application.yaml)
 *
 * @author ashutosh gupta
 */
@FeignClient(value = "redsky", url = "${application.redsky.url}", configuration = RedskyClientConfiguration.class)
public interface RedskyFeignClient {

    /**
     * Redsky client method to get product details from Redsky
     * @param id : String - product Id
     * @return @{@link RedskyProductResponse} :  product details from Redsky API.
     */
    @RequestMapping(method = RequestMethod.GET,
            value = "/redsky_aggregations/v1/redsky/case_study_v1?key=${application.redsky.apiKey}"
    , consumes = "application/json")
    RedskyProductResponse getProductById(@RequestParam(name = "tcin") String id);
}
