package com.ecole221.gestion_scoaire.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float mensualite;
    private float montantPaye;
    private float montantRestant;
    private String dernierMois;
    private LocalDate datePaiement;
    private LocalDate dateLimitePaiement;

    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "classe_id")
    private Classe classe;
}
