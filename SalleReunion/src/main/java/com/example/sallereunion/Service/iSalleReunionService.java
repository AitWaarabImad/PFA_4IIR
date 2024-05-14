package com.example.sallereunion.Service;

import com.example.sallereunion.DTO.SalleReunionDto;
import com.example.sallereunion.ENTITY.SalleReunion;

import java.util.List;
import java.util.Optional;

public interface iSalleReunionService {

    public Optional <SalleReunionDto> getSallebyId(Long id);
    public SalleReunionDto createSalle(SalleReunionDto salleReunionDto);

    public void DeleteSalle(Long Id);

    public SalleReunion updateSalle(Long id, SalleReunionDto  salleReunionDto);

    public List<SalleReunion> getAllSalle();

    public Optional<Long> getSalleReunionId(String nomC);


}
