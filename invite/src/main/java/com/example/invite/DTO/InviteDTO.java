package com.example.invite.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class InviteDTO {
    long id_invite;
    String nom_invite;
    long id_reu;
    long id_user;
    Long ID_Re;
    Date debutR;
    Date FinReu;
    String description;
    String nom_rapporteur;
    String nom_organisateur;
    String titre;
    String nom_salle;
}
