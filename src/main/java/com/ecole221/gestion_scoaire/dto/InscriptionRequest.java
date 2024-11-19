package com.ecole221.gestion_scoaire.dto;

import lombok.Data;

@Data
public class InscriptionRequest {
    private InscriptionDto inscriptionDto;
    private EtudiantDto etudiantDto;
}
