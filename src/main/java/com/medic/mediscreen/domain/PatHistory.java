package com.medic.mediscreen.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String note;

    @NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pat_Id", referencedColumnName="id")
    private Patient patient;
}
