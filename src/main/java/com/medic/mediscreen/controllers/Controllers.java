package com.medic.mediscreen.controllers;


import com.medic.mediscreen.domain.Patient;
import com.medic.mediscreen.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class Controllers {

    @Autowired
    PatientService patientService;


    @GetMapping(value = "/Patients")
    List<Patient> getPatientList() {
        return patientService.getPatients();
    }

    @GetMapping(value = "/Patient/familyName")
    Patient getPatientByFamilyName(@RequestParam String familyName){
        return patientService.getPatientByFamilyName(familyName);
    }

    @GetMapping(value = "/Patient/id")
    Patient getPatient(@RequestParam int id) {
        return patientService.getAPatientById(id);
    }

    @PostMapping(value = "/Patient/add")
    void addAPatient(@RequestBody @Valid Patient patient) {
        patientService.addAPatient(patient);
    }

    @PutMapping(value = "/Patient/set")
    void setAPatient(@RequestBody @Valid Patient patient) {
        patientService.setAPatient(patient);
    }

    @DeleteMapping(value = "/Patient/del")
    void deleteAPatient(@RequestParam int patientId) {
        patientService.deleteAPatient(patientId);
    }
}
