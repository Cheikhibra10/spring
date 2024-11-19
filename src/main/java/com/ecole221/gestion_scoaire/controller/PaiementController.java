package com.ecole221.gestion_scoaire.controller;


import com.ecole221.gestion_scoaire.dto.PaiementDto;
import com.ecole221.gestion_scoaire.model.Paiement;
import com.ecole221.gestion_scoaire.service.PaiementService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paiements")
public class PaiementController {
    @Autowired
    public PaiementService paiementService;

    @PostMapping("")
    public PaiementDto save(@RequestBody PaiementDto paiementDto) {
        return paiementService.savePaiement(paiementDto);
    }
}
