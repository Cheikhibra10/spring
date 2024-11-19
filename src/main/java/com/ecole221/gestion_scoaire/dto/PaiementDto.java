package com.ecole221.gestion_scoaire.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaiementDto {
    private int id;
    private float mensualite;
    private float montantPaye;
    private String dernierMois;
    private LocalDate datePaiement;
    private LocalDate dateLimitePaiement;
    private int etudiantId;
    private int classeId;
}
