package com.code.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.code.entity.Patient;
import com.code.exception.ResourceNotFoundException;
import com.code.repository.PatientRepository;

@Service
public class PatientService implements IPatientService {
	
	@Autowired
	PatientRepository patientRepo;

	@Override
	public List<Patient> findAllPatients() {
		return patientRepo.findAll();
	}
	@Override
	public Optional<Patient>getPatientById(Long id){
		return patientRepo.findById(id);
	}
	
	@Override
	public Patient createPatient(Patient patient) {
		// TODO Auto-generated method stub
		return patientRepo.save(patient);
	}
	
	public ResponseEntity<Patient> updatePatient(Long patientId, Patient newPatient) throws ResourceNotFoundException {
	    Patient patient = patientRepo.findById(patientId)
	        .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));

	    patient.setFullName(newPatient.getFullName());
	    patient.setEmail(newPatient.getEmail());
	    patient.setPhone(newPatient.getPhone());
	    patient.setDob(newPatient.getDob());

	    patientRepo.save(patient);
	    return ResponseEntity.ok(patient);
	}
	
	@Override
	public ResponseEntity<Patient> patchPatients(Long id, Map<String, Object> updates) throws ResourceNotFoundException {
	    Patient patient = patientRepo.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + id));

	    updates.forEach((key, value) -> {
	        switch (key) {
	            case "fullName":
	                patient.setFullName((String) value);
	                break;
	            case "email":
	                patient.setEmail((String) value);
	                break;
	            case "phone":
	                patient.setPhone((String) value);
	                break;
	            case "dob":
	                patient.setDob(LocalDate.parse((String) value, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	                break;
	        }
	    });

	    patientRepo.save(patient);
	    return ResponseEntity.ok(patient);
	}

	 public Page<Patient> getPatientsByName(String name, int page, int size) {
	        return patientRepo.findByFullNameContainingIgnoreCase(name, PageRequest.of(page, size));
	    }

	
	public Map<String, Boolean> deletePatient (Long patientId){
    	patientRepo.deleteById(patientId);
    	Map<String, Boolean> map = new HashMap<>();
    	map.put("Deleted Successfully", true);
    	return map;
    }
}
