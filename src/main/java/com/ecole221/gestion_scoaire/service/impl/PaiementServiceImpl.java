package com.ecole221.gestion_scoaire.service.impl;

import com.ecole221.gestion_scoaire.dto.PaiementDto;
import com.ecole221.gestion_scoaire.mapper.PaiementMapper;
import com.ecole221.gestion_scoaire.model.Classe;
import com.ecole221.gestion_scoaire.model.Etudiant;
import com.ecole221.gestion_scoaire.model.Paiement;
import com.ecole221.gestion_scoaire.repository.ClasseRepository;
import com.ecole221.gestion_scoaire.repository.EtudiantRepository;
import com.ecole221.gestion_scoaire.repository.PaiementRepository;
import com.ecole221.gestion_scoaire.service.PaiementService;
import com.ecole221.gestion_scoaire.validator.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PaiementServiceImpl implements PaiementService {

    @Autowired
    private PaiementRepository paiementRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private PaiementMapper paiementMapper;


    @Override
    public PaiementDto savePaiement(PaiementDto paiementDto) {
        Etudiant etudiant = Helper.getEtudiant(paiementDto.getEtudiantId(), etudiantRepository);
        Classe classe = Helper.getClasse(paiementDto.getClasseId(), classeRepository);

        final float FIXED_PAYMENT_AMOUNT = 65000.0f;

        float montantRestant = Helper.getMontantRestantForEtudiant(etudiant, paiementRepository, paiementDto.getDatePaiement());

        float mensualite = paiementDto.getMensualite();
        float autreFrais = Float.parseFloat(classe.getAutreFrais());
        float montantPaye = mensualite + autreFrais;

        Paiement paiement = paiementMapper.fromPaiementDtoToEntity(paiementDto, classe, etudiant);
        float totalMontantPaye = montantPaye + montantRestant;
        paiement.setMontantPaye(totalMontantPaye);
        if (totalMontantPaye > FIXED_PAYMENT_AMOUNT) {
            float excessAmount = totalMontantPaye - FIXED_PAYMENT_AMOUNT;

            Helper.saveMontantRestant(paiement, excessAmount, paiementRepository);
        } else {
            Helper.clearMontantRestant(paiement, paiementRepository);
        }

        Helper.clearMontantRestantForPreviousMonth(etudiant, paiementDto.getDatePaiement(), paiementRepository);

        paiement.setDatePaiement(paiementDto.getDatePaiement());
        paiement.setDateLimitePaiement(paiementDto.getDatePaiement().withDayOfMonth(5).plusMonths(1));
        paiement.setDernierMois(Helper.getDernierMois(paiementDto.getDatePaiement()));
        paiement = paiementRepository.save(paiement);
        return paiementMapper.fromPaiementEntityToDto(paiement);
    }


}
