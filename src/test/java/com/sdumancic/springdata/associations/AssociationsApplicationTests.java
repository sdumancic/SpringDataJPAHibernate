package com.sdumancic.springdata.associations;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssociationsApplicationTests {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProgrammerRepository programmerRepository;

    @Autowired
    LicenseRepository licenseRepository;

    @Test
    public void contextLoad(){

    }

    @Test
    public void createCustomer(){
        Customer c1 = new Customer();
        c1.setName("Sanjin");
        Set<PhoneNumber> pn = new HashSet<>();
        PhoneNumber n1 = new PhoneNumber();
        n1.setPhonenumber("12345678");
        n1.setType("Mobile");



        PhoneNumber n2 = new PhoneNumber();
        n2.setPhonenumber("234235");
        n2.setType("Mobile");

        c1.addPhoneNumber(n1);
        c1.addPhoneNumber(n2);

        
        customerRepository.save(c1);
    }

    @Test
    @Transactional
    public void testLoadCustomer(){
        Optional<Customer> id = customerRepository.findById(4L);
        Customer customer = null;
        if (id.isPresent()) {
            customer = id.get();
            System.out.println(customer.getName());
            Set<PhoneNumber> numbers = customer.getNumbers();
            numbers.forEach(number -> System.out.println(number.getPhonenumber()));
        }
    }

    @Test
    public void testUpdateCustomer(){
        Optional<Customer> id = customerRepository.findById(4L);
        Customer customer = null;
        if (id.isPresent()) {
            customer = id.get();
            System.out.println(customer.getName());
            System.out.println("Setting name to Sanjin");
            customer.setName("Sanjin");

            Set<PhoneNumber> numbers = customer.getNumbers();
            numbers.forEach(number -> number.setType("cell"));

            customerRepository.save(customer);
        }
    }

    @Test
    public void testDeleteCustomer() {
        customerRepository.deleteById(3L);
    }

    @Test
    public void testSaveProgrammerProjects() {
        Programmer programmer = new Programmer();
        programmer.setName("Sanjin");
        programmer.setSal(10000);
        Set<Project> projects = new HashSet<>();
        Project p1 = new Project();
        p1.setName("Project1");
        projects.add(p1);
        programmer.setProjects(projects);

        programmerRepository.save(programmer);
    }

    @Test
    @Transactional
    public void testmtomFindProgrammer(){
        Programmer programmer = programmerRepository.findById(1).get();
        System.out.println(programmer.getName());
        System.out.println(programmer.getProjects());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testOne2OneCreateLicense(){
        License license = new License();
        license.setType("CAR");
        license.setValidFrom(new Date());
        license.setValidTo(new Date());
        Person p = new Person();
        p.setFirstName("SAnjin");
        p.setLastName("Dumančić");
        p.setAge(40);
        license.setPerson(p);
        licenseRepository.save(license);
    }


}
