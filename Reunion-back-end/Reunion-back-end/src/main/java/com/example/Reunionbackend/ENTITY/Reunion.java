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
<<<<<<< HEAD


=======
>>>>>>> 5805285a143217a0583255c371c3f136692ba3d2
    Date debutR;

    Date FinReu;
  String description;
    Long ID_user;
<<<<<<< HEAD
    Long ID_rapporteur ;



=======
    //List des user qui seront invites
    //Le rapporteur designer pour la reunion
    //le rapport
>>>>>>> 5805285a143217a0583255c371c3f136692ba3d2


}
