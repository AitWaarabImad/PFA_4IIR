package com.example.rapport.Service;

import com.example.rapport.Dto.RapportDto;
import com.example.rapport.IA.DeepAiService;


import com.example.rapport.Repository.iRapportRepo;
import com.example.rapport.entity.Rapport;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class RapportService implements iRapportService{
    private iRapportRepo rapportRepository;
    private  ModelMapper modelMapper;
    private final DeepAiService deepAiService;



    @Override
    public RapportDto getRapportById(Long id) {
        Rapport rapport = rapportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rapport not found with id: " + id));
        return modelMapper.map(rapport, RapportDto.class);
    }



    @Override
    public RapportDto createRapport(RapportDto rapportDto) {
        if (rapportDto.getDescription() == null || rapportDto.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }

        Rapport rapport = modelMapper.map(rapportDto, Rapport.class);

        // Utiliser la description du rapport pour générer le résumé et la correction
        String resume = deepAiService.summarizeText(rapportDto.getDescription());
        String correction = deepAiService.correctText(rapportDto.getDescription());

        // Mettre à jour les champs résumé et corrigé dans le DTO
        rapportDto.setDescription_resumé(resume);
        rapportDto.setDescription_corrigé(correction);

        // Sauvegarde dans la base de données après mise à jour des champs
        rapport = rapportRepository.save(rapport);

        // Assurez-vous que l'entité rapport a aussi les champs mis à jour pour résumé et corrigé
        rapport.setDescription_resumé(rapportDto.getDescription_resumé());
        rapport.setDescription_corrigé(rapportDto.getDescription_corrigé());

        // Mapping de l'entité à DTO
        return modelMapper.map(rapport, RapportDto.class);
    }

    @Override
    public void deleteRapport(Long id) {
        rapportRepository.deleteById(id);
    }

    @Override
    public RapportDto updateRapport(Long id, RapportDto rapportDto) {
        Rapport existingRapport = rapportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rapport not found with id: " + id));
        modelMapper.map(rapportDto, existingRapport);
        existingRapport = rapportRepository.save(existingRapport);
        return modelMapper.map(existingRapport, RapportDto.class);
    }

    @Override
    public List<RapportDto> getAllRapports() {
        return rapportRepository.findAll().stream()
                .map(rapport -> modelMapper.map(rapport, RapportDto.class))
                .collect(Collectors.toList());
    }
}