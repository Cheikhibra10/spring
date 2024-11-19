package com.ecole221.gestion_scoaire.service;

import com.ecole221.gestion_scoaire.dto.ClasseDto;
import com.ecole221.gestion_scoaire.dto.FiliereDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface FiliereService {

     FiliereDto save(FiliereDto filiereDto);
     List<FiliereDto> list();
     FiliereDto update(int id, FiliereDto filiereDto);
     FiliereDto getFiliereById(int id);

}
