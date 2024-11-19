package com.ecole221.gestion_scoaire.repository;

import com.ecole221.gestion_scoaire.model.Classe;
import com.ecole221.gestion_scoaire.model.Etudiant;
import com.ecole221.gestion_scoaire.model.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InscriptionRepository extends JpaRepository<Inscription,Integer> {
    List<Inscription> findByClasse(Classe classe);
    Optional<Inscription> findByEtudiant(Etudiant etudiant);
    Optional<Inscription> findByEtudiantAndClasse(Etudiant etudiant, Classe classe);
    boolean existsByEtudiant(Etudiant etudiant);
}
