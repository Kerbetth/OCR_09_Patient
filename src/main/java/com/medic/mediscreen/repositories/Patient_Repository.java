package com.medic.mediscreen.repositories;

import com.medic.mediscreen.domain.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;


@Repository
public interface Patient_Repository extends CrudRepository<Patient, Long> {
    Optional<Patient> findByFamily(String Family);
    Optional<Patient> findByPatId(int patId);
    List<Patient> findAll();
}
