package com.ecole221.gestion_scoaire.controller;

import com.ecole221.gestion_scoaire.dto.EtudiantDto;
import com.ecole221.gestion_scoaire.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {


    @Autowired
    private EtudiantService etudiantService;

    @PostMapping("/add")
    public EtudiantDto createEtudiant(@RequestBody EtudiantDto etudiantDto) {
        return etudiantService.saveEtudiant(etudiantDto);
    }

    @GetMapping("/matricule/{matricule}")
    public EtudiantDto getEtudiantByMatricule(@PathVariable String matricule) {
        return etudiantService.getEtudiantByMatricule(matricule);
    }

    @GetMapping("/{id}")
    public EtudiantDto getEtudiantById(@PathVariable int id) {
        return etudiantService.getEtudiantById(id);
    }

    @GetMapping("")
    public List<EtudiantDto> getAllEtudiants() {
        return etudiantService.getAllEtudiants();
    }

}
