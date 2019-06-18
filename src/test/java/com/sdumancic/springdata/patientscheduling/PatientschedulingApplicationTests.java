package com.sdumancic.springdata.patientscheduling;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientschedulingApplicationTests {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Test
    public void testCreateDoctor(){
        Doctor doctor = new Doctor();
        doctor.setFirstName("Sanjin");
        doctor.setLastName("Dumančić");
        doctor.setSpeciality("All");
        doctorRepository.save(doctor);
    }

    @Test
    public void testCreatePatient(){
        Patient p1 = new Patient();
        p1.setFirstName("Patientname1");
        p1.setLastName("Patientlastname1");
        p1.setPhone("12345678");
        Insurance i1 = new Insurance();
        i1.setProviderName("Croatia Osig");
        i1.setCopay(20d);
        p1.setInsurance(i1);

        Doctor doctor = doctorRepository.findById(1L).get();
        List<Doctor> doctorList = Arrays.asList(doctor);
        p1.setDoctors(doctorList);

        patientRepository.save(p1);
    }

    @Test
    public void testCreateAppointment(){

        Appointment appointment = new Appointment();
        Timestamp ts = new Timestamp(new Date().getTime());
        appointment.setAppointmentTime(ts);
        appointment.setReason("I have a problem");
        appointment.setStarted(true);
        appointment.setPatient(patientRepository.findById(1L).get());
        appointment.setDoctor(doctorRepository.findById(1L).get());
        appointmentRepository.save(appointment);

    }
}

