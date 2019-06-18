package com.sdumancic.springdata.idgenerators.repos;

import com.sdumancic.springdata.idgenerators.entities.Employee;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {
}
