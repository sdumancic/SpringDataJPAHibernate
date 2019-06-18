package com.sdumancic.springdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@SpringBootApplication
public class ProductdataApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductdataApplication.class, args);
    }

}
