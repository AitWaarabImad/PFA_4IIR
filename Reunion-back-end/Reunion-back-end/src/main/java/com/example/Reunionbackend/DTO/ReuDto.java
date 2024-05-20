package com.example.Reunionbackend.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReuDto {
    Long ID_Re;
    Date debutR;
    Date FinReu;
    String description;
    Long ID_user;
    String username;
    Long ID_rapporteur;
    String nom_rapporteur;
    String nom_organisateur;
    String titre;
    Long id_salle;
    String nom_salle;
    Long idRapport;
    List<Long> ids;


}
