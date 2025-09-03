package com.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
	

}
