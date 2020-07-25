package com.medic.mediscreen.controllers;


import com.medic.mediscreen.domain.Patient;
import com.medic.mediscreen.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * -the root of the url give link to login or create an account
 * -userHome url is the main page of connected users
 */

@Controller
public class controllers {

    @Autowired
    PatientService patientService;


    @GetMapping(value = "/Patients")
    List<Patient> getPatientList() {
        return patientService.getPatients();
    }

    @GetMapping(value = "/Patients/{familyName}")
    int getPatientIdByFamilyName(@PathVariable("familyName") String familyName){
        return patientService.getPatientsIdByFamilyName(familyName);
    }

    @GetMapping(value = "/Patient/{id}")
    Patient getPatient(@PathVariable("id") int id) {
        return patientService.getAPatient(id);
    }

    @PostMapping(value = "/Patient/add")
    void addAPatient(@RequestBody Patient patient) {
        patientService.addAPatient(patient);
    }

    @PutMapping(value = "/Patient/set")
    void setAPatient(@RequestBody Patient patient) {
        patientService.setAPatient(patient);
    }

    @DeleteMapping(value = "/Patient/del")
    void deleteAPatient(@RequestParam int patientId) {
        patientService.deleteAPatient(patientId);
    }
}
