package com.code.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public Map<String, Boolean> deletePatient (Long patientId){
    	patientRepo.deleteById(patientId);
    	Map<String, Boolean> map = new HashMap<>();
    	map.put("Deleted Successfully", true);
    	return map;
    }
}
