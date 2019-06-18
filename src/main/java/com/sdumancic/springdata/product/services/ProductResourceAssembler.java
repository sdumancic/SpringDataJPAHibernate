package com.sdumancic.springdata.product.services;

import com.sdumancic.springdata.product.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Service;

@Service
public class ProductResourceAssembler implements ResourceAssembler<Product, Resource<Product>> {

    @Autowired
    private EntityLinks entityLinks;

    @Override
    public Resource<Product> toResource(Product product) {
        Link self = entityLinks.linkFor(Product.class).slash(product.getId()).withSelfRel();
        Link rel = entityLinks.linkFor(Product.class).slash(product.getId()).withRel("product");
        return new Resource<>(product, self, rel);
    }

}
