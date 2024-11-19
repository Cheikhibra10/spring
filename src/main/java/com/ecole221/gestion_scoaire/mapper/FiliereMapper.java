package com.ecole221.gestion_scoaire.mapper;


import com.ecole221.gestion_scoaire.dto.FiliereDto;
import com.ecole221.gestion_scoaire.model.Filiere;
import org.springframework.stereotype.Component;

@Component
public class FiliereMapper {

    public FiliereDto fromFiliiereEntityToDto(Filiere filiere){
        return FiliereDto.builder()
                .id(filiere.getId())
                .libelle(filiere.getLibelle())
                .build();
    }

    public Filiere fromFiliereDtoToEntity(FiliereDto filiereDto){
        return Filiere.builder()
                .id(filiereDto.getId())
                .libelle(filiereDto.getLibelle())
                .build();
    }
}
