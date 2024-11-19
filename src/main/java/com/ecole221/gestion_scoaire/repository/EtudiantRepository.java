package com.ecole221.gestion_scoaire.repository;

import com.ecole221.gestion_scoaire.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EtudiantRepository extends JpaRepository<Etudiant,Integer> {
    Optional<Etudiant> findByMatricule(String matricule);
    Optional<Etudiant> findByEmail(String email);
    Optional<Etudiant> findByTelephone(String telephone);
    Optional<Etudiant> findById(int id);

}
