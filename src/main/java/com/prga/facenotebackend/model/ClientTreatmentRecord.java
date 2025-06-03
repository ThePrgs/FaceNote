package com.prga.facenotebackend.model;

import java.time.LocalDateTime;

public record ClientTreatmentRecord(
        LocalDateTime date,
        String treatmentType,
        String treatmentDescription,
        String usedProducts,
        String notesAndReactions,
        Integer id,
        Integer clientId,
        String image) {
}

