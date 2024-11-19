package com.ecole221.gestion_scoaire.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EtudiantDto {
    private int id;
    private String matricule;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private LocalDate dateNaissance;
    private String adresse;
}
