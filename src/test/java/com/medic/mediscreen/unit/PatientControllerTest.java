package com.medic.mediscreen.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medic.mediscreen.domain.Patient;
import com.medic.mediscreen.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
public class PatientControllerTest {

    @MockBean
    private PatientService patientService;

	@Autowired
	MockMvc mockMvc;


    List<Patient> patients =new ArrayList<>();
    Patient patient = new Patient();
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        patients.add(patient);
    }

    @Test
    public void getAllPatientsListForm() throws Exception {
        when(patientService.getPatients()).thenReturn(patients);
        mockMvc.perform(get("/Patients")
        )
                .andExpect(status().isOk());
    }

    @Test
    public void getAPatientByName() throws Exception {
        when(patientService.getPatientByFamilyName(anyString())).thenReturn(patient);
        mockMvc.perform(get("/Patient/familyName")
                .param("familyName","name")
        )
                .andExpect(status().isOk());
    }

    @Test
    public void getAPatientById() throws Exception {
        when(patientService.getAPatientById(anyInt())).thenReturn(patient);
        mockMvc.perform(get("/Patient/id")
                .param("id", "1")
        )
                .andExpect(status().isOk());
    }

	@Test
	public void addingAPatient() throws Exception {
        String json = "{\"family\": \"family\", \"given\": \"given\", \"dob\":\"2000-02-04\",\"sex\":\"M\"}";
		mockMvc.perform(post("/Patient/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
		)
				.andExpect(status().isOk());
	}

    @Test
    public void setAPatient() throws Exception {
        String json = "{\"family\": \"family\", \"given\": \"given\", \"dob\":\"2000-02-04\",\"sex\":\"M\"}";
        mockMvc.perform(put("/Patient/set")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void delAPatient() throws Exception {
        mockMvc.perform(delete("/Patient/del")
                .contentType(MediaType.APPLICATION_JSON)
                .param("patientId", "1")
        )
                .andExpect(status().isOk());
    }
}
