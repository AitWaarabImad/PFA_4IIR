package com.example.invite.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class InviteDTO {
    long idInvite;
    String nom_invite;
    long idReu;
    long idUser;
    Long ID_Re;
    Date debutR;
    Date FinReu;
    String description;
    String nom_rapporteur;
    String nom_organisateur;
    String titre;
    String nom_salle;
}
