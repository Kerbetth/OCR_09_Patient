package com.medic.mediscreen.service;

import com.medic.mediscreen.domain.Patient;
import com.medic.mediscreen.repositories.MicroService_mediscreen_patient;
import com.medic.mediscreen.repositories.Patient_Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * classic CRUD methods in order to managing de Patient table in the database
 */

@Service
@Slf4j
public class PatientService {

    @Autowired
    protected Patient_Repository patient_repository;

    public void addAPatient(Patient patient) {
        patient_repository.save(patient);
    }

    public void getAPatient(String given) {
        patient_repository.findByGiven(given);
    }
}