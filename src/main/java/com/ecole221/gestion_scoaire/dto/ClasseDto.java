package com.ecole221.gestion_scoaire.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClasseDto {
    private int id;
    private String code;
    private String libelle;
    private String fraisInscription;
    private String mensualite;
    private String autreFrais;
    private int filiereId;


}
