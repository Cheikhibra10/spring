package com.ecole221.gestion_scoaire.repository;

import com.ecole221.gestion_scoaire.model.Classe;
import com.ecole221.gestion_scoaire.model.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClasseRepository extends JpaRepository<Classe,Integer> {

    List<Classe> findByFiliere(Filiere filiere);
    Optional<Classe> findByLibelle(String libelle);
    boolean existsByCodeAndLibelle(String code, String libelle);

}
