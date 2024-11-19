package com.ecole221.gestion_scoaire.service;


import com.ecole221.gestion_scoaire.dto.EtudiantDto;
import com.ecole221.gestion_scoaire.dto.InscriptionDto;
import com.ecole221.gestion_scoaire.model.Etudiant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InscriptionService {

    InscriptionDto save(InscriptionDto inscriptionDto, EtudiantDto etudiantDto);
    List<InscriptionDto> getAllInscriptionByClasse(String libelle);
    InscriptionDto getInscriptionByEtudiant(String matricule);
    List<InscriptionDto> list();
}
