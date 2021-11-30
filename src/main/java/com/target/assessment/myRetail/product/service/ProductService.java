package com.target.assessment.myRetail.product.service;

import com.target.assessment.myRetail.product.client.RedskyFeignClient;
import com.target.assessment.myRetail.product.data.repository.ProductRepository;
import com.target.assessment.myRetail.product.domain.ProductBo;
import com.target.assessment.myRetail.product.dto.Price;
import com.target.assessment.myRetail.product.dto.ProductResponse;
import com.target.assessment.myRetail.product.dto.redsky.RedskyProductResponse;
import com.target.assessment.myRetail.product.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Backend Service class with main logic for API Endpoints.
 *
 * @author ashutosh gupta
 */
@Service
public class ProductService {

    @Autowired
    RedskyFeignClient redskyFeignClient;

    @Autowired
    ProductRepository productRepository;

    /**
     * Method to get product details for particular product Id.
     * This methord aggregates date from various sources.
     * It calls Redsky api to get general details about product like name, description. etc.
     * and pulls the product price from MongoDB datasource.
     * It combines data from both sources and then return it in a form the object of @{@link ProductResponse}
     *
     * @param productId : String - Id of Product for which details need to be fetched.
     * @return @{@link ProductResponse} - Product details.
     */
    public ProductResponse getProductById(String productId) {
        RedskyProductResponse redskyProduct = redskyFeignClient.getProductById(productId);
        ProductBo product = productRepository.findById(productId)
                .orElseThrow(new DataNotFoundException("Price Information is not Available for this product with Id  ["+ productId+"] in system"));
        ProductResponse productResponse = new ProductResponse.ProductResponseBuilder()
                .withId(redskyProduct.getData().getProduct().getTcin())
                .withName(redskyProduct.getData().getProduct().getItem().getProductDescription().getTitle())
                .withPrice(new Price.PriceBuilder()
                        .withValue(product.getCurrentPrice())
                        .withCurrencyCode(product.getCurrencyCode())
                        .build()
                )
                .build();

        return productResponse;
    }

    /**
     * Service method to update current Product price.
     * @param id : String - Product Id.
     * @param price : @{@link Price} - new Price detail of product.
     */
    public void updateProductPrice(String id, Price price) {
        ProductBo product = new ProductBo();
        product.setId(id);
        product.setCurrentPrice(price.getValue());
        product.setCurrencyCode(price.getCurrencyCode());

        productRepository.save(product);
    }
}
