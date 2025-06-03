package com.prga.facenotebackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "clientTreatment")
public class ClientTreatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "id")
    private Integer id;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "treatmentType")
    private String treatmentType;

    @Column(name = "treatmentDescription")
    private String treatmentDescription;

    @Column(name = "usedProducts")
    private String usedProducts;

    @Column(name = "notesAndReactions")
    private String notesAndReactions;

    @Column(name = "image")
    private byte[] image;

    @JoinColumn(name = "clientId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Client clientId;



}
