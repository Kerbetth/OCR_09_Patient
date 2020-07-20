package com.medic.mediscreen.domain;


import javax.persistence.*;

public class PatHistory {
    Integer id;
    String riskLevel;
    String note;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId", referencedColumnName = "id")
    private Patient patient;
}
