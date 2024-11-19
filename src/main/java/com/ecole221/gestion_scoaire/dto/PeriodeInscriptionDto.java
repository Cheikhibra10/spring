package com.ecole221.gestion_scoaire.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PeriodeInscriptionDto {
    private int id;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private Boolean statut;

    private String anneeScolaire;
}
