package com.ecole221.gestion_scoaire.mapper;

import com.ecole221.gestion_scoaire.dto.PeriodeInscriptionDto;
import com.ecole221.gestion_scoaire.model.PeriodeInscription;

public class PeriodeInscriptionMapper {

    public PeriodeInscriptionDto fromEntityToDto(PeriodeInscription periodeInscription)
    {
        return PeriodeInscriptionDto.builder()
                .id(periodeInscription.getId())
                .dateDebut(periodeInscription.getDateDebut())
                .dateFin(periodeInscription.getDateFin())
                .statut(periodeInscription.getStatut())
                .anneeScolaire(periodeInscription.getAnneeScolaire())
                .build();
    }

    public PeriodeInscription fromDtoToEntity(PeriodeInscriptionDto periodeInscriptionDto)
    {
        return PeriodeInscription.builder()
                .id(periodeInscriptionDto.getId())
                .dateDebut(periodeInscriptionDto.getDateDebut())
                .dateFin(periodeInscriptionDto.getDateFin())
                .statut(periodeInscriptionDto.getStatut())
                .anneeScolaire(periodeInscriptionDto.getAnneeScolaire())
                .build();
    }
}
