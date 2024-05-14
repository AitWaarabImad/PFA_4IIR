package com.example.Reunionbackend.Services;

import com.example.Reunionbackend.DTO.ReuDto;
import com.example.Reunionbackend.ENTITY.Reunion;
import com.example.Reunionbackend.REPOSITORY.iReunionRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReunionServices implements  iReunionServices {

    @Autowired
    private iReunionRepo reunionRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private WebClient webClient;

    public ReuDto creeReunion(ReuDto rdto) {
        // Ici, nous supposons que le nom du rapporteur est donné et qu'un service distant peut nous retourner l'ID correspondant
        Long idRapporteur = webClient.get()
                .uri("http://localhost:8080/userid/" + rdto.getNom_rapporteur())
                .retrieve()
                .bodyToMono(Long.class)
                .block();


        rdto.setID_rapporteur(idRapporteur);
        Long idsalle = webClient.get()
                .uri("http://localhost:8087/SalleReunion/" + rdto.getNom_salle())
                .retrieve()
                .bodyToMono(Long.class)
                .block();
        rdto.setId_salle(idsalle);


        Reunion reunion = modelMapper.map(rdto, Reunion.class);
        reunionRepo.save(reunion);


        return rdto;
    }

    @Override
    public ReuDto getReunionById(Long id) {

        Optional<Reunion> reunion = reunionRepo.findById(id);
        Reunion reunion1 = reunion.orElseThrow(() -> new RuntimeException("Reunion not found"));
        System.out.println(reunion1);
        Long userId = Long.valueOf(reunion1.getID_user());


        ReuDto reuDto = webClient.get()
                .uri("http://localhost:8080/getId/" + userId)
                .retrieve()
                .bodyToMono(ReuDto.class)
                .block();
        reuDto.setID_Re(reunion1.getID_Re());
        reuDto.setDebutR(reunion1.getDebutR());
        reuDto.setFinReu(reunion1.getFinReu());
        reuDto.setDescription(reunion1.getDescription());
        reuDto.setID_rapporteur(reunion1.getID_rapporteur());
        reuDto.setId_salle(reunion1.getId_salle());
        reuDto.setTitre(reunion1.getTitre());
        reuDto.setNom_rapporteur(reunion1.getNom_rapporteur());
        reuDto.setNom_organisateur(reunion1.getNom_organisateur());
        reuDto.setNom_salle(reunion1.getNom_salle());



        return reuDto;
    }

    @Override
    public void deleteReunion(Long id) {
        reunionRepo.deleteById(id);
    }

    @Override
        public Optional<Reunion> updateReunion(ReuDto reuDto, Long id) {
            Reunion reunion = modelMapper.map(reuDto, Reunion.class);
            Optional<Reunion> reunion1 = reunionRepo.findById(id).map(m -> {
                m.setFinReu(reunion.getFinReu());
                m.setDebutR(reunion.getDebutR());
                m.setDescription(reunion.getDescription());
                m.setID_user(reunion.getID_user());
                m.setID_rapporteur(reunion.getID_rapporteur());
                m.setId_salle(reunion.getId_salle());
                m.setTitre(reunion.getTitre());
                m.setNom_rapporteur(reunion.getNom_rapporteur());
                m.setNom_organisateur(reunion.getNom_organisateur());
                m.setNom_salle(reunion.getNom_salle());

                return reunionRepo.save(m);
            });

        return reunion1;
    }




    public ReuDto getReunion() {

        List<Reunion> reunion = reunionRepo.findAll();
        List<ReuDto> reuDtos = null;
        for (Reunion r : reunion) {
            Long userId = Long.valueOf(r.getID_user());


            ReuDto reuDto = webClient.get()
                    .uri("http://localhost:8080/getId/" + userId)
                    .retrieve()
                    .bodyToMono(ReuDto.class)
                    .block();
            reuDto.setID_Re(r.getID_Re());
            reuDto.setDebutR(r.getDebutR());
            reuDto.setFinReu(r.getFinReu());
            reuDto.setDescription(r.getDescription());
            reuDto.setID_rapporteur(r.getID_rapporteur());
            reuDto.setId_salle(r.getId_salle());
            reuDto.setTitre(r.getTitre());
            reuDto.setNom_rapporteur(r.getNom_rapporteur());
            reuDto.setNom_organisateur(r.getNom_organisateur());
            reuDto.setNom_salle(r.getNom_salle());



            reuDtos.add(reuDto);
            return reuDto;
        }
        return null;
    }
    public List<ReuDto> findReunionsByUserId(Long ID_user) {
        // Récupère toutes les réunions de la base de données
        List<Reunion> allReunions = reunionRepo.findAll();

        // Filtre les réunions en fonction de l'ID_user et les convertit en ReuDto
        return allReunions.stream()
                .filter(reunion -> reunion.getID_user().equals(ID_user))
                .map(reunion -> modelMapper.map(reunion, ReuDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReuDto> getAllReunions() {
        List<Reunion> reunions = reunionRepo.findAll();
        List<ReuDto> reuDtos = new ArrayList<>();
        for (Reunion reunion : reunions) {

            ReuDto reuDto = new ReuDto();
            reuDto.setID_user(reunion.getID_user());
            reuDto.setID_Re(reunion.getID_Re());
            reuDto.setDebutR(reunion.getDebutR());
            reuDto.setFinReu(reunion.getFinReu());
            reuDto.setDescription(reunion.getDescription());
            reuDto.setID_rapporteur(reunion.getID_rapporteur());
            reuDto.setId_salle(reunion.getId_salle());
            reuDto.setTitre(reunion.getTitre());
            reuDto.setNom_rapporteur(reunion.getNom_rapporteur());
            reuDto.setNom_organisateur(reunion.getNom_organisateur());
            reuDto.setNom_salle(reunion.getNom_salle());



            reuDtos.add(reuDto);
        }
        return reuDtos;
    }



}
