package com.sdumancic.springdata.product.controllers;

import com.sdumancic.springdata.product.entities.Product;
import com.sdumancic.springdata.product.services.ProductResourceAssembler;
import com.sdumancic.springdata.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ExposesResourceFor(Product.class)
@RequestMapping("products")
public class ProductRestController {

    @Autowired
    private EntityLinks entityLinks;

    @Autowired
    ProductService productService;

    @Autowired
    private PagedResourcesAssembler<Product> pagedAssembler;

    @Autowired
    private ProductResourceAssembler productResourceAssembler;


    @GetMapping(value = "/{productId}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource<Product> getProductById(@PathVariable int productId) {
        Link selfLink = entityLinks.linkToSingleResource(Product.class, productId);
        Product product = productService.findById(productId);
        Resource<Product> resource = new Resource<>(product);
        resource.add(selfLink);
        return resource;
    }

    @RequestMapping( method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> findAll(Pageable pageRequest, PagedResourcesAssembler assembler) {
        Page<Product> page = productService.findAll(pageRequest);
        return ResponseEntity.ok(pagedAssembler.toResource(page, productResourceAssembler));

    }

}
