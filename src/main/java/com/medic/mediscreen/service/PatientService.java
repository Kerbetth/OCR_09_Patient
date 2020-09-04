package com.medic.mediscreen.service;

import com.medic.mediscreen.domain.Patient;
import com.medic.mediscreen.repositories.PatientRepository;
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
    protected PatientRepository patientRepository;



    public Patient getAPatientById(int patId) {
        return patientRepository.findById(patId).get();
    }

    public Patient getPatientByFamilyName(String familyName) {
        return patientRepository.findByFamily(familyName).get();

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
        }
        else throw new NoSuchElementException();
    }

    public void deleteAPatient(int patientId) {
        Optional<Patient> patient = patientRepository.findById(patientId);
        if (patient.isPresent()) {
            patientRepository.delete(patient.get());
        }
        else throw new NoSuchElementException();
    }

}