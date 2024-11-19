package com.ecole221.gestion_scoaire.service;

import com.ecole221.gestion_scoaire.dto.ClasseDto;
import com.ecole221.gestion_scoaire.model.Filiere;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClasseService {

    ClasseDto save(ClasseDto classeDto);
    List<ClasseDto> getAllClasses();
    ClasseDto getClasseById(int id);
    List<ClasseDto> getClasseByFiliereByLibelle(String libelle);
    ClasseDto updateClasse(int id, ClasseDto classeDto);
}
