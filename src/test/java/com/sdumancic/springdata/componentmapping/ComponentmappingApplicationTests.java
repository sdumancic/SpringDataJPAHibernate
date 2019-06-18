package com.sdumancic.springdata.componentmapping;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ComponentmappingApplicationTests {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void contextLoad(){

    }

    @Test
    public void testCreate(){
        Employee e = new Employee();
        e.setId(123);
        e.setName("Sanjin");
        Address a = new Address();
        a.setCity("Čakovec");
        a.setCountry("Croatia");
        a.setState("Croatia");
        a.setStreetaddress("Vukovarska 1");
        a.setZipcode("00385");
        e.setAddress(a);
        employeeRepository.save(e);

    }
}
