package com.example.Reunionbackend.Services;

import com.example.Reunionbackend.DTO.ReuDto;
import com.example.Reunionbackend.ENTITY.Reunion;
import com.example.Reunionbackend.REPOSITORY.iReunionRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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

    public ReuDto creeReunion(ReuDto Rdto) {
        Reunion reunion = new Reunion();
        reunion = modelMapper.map(Rdto, Reunion.class);
        reunionRepo.save(reunion);
        return Rdto;
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




}
