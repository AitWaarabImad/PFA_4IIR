package com.example.Reunionbackend.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
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


}
