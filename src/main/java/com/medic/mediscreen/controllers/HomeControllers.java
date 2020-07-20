package com.medic.mediscreen.controllers;


import com.medic.mediscreen.domain.Patient;
import com.medic.mediscreen.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * -the root of the url give link to login or create an account
 * -userHome url is the main page of connected users
 */

@Controller
public class HomeControllers {
@Autowired
    PatientService patientService;


    @RequestMapping("/")
    public String getLog(Model model) {
        return "LogPage";
    }


    @PostMapping("/patient/add")
    public String userPage(@RequestBody Patient patient) {
        patientService.addAPatient(patient);
        return "UserPage";
    }

}
