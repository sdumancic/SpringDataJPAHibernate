package com.sdumancic.springdata.product.repos;

import com.sdumancic.springdata.product.entities.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface ProductRepository extends PagingAndSortingRepository<Product,Integer> {

    Page<Product> findAllByPrice(double price, Pageable pageable);


    List<Product> findByName(String name);

    List<Product> findByNameAndDesc(String name, String desc);
    List<Product> findByPriceGreaterThan(Double price);
    List<Product> findByDescContains(String desc);
    List<Product> findByPriceBetween(Double lowerPrice, Double higherPrice);
    List<Product> findByDescLike(String desc);
    List<Product> findByIdIn(List<Integer> ids);

}
