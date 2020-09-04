package com.medic.mediscreen.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medic.mediscreen.domain.Patient;
import com.medic.mediscreen.repositories.PatientRepository;
import com.medic.mediscreen.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository patient_repository;

    @InjectMocks
    private PatientService patientService = new PatientService();


    List<Patient> patients = new ArrayList<>();
    Patient patient = new Patient();
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        patient.setId(1);
        patient.setGiven("test");
        patient.setSex('F');
        patient.setDob(LocalDate.EPOCH);
        patient.setAddress("address");
        patient.setFamily("family");
        patients.add(new Patient());

    }

    @Test
    public void getAllPatients() {
        when(patient_repository.findAll()).thenReturn(patients);
        assertThat(patientService.getPatients()).hasSize(1);
    }

    @Test
    public void getAPatientByName(){

        when(patient_repository.findByFamily(anyString())).thenReturn(java.util.Optional.ofNullable(patient));
        Patient patient1 =patientService.getPatientByFamilyName(anyString());
        assertThat(patient1.getAddress()).isEqualTo("address");
        assertThat(patient1.getFamily()).isEqualTo("family");
    }

    @Test
    public void getAPatientById() {
        when(patient_repository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(patient));
        Patient patient1 =patientService.getAPatientById(anyInt());
        assertThat(patient1.getAddress()).isEqualTo("address");
        assertThat(patient1.getFamily()).isEqualTo("family");
    }

    @Test
    public void addingAPatient() {
        patientService.addAPatient(patient);
    }

    @Test
    public void setAPatient() {
        when(patient_repository.findById(anyInt())).thenReturn(java.util.Optional.of(patient));
    }

    @Test
    public void delAPatient(){
        when(patient_repository.findById(anyInt())).thenReturn(java.util.Optional.of(patient));
    }

    @Test
    public void setAPatientWithWrongId() {
        when(patient_repository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class,
                ()->{
                    patientService.setAPatient(patient);
                });
    }

    @Test
    public void delAPatientWithWrongId(){
        when(patient_repository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class,
                ()->{
                    patientService.deleteAPatient(1);
                });
    }
}
