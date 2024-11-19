package com.ecole221.gestion_scoaire.service.impl;

import com.ecole221.gestion_scoaire.dto.ClasseDto;
import com.ecole221.gestion_scoaire.mapper.ClasseMapper;
import com.ecole221.gestion_scoaire.model.Classe;
import com.ecole221.gestion_scoaire.model.Filiere;
import com.ecole221.gestion_scoaire.repository.ClasseRepository;
import com.ecole221.gestion_scoaire.repository.FiliereRepository;
import com.ecole221.gestion_scoaire.service.ClasseService;
import com.ecole221.gestion_scoaire.validator.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ClasseServiceImpl implements ClasseService {

    @Autowired
    private ClasseRepository classeRepository;
    @Autowired
    private FiliereRepository filiereRepository;

    @Autowired
    private ClasseMapper classeMapper;
    @Autowired
    private Helper helper;
    @Override
    public ClasseDto save(ClasseDto classeDto)
    {
        if (classeRepository.existsByCodeAndLibelle(classeDto.getCode(), classeDto.getLibelle())) {
            throw new IllegalArgumentException("La classe " + classeDto.getLibelle() + " en " + classeDto.getCode() + " existe déjà");
        }
        helper.validateClasseDto(classeDto);

        Filiere filiere = helper.getFiliere(classeDto.getFiliereId());
        Classe classe = classeMapper.fromClasseDtoToEntity(classeDto, filiere);
        classe = classeRepository.save(classe);
        return classeMapper.fromClasseEntityToDto(classe);
    }

    @Override
    public List<ClasseDto> getAllClasses()
    {
        List<Classe> classes = classeRepository.findAll();
        return classes.stream()
                .map(classeMapper::fromClasseEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClasseDto getClasseById(int id)
    {
        Classe classe = classeRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("La classe avec l'id "+id+" n'existe pas"));
        return classeMapper.fromClasseEntityToDto(classe);
    }

    @Override
    public List<ClasseDto> getClasseByFiliereByLibelle(String libelle) {
        Filiere filiere = filiereRepository.findByLibelle(libelle)
                .orElseThrow(() -> new IllegalArgumentException("Filiere avec ce libelle n'existe pas: " + libelle));
        List<Classe> classes = classeRepository.findByFiliere(filiere);
        return classes.stream()
                .map(classeMapper::fromClasseEntityToDto)
                .collect(Collectors.toList());
    }

    public ClasseDto updateClasse(int id, ClasseDto classeDto) {
        // Check if the Classe exists
        Classe existingClasse = classeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Classe avec l'id " + id + " n'existe pas"));

        // Validate ClasseDto
        helper.validateClasseDto(classeDto);

        // Get Filiere entity based on filiereId
        Filiere filiere = helper.getFiliere(classeDto.getFiliereId());

        // Update the existing Classe entity
        existingClasse.setCode(classeDto.getCode());
        existingClasse.setLibelle(classeDto.getLibelle());
        existingClasse.setFraisInscription(classeDto.getFraisInscription());
        existingClasse.setMensualite(classeDto.getMensualite());
        existingClasse.setAutreFrais(classeDto.getAutreFrais());
        existingClasse.setFiliere(filiere);

        // Save the updated Classe
        Classe updatedClasse = classeRepository.save(existingClasse);

        // Return the updated DTO
        return classeMapper.fromClasseEntityToDto(updatedClasse);
    }
}
