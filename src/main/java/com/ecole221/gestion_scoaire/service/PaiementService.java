package com.ecole221.gestion_scoaire.service;

import com.ecole221.gestion_scoaire.dto.PaiementDto;
import org.springframework.stereotype.Service;

@Service
public interface PaiementService {
    PaiementDto savePaiement(PaiementDto paiementDto);

}
