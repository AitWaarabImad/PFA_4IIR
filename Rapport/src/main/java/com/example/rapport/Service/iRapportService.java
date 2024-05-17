package com.example.rapport.Service;

import com.example.rapport.Dto.RapportDto;

import java.util.List;

public interface iRapportService {
    RapportDto getRapportById(Long id);
    RapportDto createRapport(RapportDto rapportDto);
    void deleteRapport(Long id);
    RapportDto updateRapport(Long id, RapportDto rapportDto);
    List<RapportDto> getAllRapports();
}