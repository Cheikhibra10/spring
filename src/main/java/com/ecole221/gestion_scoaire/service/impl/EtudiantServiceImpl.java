package com.ecole221.gestion_scoaire.service.impl;

import com.ecole221.gestion_scoaire.dto.EtudiantDto;
import com.ecole221.gestion_scoaire.mapper.EtudiantMapper;
import com.ecole221.gestion_scoaire.model.Etudiant;
import com.ecole221.gestion_scoaire.model.GenerateMatricule;
import com.ecole221.gestion_scoaire.repository.EtudiantRepository;
import com.ecole221.gestion_scoaire.repository.GenerateMaticuleRepository;
import com.ecole221.gestion_scoaire.service.EtudiantService;
import com.ecole221.gestion_scoaire.validator.Helper;
import com.ecole221.gestion_scoaire.validator.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EtudiantServiceImpl implements EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private GenerateMaticuleRepository generateMaticuleRepository;
    @Autowired
    private EtudiantMapper etudiantMapper;
    @Autowired
    private Helper helper;
    @Override
    public EtudiantDto saveEtudiant(EtudiantDto etudiantDto) {
        helper.validateEtudiantDto(etudiantDto);
        String matricule = Helper.generateMatricule(generateMaticuleRepository);
        Etudiant etudiant = etudiantMapper.etudiantDtoToEtudiantEntity(etudiantDto);
        etudiant.setMatricule(matricule);
        etudiant = etudiantRepository.save(etudiant);
        Helper.updateGenerateMatricule(generateMaticuleRepository);
        return etudiantMapper.etudiantEntityToEtudiantDto(etudiant);
    }

    @Override   
    public EtudiantDto getEtudiantByMatricule(String matricule) {
        Etudiant etudiant = etudiantRepository.findByMatricule(matricule)
                .orElseThrow(() -> new IllegalArgumentException("Etudiant avec le matricule " + matricule + " n'existe pas!"));
        return etudiantMapper.etudiantEntityToEtudiantDto(etudiant);
    }

    @Override
    public EtudiantDto getEtudiantById(int id) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Etudiant avec l' Id " + id + " n'existe pas!"));
        return etudiantMapper.etudiantEntityToEtudiantDto(etudiant);
    }

    @Override
    public List<EtudiantDto> getAllEtudiants() {
        List<Etudiant> etudiants = etudiantRepository.findAll();
        return etudiants.stream()
                .map(etudiantMapper::etudiantEntityToEtudiantDto)
                .collect(Collectors.toList());
    }

}
