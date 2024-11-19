package com.ecole221.gestion_scoaire.mapper;

import com.ecole221.gestion_scoaire.dto.PaiementDto;
import com.ecole221.gestion_scoaire.model.Classe;
import com.ecole221.gestion_scoaire.model.Etudiant;
import com.ecole221.gestion_scoaire.model.Paiement;
import org.springframework.stereotype.Component;

@Component
public class PaiementMapper {

    public PaiementDto fromPaiementEntityToDto(Paiement paiement){
        return PaiementDto.builder()
                .id(paiement.getId())
                .mensualite(paiement.getMensualite())
                .montantPaye(paiement.getMontantPaye())
                .dernierMois(paiement.getDernierMois())
                .datePaiement(paiement.getDatePaiement())
                .dateLimitePaiement(paiement.getDateLimitePaiement())
                .classeId(paiement.getClasse().getId())
                .etudiantId(paiement.getEtudiant().getId())
                .build();
    }

    public Paiement fromPaiementDtoToEntity(PaiementDto paiementDto, Classe classe, Etudiant etudiant){
        return Paiement.builder()
                .id(paiementDto.getId())
                .mensualite(paiementDto.getMensualite())
                .montantPaye(paiementDto.getMontantPaye())
                .dernierMois(paiementDto.getDernierMois())
                .datePaiement(paiementDto.getDatePaiement())
                .dateLimitePaiement(paiementDto.getDateLimitePaiement())
                .classe(classe)
                .etudiant(etudiant)
                .build();
    }
}

