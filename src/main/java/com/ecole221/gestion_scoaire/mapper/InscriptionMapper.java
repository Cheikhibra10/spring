package com.ecole221.gestion_scoaire.mapper;


import com.ecole221.gestion_scoaire.dto.InscriptionDto;
import com.ecole221.gestion_scoaire.model.Classe;
import com.ecole221.gestion_scoaire.model.Etudiant;
import com.ecole221.gestion_scoaire.model.Inscription;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InscriptionMapper {

    public static InscriptionDto fromInscriptionEntityToDto(Inscription inscription)
    {
        return InscriptionDto.builder()
                .id(inscription.getId())
                .date(inscription.getDate())
                .anneeScolaire(inscription.getAnneeScolaire())
                .classeId(inscription.getClasse().getId())
                .etudiantId(inscription.getEtudiant().getId())
                .build();
    }

    public Inscription fromInscriptionDtoToEntity(InscriptionDto inscriptionDto, Classe classe, Etudiant etudiant) {
        if (etudiant == null) {
            throw new IllegalArgumentException("Etudiant cannot be null");
        }
        return Inscription.builder()
                .id(inscriptionDto.getId())
                .date(inscriptionDto.getDate())
                .anneeScolaire(inscriptionDto.getAnneeScolaire())
                .classe(classe)
                .etudiant(etudiant)
                .build();
    }

}
