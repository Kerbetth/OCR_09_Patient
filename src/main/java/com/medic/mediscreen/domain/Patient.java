package com.medic.mediscreen.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PATIENT")
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @NotBlank
    String family;
    @NotBlank
    String given;
    @NotBlank
    LocalDate dob;
    @NotBlank
    char sex;
    @NotBlank
    String address;
    String phone;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "patient")
    private Set<PatHistory> patHistories;
}
