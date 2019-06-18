package com.sdumancic.springdata.hibernateinheritance;

import com.sdumancic.springdata.hibernateinheritance.entities.Check;
import com.sdumancic.springdata.hibernateinheritance.entities.CreditCard;
import com.sdumancic.springdata.hibernateinheritance.repos.PaymentRepository;
import com.sdumancic.springdata.product.repos.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HibernateInheritanceApplicationTests {


    @Autowired
    PaymentRepository paymentRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void createPayment(){
        CreditCard cc = new CreditCard();
        cc.setId(123);
        cc.setAmount(1000);
        cc.setCardnumber("1234567890");
        paymentRepository.save(cc);
    }

    @Test
    public void createCheckPayment(){
        Check cc = new Check();
        cc.setId(124);
        cc.setAmount(1000);
        cc.setChecknumber("1234567890");
        paymentRepository.save(cc);
    }

}
