package com.target.assessment.myRetail.product.api.v1;


import com.target.assessment.myRetail.product.dto.Price;
import com.target.assessment.myRetail.product.dto.ProductResponse;
import com.target.assessment.myRetail.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Controller class containing various endpoint for products api
 *
 * @author ashutosh gupta
 */
@RestController
@RequestMapping("/api/v1/product")
public class ProductResource {

    @Autowired
    ProductService productService;

    /**
     * Products API endpoint to get product details for given product Id.
     * Uses HTTP GET Method.
     *
     * @param productId : String - ID of product for which product details need to be fetched.
     * @return @{@link ProductResponse} :  details of product in json format.
     */
    @GetMapping("/{productId}")
    public ProductResponse getProductById(@PathVariable String productId) {
        return productService.getProductById(productId);
    }


    /**
     * Products API endpoint to update price of the product.
     * Uses HTTP PUT Method.
     *
     * @param productId : String - Id of product whose price need to be updated.
     * @param price : @{@link Price} - New price information.
     */
    @PutMapping("/{productId}")
    public void updateProductPrice(@PathVariable String productId, @RequestBody Price price) {
        productService.updateProductPrice(productId, price);
    }

}
