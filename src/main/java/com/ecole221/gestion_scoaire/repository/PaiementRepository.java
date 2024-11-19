package com.ecole221.gestion_scoaire.repository;

import com.ecole221.gestion_scoaire.model.Etudiant;
import com.ecole221.gestion_scoaire.model.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PaiementRepository extends JpaRepository<Paiement, Integer> {
    List<Paiement> findByEtudiant(Etudiant etudiant);

    // Find payments by student and last month
    List<Paiement> findByEtudiantAndDernierMois(Etudiant etudiant, String dernierMois);

    // Find payments by the date of payment
    List<Paiement> findByDatePaiement(LocalDate datePaiement);
}
