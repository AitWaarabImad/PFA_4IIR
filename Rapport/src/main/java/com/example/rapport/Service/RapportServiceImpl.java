package com.example.rapport.Service;

import com.example.rapport.Dto.RapportDto;


import com.example.rapport.IA.HuggingFaceTextAnalysisService;
import com.example.rapport.Repository.iRapportRepo;
import com.example.rapport.Service.iRapportService;
import com.example.rapport.entity.Rapport;

import org.json.JSONObject;
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
@Autowired
    private HuggingFaceTextAnalysisService textAnalysisService;
    @Override
    public RapportDto getRapportById(Long id) {
        return rapportRepository.findById(id)
                .map(rapport -> modelMapper.map(rapport, RapportDto.class))
                .orElseThrow(() -> new RuntimeException("Rapport not found with id: " + id));
    }


    @Override
    public RapportDto createRapport(Long id_rapp, Long id_reu) {
        // Mapping du DTO modifié vers l'entité Rapport et sauvegarde dans la base de données
        RapportDto rapportDto = new RapportDto(null,id_reu,id_rapp," "," "," ");
        Rapport rapport = modelMapper.map(rapportDto, Rapport.class);
        rapport = rapportRepository.save(rapport);

        // Renvoi du DTO à partir de l'entité sauvegardée
        return modelMapper.map(rapport, RapportDto.class);
    }

    public void deleteRapport(Long id) {
        rapportRepository.deleteById(id);
    }

    @Override
    public RapportDto updateRapport(Long id, RapportDto rapportDto) {
        // Récupération du rapport à partir de l'identifiant
        Rapport rapport = rapportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rapport non trouvé avec l'identifiant : " + id));

        // Correction et résumé de la description
        String correctedDescription = textAnalysisService.correctText(rapportDto.getDescription());
        String summarizedDescription = textAnalysisService.summarizeText(rapportDto.getDescription());

        // Mise à jour du DTO avec les textes corrigé et résumé
        rapportDto.getDescription();
        rapportDto.setDescription_corrige(correctedDescription);
        rapportDto.setDescription_resume(summarizedDescription);

        // Mapping du DTO modifié vers l'entité Rapport et sauvegarde dans la base de données
        rapport = modelMapper.map(rapportDto, Rapport.class);
        rapport = rapportRepository.save(rapport);

        // Mapping de l'entité Rapport mise à jour vers un nouveau DTO et retour
        return modelMapper.map(rapport, RapportDto.class);
    }

    @Override
    public List<RapportDto> getAllRapports() {
        return rapportRepository.findAll().stream()
                .map(rapport -> modelMapper.map(rapport, RapportDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RapportDto> getRaportsbyIdrapp(Long id) {
        List<Rapport> allReunions = rapportRepository.findAll();

        // Filtre les réunions en fonction de l'ID_user et les convertit en ReuDto
        return allReunions.stream()
                .filter(reunion -> reunion.getIdRapporteur().equals(id))
                .map(reunion -> modelMapper.map(reunion, RapportDto.class))
                .collect(Collectors.toList());
    }
}
