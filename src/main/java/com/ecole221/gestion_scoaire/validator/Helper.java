package com.ecole221.gestion_scoaire.validator;

import com.ecole221.gestion_scoaire.dto.ClasseDto;
import com.ecole221.gestion_scoaire.dto.EtudiantDto;
import com.ecole221.gestion_scoaire.dto.InscriptionDto;
import com.ecole221.gestion_scoaire.mapper.InscriptionMapper;
import com.ecole221.gestion_scoaire.model.*;
import com.ecole221.gestion_scoaire.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class Helper {

    private final EtudiantRepository etudiantRepository;
    private final ClasseRepository classeRepository;
    private final FiliereRepository filiereRepository;
    private final PeriodeInscriptionRepository periodeInscriptionRepository;


    public void validateEtudiantDto(EtudiantDto etudiantDto) {
        Validation.validateNotEmptyOrSpaces(etudiantDto.getNom(), "nom");
        Validation.validateNotEmptyOrSpaces(etudiantDto.getPrenom(), "prenom");
        Validation.validatePhoneNumber(etudiantDto.getTelephone());
        Validation.validateEmail(etudiantDto.getEmail());
        Validation.validateAndParseDate(etudiantDto.getDateNaissance().toString(), "yyyy-MM-dd");
        Validation.validateNotEmptyOrSpaces(etudiantDto.getAdresse(), "adresse");

        if (etudiantRepository.findByEmail(etudiantDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("L'email existe déjà");
        }

        if (etudiantRepository.findByTelephone(etudiantDto.getTelephone()).isPresent()) {
            throw new IllegalArgumentException("Le telephone existe déjà");
        }
    }
    public static String generateMatricule(GenerateMaticuleRepository generateMatriculeRepository) {
        GenerateMatricule generateMatricule = generateMatriculeRepository.findAll().get(0);
        return String.format("M%05d-%s", generateMatricule.getNumero() + 1, LocalDate.now().getYear());
    }
    public static void updateGenerateMatricule(GenerateMaticuleRepository generateMatriculeRepository) {
        GenerateMatricule generateMatricule = generateMatriculeRepository.findAll().get(0);
        generateMatricule.setNumero(generateMatricule.getNumero() + 1);
        generateMatriculeRepository.save(generateMatricule);
    }
    public void validateClasseDto(ClasseDto classeDto) {
        if (classeRepository.existsByCodeAndLibelle(classeDto.getCode(), classeDto.getLibelle())) {
            throw new IllegalArgumentException("La classe " + classeDto.getLibelle() + " en " + classeDto.getCode() + " existe déjà");
        }
        Validation.validateNotEmptyOrSpaces(classeDto.getCode(), "code");
        Validation.validateNotEmptyOrSpaces(classeDto.getLibelle(), "libelle");
        Validation.validateNotEmptyOrSpaces(classeDto.getFraisInscription(), "fraisInscription");
        Validation.validateNotEmptyOrSpaces(classeDto.getMensualite(), "mensualite");
        Validation.validateNotEmptyOrSpaces(classeDto.getAutreFrais(), "autreFrais");
        Validation.validateNotNegative(classeDto.getFiliereId(), "filiereId");
    }

    public void validateInscriptionDto(InscriptionDto inscriptionDto) {
        if (inscriptionDto.getDate() == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }

        // Proceed with validation
        Validation.validateNotEmptyOrSpaces(inscriptionDto.getAnneeScolaire(), "anneeScolaire");
         Validation.validateNotEmptyOrSpacesNumber(inscriptionDto.getClasseId(), "classeId");
        Validation.validateNotNegative(inscriptionDto.getClasseId(), "classeId");
    }


    public Filiere getFiliere(int filiereId) {
        return filiereRepository.findById(filiereId)
                .orElseThrow(() -> new IllegalArgumentException("La filiere avec l'id " + filiereId + " n'existe pas"));
    }t

    public static void validateInscriptionDate(LocalDate inscriptionDate, LocalDate dateDebut, LocalDate dateFin) {
        if (inscriptionDate.isBefore(dateDebut) || inscriptionDate.isAfter(dateFin)) {
            throw new IllegalArgumentException("L'Inscription n'est pas permise dans cette période");
        }
    }

    public static String generateAnneeScolaire() {
        int currentYear = LocalDate.now().getYear();
        return currentYear + "-" + (currentYear + 1);
    }

    public static PeriodeInscription getPeriodeInscription(String anneeScolaire, PeriodeInscriptionRepository periodeInscriptionRepository) {
        return periodeInscriptionRepository.findByAnneeScolaire(anneeScolaire)
                .orElseThrow(() -> new IllegalArgumentException("Cette année académique n'est pas disponible"));
    }

    public static Etudiant getEtudiant(int etudiantId, EtudiantRepository etudiantRepository) {
        return etudiantRepository.findById(etudiantId)
                .orElseThrow(() -> new IllegalArgumentException("Etudiant avec l'id " + etudiantId + " n'existe pas"));
    }

    public static Classe getClasse(int classeId, ClasseRepository classeRepository) {
        return classeRepository.findById(classeId)
                .orElseThrow(() -> new IllegalArgumentException("Classe avec l'id " + classeId + " n'existe pas"));
    }

    public static Etudiant getEtudiantByMatricule(String matricule,EtudiantRepository etudiantRepository)
    {
        return etudiantRepository.findByMatricule(matricule)
                .orElseThrow(() -> new IllegalArgumentException("Etudiant avec le matricule " + matricule + " n'existe pas!"));

    }

    public static Inscription getEtudiantByInscription(Etudiant etudiant,InscriptionRepository inscriptionRepository)
    {
        return inscriptionRepository.findByEtudiant(etudiant)
                .orElseThrow(() -> new IllegalArgumentException("Etudiant avec le matricule " + etudiant.getMatricule() + " n'existe pas!"));

    }

    public static Classe getClasseLibelle(String libelle,ClasseRepository classeRepository)
    {
        return classeRepository.findByLibelle(libelle)
                .orElseThrow(() -> new IllegalArgumentException("La classe "+libelle+" n'existe pas"));

    }
    public static List<Inscription> getClasseInscrits(Classe classe,InscriptionRepository inscriptionRepository)
    {
         List<Inscription> inscriptions = inscriptionRepository.findByClasse(classe);
        if(inscriptions.isEmpty()){
            throw new IllegalArgumentException("La classe " + classe.getLibelle() + " n'est pas trouvé dans les inscriptions");
        }
         inscriptions.stream()
                .map(InscriptionMapper::fromInscriptionEntityToDto)
                .collect(Collectors.toList()).reversed();
        return inscriptions;
    }
    public static void checkExistingInscription(Etudiant etudiant, Classe classe, InscriptionRepository inscriptionRepository) {
        Optional<Inscription> existingInscription = inscriptionRepository.findByEtudiantAndClasse(etudiant, classe);
        if (existingInscription.isPresent()) {
            throw new IllegalArgumentException("L'etudiant est déjà inscrit dans cette classe");
        }
    }
    public static void checkExistingEtudiant(Etudiant etudiant, InscriptionRepository inscriptionRepository) {
        boolean existingEtudiant = inscriptionRepository.existsByEtudiant(etudiant);
        if (existingEtudiant) {
            throw new IllegalArgumentException("L'etudiant existe deja");
        }
    }

    public static float getMontantRestantForEtudiant(Etudiant etudiant, PaiementRepository paiementRepository, LocalDate currentDate) {
        String lastMonth = currentDate.minusMonths(1).format(DateTimeFormatter.ofPattern("M-yyyy"));
        List<Paiement> paiements = paiementRepository.findByEtudiantAndDernierMois(etudiant, lastMonth);
        float montantRestant = 0;
        for (Paiement paiement : paiements) {
            montantRestant += paiement.getMontantRestant();
        }
        return montantRestant;
    }


    public static void saveMontantRestant(Paiement paiement, float montantRestant,PaiementRepository paiementRepository) {
        paiement.setMontantRestant(montantRestant);
        paiementRepository.save(paiement);
    }

    public static void clearMontantRestant(Paiement paiement,PaiementRepository paiementRepository) {
        paiement.setMontantRestant(0);
        paiementRepository.save(paiement);
    }

    public static void clearMontantRestantForPreviousMonth(Etudiant etudiant, LocalDate currentDate, PaiementRepository paiementRepository) {
        String lastMonth = Helper.getDernierMois(currentDate.minusMonths(1));
        List<Paiement> previousMonthPaiements = paiementRepository.findByEtudiantAndDernierMois(etudiant, lastMonth);

        for (Paiement previousPaiement : previousMonthPaiements) {
            if (previousPaiement.getMontantRestant() > 0) {
                previousPaiement.setMontantRestant(0);
                paiementRepository.save(previousPaiement);
            }
        }
    }

    public static String getDernierMois(LocalDate datePaiement) {
        LocalDate lastMonth = datePaiement.minusMonths(1);
        return lastMonth.getMonthValue() + "-" + lastMonth.getYear();
    }
}
