package com.sdumancic.springdata.hibernateinheritance.repos;

import com.sdumancic.springdata.hibernateinheritance.entities.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment,Integer> {
}
