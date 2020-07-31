package com.medic.mediscreen.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PATHISTORY")
@EqualsAndHashCode(of = "id")
public class PatHistory {
    @Id
    @GeneratedValue
    Integer id;
    String note;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "patId")
    @NotNull
    private Patient patient;
}
