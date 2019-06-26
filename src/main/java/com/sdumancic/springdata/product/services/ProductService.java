package com.sdumancic.springdata.product.services;

import com.sdumancic.springdata.product.entities.Product;
import com.sdumancic.springdata.product.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public Page<Product> findAllByPrice(double price, Pageable pageable){
        final Page<Product> allByPrice = repository.findAllByPrice(price, pageable);
        return allByPrice;
    }

    public Page<Product> findAll(Pageable pageable){
        final Page<Product> allProducts = repository.findAll(pageable);
        return allProducts;
    }

    public Product findById(int id){
        return repository.findById(id).get();
    }
}
