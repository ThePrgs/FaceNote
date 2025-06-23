package com.prga.facenotebackend.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "client", schema = "public")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone")
    private String phone;

    @Column(name = "acuteMedicalIssues")
    private Boolean acuteMedicalIssues;

    @Column(name = "acuteMedicalIssuesDescription")
    private String acuteMedicalIssuesDescription;

    @Column(name = "chronicMedicalIssues")
    private Boolean chronicMedicalIssues;

    @Column(name = "chronicMedicalIssuesDescription")
    private String chronicMedicalIssuesDescription;

    @Column(name = "surgicalProcedures")
    private Boolean surgicalProcedures;

    @Column(name = "surgicalProceduresDescription")
    private String surgicalProceduresDescription;

    @Column(name = "currentMedications")
    private Boolean currentMedications;

    @Column(name = "currentMedicationsDescription")
    private String currentMedicationsDescription;

    @Column(name = "medicationAllergies")
    private Boolean medicationsAllergies;

    @Column(name = "medicationAllergiesDescription")
    private String medicationAllergiesDescription;

    @Column(name = "skinTypes")
    private String skinType;

    @Column(name = "skinConditions")
    private String skinCondition;

    @Column(name = "homeMedications")
    private String homeMedications;
}
