package com.medic.mediscreen.service;

import com.medic.mediscreen.domain.Patient;
import com.medic.mediscreen.exceptions.PatientNotFoundException;
import com.medic.mediscreen.repositories.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    protected PatientRepository patientRepository;


    public Patient getAPatientById(int patId) {
        Optional<Patient> patientOptional = patientRepository.findById(patId);
        if (patientOptional.isPresent()) {
            return patientOptional.get();
        } else {
            log.info("No patient found with the id: "+patId);
            throw new PatientNotFoundException("No patient found with this id");
        }
    }

    public Patient getPatientByFamilyName(String familyName) {
        Optional<Patient> patientOptional = patientRepository.findByFamily(familyName);
        if (patientOptional.isPresent()) {
            return patientOptional.get();
        } else {
            log.info("No patient found with the family Name: "+familyName);
            throw new PatientNotFoundException("No patient found with this family Name");
        }
    }

    public List<Patient> getPatients() {

        return patientRepository.findAll();
    }

    public void addAPatient(Patient Patient) {
        patientRepository.save(Patient);
    }

    public void setAPatient(Patient patient) {
        Optional<Patient> patientOptional = patientRepository.findById(patient.getId());
        if (patientOptional.isPresent()) {
            patientRepository.save(patient);
        } else {
            log.info("No patient found with the id: "+patient.getId());
            throw new PatientNotFoundException("No patient found with this id");
        }
    }

    public void deleteAPatient(int patientId) {
        Optional<Patient> patient = patientRepository.findById(patientId);
        if (patient.isPresent()) {
            patientRepository.delete(patient.get());
        } else {
            log.info("No patient found with the id: "+patientId);
            throw new PatientNotFoundException("No patient found with this id");
        }
    }

}