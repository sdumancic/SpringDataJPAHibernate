package com.sdumancic.springdata.product;

import com.sdumancic.springdata.product.entities.Product;
import com.sdumancic.springdata.product.repos.ProductRepository;
import net.sf.ehcache.CacheManager;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableCaching
public class ProductdataApplicationTests {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testCreate(){
        Product p = new Product();
        p.setId(1);
        p.setName("Test MongoProduct");
        p.setDesc("This is product description");
        p.setPrice(123.23d);
        productRepository.save(p);
    }

    @Test
    public void testRead(){
        Optional<Product> byId = productRepository.findById(1);
        Product p = null;
        if (byId.isPresent())
            p= byId.get();
        assertNotNull(p);
        assertEquals("Test MongoProduct",p.getName());
    }

    @Test
    public void testUpdate(){
        Optional<Product> byId = productRepository.findById(1);
        Product p = null;
        if (byId.isPresent())
            p= byId.get();
        p.setPrice(1200d);
        productRepository.save(p);
        assertNotNull(p);
        assertEquals("Test MongoProduct",p.getName());
    }

    @Test
    public void testDelete(){
        if (productRepository.existsById(1))
            productRepository.deleteById(1);

    }


    @Test
    public void testFindByName() {
        List<Product> products = productRepository.findByName("Apple IPAD");
        products.forEach(p -> System.out.println("Price = " + p.getPrice()));
    }

    @Test
    public void testFindByNameAndDesc(){
        List<Product> products = productRepository.findByNameAndDesc("Apple IPAD","Ipad 9.7 PRO");
        products.forEach(p -> System.out.println("Price = " + p.getPrice()));
    }

    @Test
    public void testFindByPriceGreaterThen(){
        List<Product> products = productRepository.findByPriceGreaterThan(10d);
        products.forEach(p -> System.out.println("Price = " + p.getPrice()));
    }

    @Test
    public void testFindByDescContains(){
        List<Product> products = productRepository.findByDescContains("Apple");
        products.forEach(p -> System.out.println("Price = " + p.getName()));
    }

    @Test
    public void testFindByPriceBetween(){
        List<Product> products = productRepository.findByPriceBetween(8d,12d);
        products.forEach(p -> System.out.println("Price = " + p.getName()));
    }

    @Test
    public void testFindByDescLike(){
        List<Product> products = productRepository.findByDescLike("This%");
        products.forEach(p -> System.out.println("Price = " + p.getName()));
    }

    @Test
    public void testFindIdIn(){
        List<Product> products = productRepository.findByIdIn(Arrays.asList(-1,-2,-3));
        products.forEach(p -> System.out.println("Price = " + p.getName()));
    }

    @Test
    public void testFindAllPaging(){
        Pageable pageable = PageRequest.of(1,3, Sort.by("name","desc"));

        Page<Product> products = productRepository.findAll(pageable);
        products.forEach(p -> System.out.println("Name = " + p.getName() + " Desc = " + p.getName()));
    }

    @Test
    @Transactional
    public void testCaching(){
        Session session = entityManager.unwrap(Session.class);

        Product product = productRepository.findById(-1).get();
        int size= CacheManager.ALL_CACHE_MANAGERS.get(0).getCache("com.sdumancic.springdata.product.entities.MongoProduct").getSize();
        assertThat(size, greaterThan(0));
        System.out.println("Cache size=" + size);
        productRepository.findById(-1).get();
        System.out.println("Cache size=" + size);
        session.evict(product);
        System.out.println("Cache size=" + size);
        productRepository.findById(-1).get();
        System.out.println("Cache size=" + size);
    }
}

