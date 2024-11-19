package com.ecole221.gestion_scoaire.service;

import com.ecole221.gestion_scoaire.dto.EtudiantDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EtudiantService {
    EtudiantDto saveEtudiant(EtudiantDto etudiantDto);
    EtudiantDto getEtudiantByMatricule(String matricule);
    EtudiantDto getEtudiantById(int id);
    List<EtudiantDto> getAllEtudiants();
}
