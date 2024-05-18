package com.example.rapport.Service;

import com.example.rapport.Dto.RapportDto;
import com.example.rapport.Repository.iRapportRepo;
import com.example.rapport.Service.iRapportService;
import com.example.rapport.entity.Rapport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RapportServiceImpl implements iRapportService {

    @Autowired
    private iRapportRepo rapportRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RapportDto getRapportById(Long id) {
        return rapportRepository.findById(id)
                .map(rapport -> modelMapper.map(rapport, RapportDto.class))
                .orElseThrow(() -> new RuntimeException("Rapport not found with id: " + id));
    }

    @Override
    public RapportDto createRapport(RapportDto rapportDto) {
        Rapport rapport = modelMapper.map(rapportDto, Rapport.class);
        rapport = rapportRepository.save(rapport);
        return modelMapper.map(rapport, RapportDto.class);
    }

    @Override
    public void deleteRapport(Long id) {
        rapportRepository.deleteById(id);
    }

    @Override
    public RapportDto updateRapport(Long id, RapportDto rapportDto) {
        Rapport rapport = rapportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rapport not found with id: " + id));
        modelMapper.map(rapportDto, rapport);
        rapport = rapportRepository.save(rapport);
        return modelMapper.map(rapport, RapportDto.class);
    }

    @Override
    public List<RapportDto> getAllRapports() {
        return rapportRepository.findAll().stream()
                .map(rapport -> modelMapper.map(rapport, RapportDto.class))
                .collect(Collectors.toList());
    }
}
