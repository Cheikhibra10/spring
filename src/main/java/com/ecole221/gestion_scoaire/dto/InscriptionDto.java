package com.ecole221.gestion_scoaire.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InscriptionDto {
    private int id;
    private LocalDate date;
    private String anneeScolaire;
    private Integer classeId;
    private Integer etudiantId;
}
