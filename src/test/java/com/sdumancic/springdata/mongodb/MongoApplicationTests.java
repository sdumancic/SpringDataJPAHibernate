package com.sdumancic.springdata.mongodb;

import com.sdumancic.springdata.mongodb.model.MongoProduct;
import com.sdumancic.springdata.mongodb.repos.MongoProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoApplicationTests {

    @Autowired
    MongoProductRepository repository;

    @Test
    public void testSave(){
        MongoProduct product = new MongoProduct();
        product.setName("Apple IPAD 12.9");
        product.setPrice(1299);
        MongoProduct savedProduct = repository.save(product);
        assertNotNull(savedProduct);

    }
}
