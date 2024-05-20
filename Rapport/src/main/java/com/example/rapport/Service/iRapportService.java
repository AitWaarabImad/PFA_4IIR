package com.example.rapport.Service;

import com.example.rapport.Dto.RapportDto;

import java.util.List;

public interface iRapportService {
    RapportDto getRapportById(Long id);
    RapportDto createRapport(Long id_rapp,Long id_reu);
    void deleteRapport(Long id);
    RapportDto updateRapport(Long id, RapportDto rapportDto);
    List<RapportDto> getAllRapports();

    List<RapportDto> getRaportsbyIdrapp(Long id);
}