package com.ecole221.gestion_scoaire.mapper;


import com.ecole221.gestion_scoaire.dto.ClasseDto;
import com.ecole221.gestion_scoaire.model.Classe;
import com.ecole221.gestion_scoaire.model.Filiere;
import org.springframework.stereotype.Component;

@Component
public class ClasseMapper {

    public ClasseDto fromClasseEntityToDto(Classe classe)
    {
        return ClasseDto.builder()
                .id(classe.getId())
                .code(classe.getCode())
                .libelle(classe.getLibelle())
                .fraisInscription(classe.getFraisInscription())
                .mensualite(classe.getMensualite())
                .autreFrais(classe.getAutreFrais())
                .filiereId(classe.getFiliere().getId() )
                .build();
    }

    public Classe fromClasseDtoToEntity(ClasseDto classeDto, Filiere filiere)
    {
        return Classe.builder()
                .id(classeDto.getId())
                .code(classeDto.getCode())
                .libelle(classeDto.getLibelle())
                .fraisInscription(classeDto.getFraisInscription())
                .mensualite(classeDto.getMensualite())
                .autreFrais(classeDto.getAutreFrais())
                .filiere(filiere)
                .build();
    }
}
