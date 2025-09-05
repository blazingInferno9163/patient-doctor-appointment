package com.code.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.code.entity.Patient;
import com.code.exception.ResourceNotFoundException;

public interface IPatientService {
	List<Patient>findAllPatients();
	Optional<Patient>getPatientById(Long id);
	Patient createPatient(Patient patient);
	ResponseEntity<Patient> updatePatient(Long patientId,Patient patientDetails) throws ResourceNotFoundException;
	Map<String, Boolean> deletePatient (Long patientId);
	ResponseEntity<Patient> patchPatients(Long id, Map<String, Object> updates) throws ResourceNotFoundException;
//	List<Patient> getPatientsByName(String name, int page, int size);

//    Page<Patient> getPatientsByName(String name, int page, int size);
    List<Patient> findPatientsByNameAndAge(String name, int age, int size);

}
