package com.ecole221.gestion_scoaire.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FiliereDto {
    private int id;
    private String libelle;
}
