package com.sdumancic.springdata.patientscheduling;

import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
}
