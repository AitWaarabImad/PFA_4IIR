package com.example.Reunionbackend.ENTITY;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reunion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID_Re;
    Date debutR;
    Date FinReu;
    String description;
    Long ID_user;
    Long ID_rapporteur ;

    String nom_rapporteur;
    String nom_organisateur;
    String titre;
    Long id_salle;
    String nom_salle;


}
