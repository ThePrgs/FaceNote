package com.prga.facenotebackend.model;

import java.time.LocalDate;
import java.util.List;

public record ClientRecord(
        Integer id,
        String name,
        String lastName,
        LocalDate dateOfBirth,
        String gender,
        String phone,
        Boolean acuteMedicalIssues,
        String acuteMedicalIssuesDescription,
        Boolean chronicMedicalIssues,
        String chronicMedicalIssuesDescription,
        Boolean surgicalProcedures,
        String surgicalProceduresDescription,
        Boolean currentMedications,
        String currentMedicationsDescription,
        Boolean medicationAllergies,
        String medicationAllergiesDescription,
        List<String> skinTypes,
        List<String> skinConditions,
        String homeMedications
) {}
