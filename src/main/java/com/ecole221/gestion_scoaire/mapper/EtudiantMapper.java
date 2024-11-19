package com.ecole221.gestion_scoaire.mapper;

import com.ecole221.gestion_scoaire.dto.EtudiantDto;
import com.ecole221.gestion_scoaire.model.Etudiant;
import com.ecole221.gestion_scoaire.model.Inscription;
import org.springframework.stereotype.Component;

@Component
public class EtudiantMapper {

    public EtudiantDto etudiantEntityToEtudiantDto(Etudiant etudiant){
        return EtudiantDto.builder()
                .id(etudiant.getId())
                .matricule(etudiant.getMatricule())
                .nom(etudiant.getNom())
                .prenom(etudiant.getPrenom())
                .telephone(etudiant.getTelephone())
                .email(etudiant.getEmail())
                .dateNaissance(etudiant.getDateNaissance())
                .adresse(etudiant.getAdresse())
                .build();
    }

    public Etudiant etudiantDtoToEtudiantEntity(EtudiantDto etudiantDto){
        return Etudiant.builder()
                .id(etudiantDto.getId())
                .matricule(etudiantDto.getMatricule())
                .nom(etudiantDto.getNom())
                .prenom(etudiantDto.getPrenom())
                .telephone(etudiantDto.getTelephone())
                .email(etudiantDto.getEmail())
                .dateNaissance(etudiantDto.getDateNaissance())
                .adresse(etudiantDto.getAdresse())
                .build();
    }
}
