package com.ecole221.gestion_scoaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecole221.gestion_scoaire.model.GenerateMatricule;
import org.springframework.stereotype.Repository;

public interface GenerateMaticuleRepository extends JpaRepository<GenerateMatricule,Integer> {
}
