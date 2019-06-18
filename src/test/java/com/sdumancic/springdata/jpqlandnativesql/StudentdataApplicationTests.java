package com.sdumancic.springdata.jpqlandnativesql;

import com.sdumancic.springdata.jpqlandnativesql.entities.Student;
import com.sdumancic.springdata.jpqlandnativesql.repos.StudentRepository;
import com.sdumancic.springdata.product.entities.Product;
import com.sdumancic.springdata.product.repos.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentdataApplicationTests {

    @Autowired
    StudentRepository studentRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testCreateStudent(){
        Student s = new Student();
        s.setFirstName("Sanjin");
        s.setLastName("Dumančić");
        s.setScore(88);
        studentRepository.save(s);

        Student s1 = new Student();
        s1.setFirstName("Bill");
        s1.setLastName("Gates");
        s1.setScore(75);
        studentRepository.save(s1);
    }

    @Test
    public void testFindAllStudents(){
        studentRepository.findAllStudents(new PageRequest(1,2, Sort.Direction.ASC,"id")).forEach(student -> System.out.println(student));
    }

    @Test
    public void testFindAllStudentsNQ(){
        studentRepository.findAllStudentsNQ().forEach(student -> System.out.println(student));
    }

    @Test
    public void testFindAllStudentsPartialData(){
        List<Object[]> partialData = studentRepository.findAllStudentsPartialData();
        for (Object[] object : partialData){
            System.out.println(object[0] + "," + object[1]);
        }
    }

    @Test
    public void testFindAllStudentsByFirstName(){
        studentRepository.findAllStudentsByFirstName("Sanjin").forEach(student -> System.out.println(student));
    }

    @Test
    public void testFindAllStudentsByFirstNameNQ(){
        studentRepository.findByFirstNameNQ("Sanjin").forEach(student -> System.out.println(student));
    }

    @Test
    public void testFindAllStudentsForScores(){
        studentRepository.findAllStudentsForScores(80,90).forEach(student -> System.out.println(student));
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testDeleteAllStudentsByFirstName(){
        studentRepository.deleteStudentsByFirstName("Sanjin");
        studentRepository.deleteStudentsByFirstName("Bill");
    }

}
