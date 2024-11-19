package com.ecole221.gestion_scoaire.service.impl;

import com.ecole221.gestion_scoaire.dto.EtudiantDto;
import com.ecole221.gestion_scoaire.dto.InscriptionDto;
import com.ecole221.gestion_scoaire.mapper.EtudiantMapper;
import com.ecole221.gestion_scoaire.mapper.InscriptionMapper;
import com.ecole221.gestion_scoaire.model.Classe;
import com.ecole221.gestion_scoaire.model.Etudiant;
import com.ecole221.gestion_scoaire.model.Inscription;
import com.ecole221.gestion_scoaire.model.PeriodeInscription;
import com.ecole221.gestion_scoaire.repository.*;
import com.ecole221.gestion_scoaire.service.InscriptionService;
import com.ecole221.gestion_scoaire.validator.Helper;
import com.ecole221.gestion_scoaire.validator.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InscriptionServiceImpl implements InscriptionService {

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Autowired
    private InscriptionMapper inscriptionMapper;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private ClasseRepository classeRepository;
    @Autowired
    private GenerateMaticuleRepository generateMaticuleRepository;
    @Autowired
    private EtudiantMapper etudiantMapper;
    @Autowired
    private PeriodeInscriptionRepository periodeInscriptionRepository;
    @Autowired
    private Helper helper;
    public InscriptionDto save(InscriptionDto inscriptionDto, EtudiantDto etudiantDto) {
        try {
            // Validate DTOs
            helper.validateInscriptionDto(inscriptionDto);
            helper.validateEtudiantDto(etudiantDto);

            LocalDate inscriptionDate = Validation.validateAndParseDate(inscriptionDto.getDate().toString(), "yyyy-MM-dd");

            // Generate the academic year
            String anneeScolaire = Helper.generateAnneeScolaire();

            // Get and validate the inscription period
            PeriodeInscription periodeInscription = Helper.getPeriodeInscription(anneeScolaire, periodeInscriptionRepository);
            Helper.validateInscriptionDate(inscriptionDate, periodeInscription.getDateDebut(), periodeInscription.getDateFin());

            // Set the academic year in the DTO
            inscriptionDto.setAnneeScolaire(anneeScolaire);

            // Generate the student's matricule
            String matricule = Helper.generateMatricule(generateMaticuleRepository);

            // Map the EtudiantDto to an Etudiant entity
            Etudiant etudiant = etudiantMapper.etudiantDtoToEtudiantEntity(etudiantDto);
            etudiant.setMatricule(matricule);

            // Save the Etudiant entity
            etudiant = etudiantRepository.save(etudiant);

            // Set the generated Etudiant ID in InscriptionDto
            inscriptionDto.setEtudiantId(etudiant.getId());

            // Update the matricule generation repository
            Helper.updateGenerateMatricule(generateMaticuleRepository);

            // Get the Classe entity using the class ID from the DTO
            Classe classe = Helper.getClasse(inscriptionDto.getClasseId(), classeRepository);

            // Check for existing etudiant inscriptions
            Helper.checkExistingEtudiant(etudiant, inscriptionRepository);
            Helper.checkExistingInscription(etudiant, classe, inscriptionRepository);

            // Map the InscriptionDto to an Inscription entity, including the saved Etudiant and Classe entities
            Inscription inscription = inscriptionMapper.fromInscriptionDtoToEntity(inscriptionDto, classe, etudiant);

            // Save the Inscription entity
            inscription = inscriptionRepository.save(inscription);

            // Return the mapped InscriptionDto
            return InscriptionMapper.fromInscriptionEntityToDto(inscription);
        } catch (Exception e) {
            // Log detailed error message
            System.err.println("Error occurred while saving inscription: " + e.getMessage());
            e.printStackTrace();  // Print the stack trace for debugging
            throw new RuntimeException("Une Erreur s'est produit");  // Generic message returned to user
        }
    }






    @Override
        public List<InscriptionDto> getAllInscriptionByClasse(String libelle) {
        Classe classe = Helper.getClasseLibelle(libelle,classeRepository);
        List<Inscription> inscriptions = Helper.getClasseInscrits(classe,inscriptionRepository);

        return inscriptions.stream()
                .map(InscriptionMapper::fromInscriptionEntityToDto)
                .collect(Collectors.toList());
    }


    @Override
    public InscriptionDto getInscriptionByEtudiant(String matricule) {
      Etudiant etudiant = Helper.getEtudiantByMatricule(matricule,etudiantRepository);
      Inscription inscription = Helper.getEtudiantByInscription(etudiant,inscriptionRepository);
        return InscriptionMapper.fromInscriptionEntityToDto(inscription);
    }

                                                        
    public List<InscriptionDto> list()
    {
         List<Inscription> inscriptions = inscriptionRepository.findAll();
         return inscriptions.stream()
                 .map(InscriptionMapper::fromInscriptionEntityToDto)
                 .collect(Collectors.toList());
    }
}
