package com.ecole221.gestion_scoaire.controller;


import com.ecole221.gestion_scoaire.dto.EtudiantDto;
import com.ecole221.gestion_scoaire.dto.InscriptionDto;
import com.ecole221.gestion_scoaire.dto.InscriptionRequest;
import com.ecole221.gestion_scoaire.service.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/inscriptions")
public class InscriptionController {

    @Autowired
    private InscriptionService inscriptionService;
    @GetMapping("")
    public List<InscriptionDto> getAll()
    {
        return inscriptionService.list();
    }

    @PostMapping("/add")
    public InscriptionDto addInscription(@RequestBody InscriptionRequest request) {
        InscriptionDto inscriptionDto = request.getInscriptionDto();
        EtudiantDto etudiantDto = request.getEtudiantDto();

        // Call the service method to handle the logic
        return inscriptionService.save(inscriptionDto, etudiantDto);
    }

    @GetMapping("/etudiant/{matricule}")
    public InscriptionDto getInscriptionEtudiant(@PathVariable String matricule)
    {
        return inscriptionService.getInscriptionByEtudiant(matricule);
    }

    @GetMapping("/classe/{libelle}")
    public List<InscriptionDto> getInscriptionsClasse(@PathVariable String libelle)
    {
        return inscriptionService.getAllInscriptionByClasse(libelle);
    }
}
