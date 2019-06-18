package com.sdumancic.springdata.componentmapping;

import org.springframework.data.repository.CrudRepository;



public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
