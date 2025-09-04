package com.code.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.code.entity.Patient;
import com.code.exception.ResourceNotFoundException;
import com.code.service.IPatientService;
import com.code.service.PatientService;

@RestController
@RequestMapping("/papi")
public class PatientController {
	
	@Autowired
	IPatientService patientService;
	
	@GetMapping("/patients")
	public List<Patient> getPatientList() {
		return patientService.findAllPatients();
	}

	@GetMapping("/patient/{id}")
	public Patient getPatient(@PathVariable Long id) throws ResourceNotFoundException{
		 Patient patient = patientService.getPatientById(id)
	        		.orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + id));
		return patient;
	 
	}

//	// GET /papi/patients?name=ashu&page=0&size=10
//	    @GetMapping(value = "/patients", params = "name")
//	    public ResponseEntity<Page<Patient>> getPatientsByName(
//	            @RequestParam String name,
//	            @RequestParam(defaultValue = "0") int page,
//	            @RequestParam(defaultValue = "10") int size) {
//
//	        Pageable pageable = PageRequest.of(page, size);
//	        Page<Patient> patients = patientService.findPatientsByName(name, pageable);
//	        return ResponseEntity.ok(patients);
//	    }

	
	@PostMapping("/patient")
	public ResponseEntity savePatient(@RequestBody Patient patient) {
		Map<String, Object> map = new HashMap<String, Object>();
		patientService.createPatient(patient);
		map.put("status", 1);
		map.put("message", "Record is Saved Successfully!");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
		 
		
	}
	

	 // PATCH /papi/patients/{id}
	@PatchMapping("/patient/{id}")
	public ResponseEntity<Patient> patchPatient(@PathVariable Long id, @RequestBody Map<String, Object> updates) throws ResourceNotFoundException {
	    return patientService.patchPatients(id, updates);
	}

	@PutMapping("/patient/{id}")
	public ResponseEntity<Patient>updatePatient(@PathVariable Long id,@RequestBody Patient patient) throws ResourceNotFoundException{
		return patientService.updatePatient(id,patient);
	}
	@DeleteMapping("/patient/{id}")
	public Map<String, Boolean> deletePatient(@PathVariable Long id){
		return patientService.deletePatient(id);
	}

@GetMapping("/papi/patients")
    public Page<Patient> getPatients(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return patientService.getPatientsByName(name, page, size);
    }

}	

