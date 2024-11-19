package com.ecole221.gestion_scoaire.service;

import com.ecole221.gestion_scoaire.dto.PeriodeInscriptionDto;
import org.springframework.stereotype.Service;

@Service
public interface PeriodeInscriptionService {

    PeriodeInscriptionDto savePeriode(PeriodeInscriptionDto periodeInscriptionDto);
}
