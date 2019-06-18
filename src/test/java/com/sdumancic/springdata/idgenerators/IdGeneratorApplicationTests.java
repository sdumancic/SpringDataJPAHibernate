package com.sdumancic.springdata.idgenerators;

import com.sdumancic.springdata.idgenerators.entities.Employee;
import com.sdumancic.springdata.idgenerators.repos.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class IdGeneratorApplicationTests {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testCreateEmployee(){
        Employee e = new Employee();
        //e.setId(1L);
        e.setName("Sanjin");
        employeeRepository.save(e);
        System.out.println(e.getId().toString());
    }

}
