package com.ecole221.gestion_scoaire.service.impl;

import com.ecole221.gestion_scoaire.dto.FiliereDto;
import com.ecole221.gestion_scoaire.mapper.FiliereMapper;
import com.ecole221.gestion_scoaire.model.Classe;
import com.ecole221.gestion_scoaire.model.Filiere;
import com.ecole221.gestion_scoaire.repository.FiliereRepository;
import com.ecole221.gestion_scoaire.service.FiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FiliereServiceImpl implements FiliereService {

    @Autowired
    private FiliereRepository filiereRepository;

    @Autowired
    private FiliereMapper filiereMapper;

    public FiliereDto save(FiliereDto filiereDto){
        if(filiereRepository.existsByLibelle(filiereDto.getLibelle())){
            throw new IllegalArgumentException("La filiere " + filiereDto.getLibelle() + " existe déjà");
        }
        Filiere filiere = filiereMapper.fromFiliereDtoToEntity(filiereDto);
        filiere = filiereRepository.save(filiere);
        return filiereMapper.fromFiliiereEntityToDto(filiere);
    }

    public List<FiliereDto>  list(){
        List<Filiere> filieres = filiereRepository.findAll();
        return filieres.stream()
                .map(filiereMapper::fromFiliiereEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public FiliereDto update(int id, FiliereDto filiereDto) {
        Filiere existingFiliere = filiereRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Filiere avec l'id " + id + " n'existe pas"));

        if (filiereRepository.existsByLibelle(filiereDto.getLibelle()) && !existingFiliere.getLibelle().equals(filiereDto.getLibelle())) {
            throw new IllegalArgumentException("La filiere " + filiereDto.getLibelle() + " existe déjà");
        }

        existingFiliere.setLibelle(filiereDto.getLibelle());

        Filiere updatedFiliere = filiereRepository.save(existingFiliere);

        return filiereMapper.fromFiliiereEntityToDto(updatedFiliere);
    }

    @Override
    public FiliereDto getFiliereById(int id) {
        Filiere filiere = filiereRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("La filiere avec l'id "+id+" n'existe pas"));
        return filiereMapper.fromFiliiereEntityToDto(filiere);
    }
}
