package com.sdumancic.springdata.transactionmanagement;

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
public class TransactionManagementApplicationTests {

    @Autowired
    EntityManager entityManager;

    @Autowired
    BankAccountService service;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testTransfer(){
        service.transfer(1000);
    }
}

