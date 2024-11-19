package com.ecole221.gestion_scoaire.controller;


import com.ecole221.gestion_scoaire.dto.ClasseDto;
import com.ecole221.gestion_scoaire.dto.FiliereDto;
import com.ecole221.gestion_scoaire.service.FiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filieres")
public class FiliereController {

    @Autowired
    private FiliereService filiereService;
    @GetMapping("")
    public List<FiliereDto> getAll(){
        return filiereService.list();
    }

    @PostMapping("/add")
    public FiliereDto addFiliere(@RequestBody FiliereDto filiereDto){
        return filiereService.save(filiereDto);

    }

    @PutMapping("/update/{id}")
    public FiliereDto updateFiliere(@PathVariable int id, @RequestBody FiliereDto filiereDto) {
            return filiereService.update(id, filiereDto);
    }

    @GetMapping("/{id}")
    public FiliereDto getById(@PathVariable int id)
    {
        return filiereService.getFiliereById(id);
    }
}
