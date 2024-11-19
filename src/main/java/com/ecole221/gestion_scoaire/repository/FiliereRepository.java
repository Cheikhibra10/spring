package com.ecole221.gestion_scoaire.repository;

import com.ecole221.gestion_scoaire.model.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FiliereRepository extends JpaRepository<Filiere,Integer> {
   Optional<Filiere> findByLibelle(String libelle);
   boolean existsByLibelle(String libelle);
}
