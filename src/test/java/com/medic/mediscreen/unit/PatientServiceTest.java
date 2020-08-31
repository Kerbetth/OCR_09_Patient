package com.medic.mediscreen.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medic.mediscreen.domain.PatHistory;
import com.medic.mediscreen.domain.Patient;
import com.medic.mediscreen.repositories.Patient_Repository;
import com.medic.mediscreen.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
public class PatientServiceTest {

    @Mock
    private Patient_Repository patient_repository;

    @InjectMocks
    private PatientService patientService = new PatientService();

    PatHistory patHistory = new PatHistory();
    List<Patient> patients = new ArrayList<>();
    Patient patient = new Patient();
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        patHistory.setId(1);
        patHistory.setPatient(new Patient());
        patHistory.setNote("a note");
        patients.add(new Patient());
    }

    @Test
    public void getAllPatients() {
        when(patient_repository.findAll()).thenReturn(patients);
        assertThat(patientService.getPatients()).hasSize(1);
    }

    @Test
    public void getAPatientByName(){
        patient.setId(2);
        patient.setAddress("address");
        patient.setFamily("family");
        when(patient_repository.findByFamily(anyString())).thenReturn(java.util.Optional.ofNullable(patient));
        Patient patient1 =patientService.getPatientByFamilyName(anyString());
        assertThat(patient1.getAddress()).isEqualTo("address");
        assertThat(patient1.getFamily()).isEqualTo("family");
        assertThat(patient1.getId()).isEqualTo(2);
    }

    @Test
    public void getAPatientById() {
        patient.setId(2);
        patient.setAddress("address");
        patient.setFamily("family");
        when(patient_repository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(patient));
        Patient patient1 =patientService.getAPatientById(anyInt());
        assertThat(patient1.getAddress()).isEqualTo("address");
        assertThat(patient1.getFamily()).isEqualTo("family");
        assertThat(patient1.getId()).isEqualTo(2);
    }

    @Test
    public void addingAPatient() {
        patient.setId(2);
        patient.setAddress("address");
        patient.setFamily("family");
        patientService.addAPatient(patient);
    }

    @Test
    public void setAPatient() {
        patient.setId(2);
        patient.setAddress("address");
        patient.setFamily("family");
        when(patient_repository.findById(anyInt())).thenReturn(java.util.Optional.of(patient));
    }

    @Test
    public void delAPatient(){
        patient.setId(2);
        patient.setAddress("address");
        patient.setFamily("family");
        when(patient_repository.findById(anyInt())).thenReturn(java.util.Optional.of(patient));
    }

    @Test
    public void setAPatientWithWrongId() {
        patient.setId(2);
        patient.setAddress("address");
        patient.setFamily("family");
        when(patient_repository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class,
                ()->{
                    patientService.setAPatient(patient);
                });
    }

    @Test
    public void delAPatientWithWrongId(){
        patient.setId(2);
        patient.setAddress("address");
        patient.setFamily("family");
        when(patient_repository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class,
                ()->{
                    patientService.deleteAPatient(1);
                });
    }
}
