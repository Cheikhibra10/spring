package com.ecole221.gestion_scoaire.controller;


import com.ecole221.gestion_scoaire.dto.ClasseDto;
import com.ecole221.gestion_scoaire.dto.FiliereDto;
import com.ecole221.gestion_scoaire.service.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClasseController {
    @Autowired
    private ClasseService classeService;

    @GetMapping("")
    public List<ClasseDto> list()
    {
        return classeService.getAllClasses();
    }

    @PostMapping("/add")
    public ClasseDto addClasse(@RequestBody ClasseDto classeDto)
    {
        return classeService.save(classeDto);
    }

    @GetMapping("/{id}")
    public ClasseDto getEtudiant(@PathVariable int id)
    {
        return classeService.getClasseById(id);
    }
    @GetMapping("/filiere/{libelle}")
    public List<ClasseDto> getClasseByFiliereLibelle(@PathVariable String libelle) {
        return classeService.getClasseByFiliereByLibelle(libelle);
    }

    @PutMapping("/update/{id}")
    public ClasseDto update(@PathVariable int id, @RequestBody ClasseDto classeDto) {
        return classeService.updateClasse(id,classeDto);
    }
}
