package com.sdumancic.springdata.jpqlandnativesql.repos;

import com.sdumancic.springdata.jpqlandnativesql.entities.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student,Long> {

    @Query("from Student")
    List<Student> findAllStudents(Pageable pageable);

    @Query("select st.firstName, st.lastName from Student st")
    List<Object[]> findAllStudentsPartialData();

    @Query("from Student where firstName=:firstName")
    List<Student> findAllStudentsByFirstName(@Param("firstName") String firstName);

    @Query("from Student where score >:minScore and score <:maxScore")
    List<Student> findAllStudentsForScores(@Param("minScore") int minScore, @Param("maxScore") int maxScore);

    @Modifying
    @Query("delete from Student where firstName=:firstName")
    void deleteStudentsByFirstName(@Param("firstName") String firstName);

    @Query(nativeQuery = true, value="select * from student")
    List<Student> findAllStudentsNQ();

    @Query(nativeQuery = true, value="select * from student where fname=:firstName")
    List<Student> findByFirstNameNQ(@Param("firstName") String firstName);
}
