package com.medic.mediscreen.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.medic.mediscreen.domain.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@Sql(scripts={"classpath:test_data_init.sql"})
public class PatientControllerITest {


	@Autowired
	MockMvc mockMvc;


    ObjectMapper objectMapper = new ObjectMapper();
    Patient patient = new Patient();

    @BeforeEach
    void setup() {
        patient.setId(1);
        patient.setSex('F');
        patient.setPhone("000");
        patient.setGiven("given");
        patient.setFamily("family");
        patient.setAddress("address");
        patient.setDob(LocalDate.EPOCH);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    public void getAllPatientsListForm() throws Exception {
       String result =mockMvc.perform(get("/Patients")
        )
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        ArrayList<Patient> patients = objectMapper.readValue(result, ArrayList.class);
        assertThat(patients).hasSize(10);
    }

    @Test
    public void getAPatientByName() throws Exception {
        mockMvc.perform(get("/Patient/familyName")
                .param("familyName", "Clark")
        )
                .andExpect(status().isOk());
    }

    @Test
    public void getAPatientById() throws Exception {

        mockMvc.perform(get("/Patient/id")
                .param("id","1")
        )
                .andExpect(status().isOk());
    }

	@Test
	public void addingAPatient() throws Exception {
        String json = objectMapper.writeValueAsString(patient);
        System.out.println(json);
		mockMvc.perform(post("/Patient/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
		)
				.andExpect(status().isOk());
	}

    @Test
    public void setAPatient() throws Exception {

        String json = objectMapper.writeValueAsString(patient);
        mockMvc.perform(post("/Patient/set")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .param("id", "1")
        )
                .andExpect(status().isOk());
    }

    @Test
    public void delAPatient() throws Exception {
        String json = objectMapper.writeValueAsString(patient);

        mockMvc.perform(post("/Patient/del")
                .contentType(MediaType.APPLICATION_JSON)
                .param("patientId", "1")
        )
                .andExpect(status().isOk());
    }
}
