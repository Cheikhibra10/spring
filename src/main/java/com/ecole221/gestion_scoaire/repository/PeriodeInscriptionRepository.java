package com.ecole221.gestion_scoaire.repository;

import com.ecole221.gestion_scoaire.model.PeriodeInscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PeriodeInscriptionRepository extends JpaRepository<PeriodeInscription,Integer> {
    Optional<PeriodeInscription> findByAnneeScolaire(String anneeScolaire);
    boolean existsByanneeScolaire(String anneeScolaire);
}
