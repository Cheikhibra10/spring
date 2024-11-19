//package com.ecole221.gestion_scoaire.service.impl;
//
//import com.ecole221.gestion_scoaire.dto.FiliereDto;
//import com.ecole221.gestion_scoaire.dto.PeriodeInscriptionDto;
//import com.ecole221.gestion_scoaire.mapper.PeriodeInscriptionMapper;
//import com.ecole221.gestion_scoaire.model.Filiere;
//import com.ecole221.gestion_scoaire.repository.PeriodeInscriptionRepository;
//import com.ecole221.gestion_scoaire.service.PeriodeInscriptionService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class PeriodeInscriptionImpl implements PeriodeInscriptionService {
//
//    @Autowired
//    public PeriodeInscriptionRepository periodeInscriptionRepository;
//    @Autowired
//    public PeriodeInscriptionMapper periodeInscriptionMapper;
//    @Override
//    public PeriodeInscriptionDto savePeriode(PeriodeInscriptionDto periodeInscriptionDto) {
//        if(periodeInscriptionRepository.existsByLibelle(periodeInscriptionDto.getAnneeScolaire())){
//
//        }
//    }
//}
