package com.target.assessment.myRetail.product.data.repository;

import com.target.assessment.myRetail.product.domain.ProductBo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Data Repository to get Product Document from MongoDB.
 *
 * @author ashutosh gupta
 */
@Repository
public interface ProductRepository extends MongoRepository<ProductBo, String> {

}
