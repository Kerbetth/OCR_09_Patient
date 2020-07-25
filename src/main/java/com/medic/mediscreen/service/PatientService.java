package com.medic.mediscreen.service;

import com.medic.mediscreen.domain.Patient;
import com.medic.mediscreen.repositories.Patient_Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public Patient getAPatient(int patId) {
        return patient_repository.findByPatId(patId).get();
    }

    public List<Patient> getPatients() {
        return patient_repository.findAll();
    }

    public void setAPatient(Patient newpatient) {
        if (patient_repository.findByPatId(newpatient.getPatId()).isPresent()) {
            patient_repository.save(newpatient);
        }
        else throw new NoSuchElementException();
    }


    public void deleteAPatient(int patientId) {
        Optional<Patient> patient = patient_repository.findByPatId(patientId);
        if (patient.isPresent()) {
            patient_repository.delete(patient.get());
        }
    }

    public int getPatientsIdByFamilyName(String familyName) {
        return patient_repository.findByFamily(familyName).get().getPatId();
    }
}