package com.medic.mediscreen.repositories;

import com.medic.mediscreen.domain.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {
    Optional<Patient> findByFamily(String Family);
    Optional<Patient> findById(int id);
    List<Patient> findAll();
}
