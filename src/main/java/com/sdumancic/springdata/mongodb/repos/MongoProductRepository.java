package com.sdumancic.springdata.mongodb.repos;

import com.sdumancic.springdata.mongodb.model.MongoProduct;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoProductRepository extends MongoRepository<MongoProduct, String> {
}
