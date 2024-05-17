package com.example.invite.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Invité {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   long id_invite;
    String nom_invite;
    long id_reu;
    long id_user;
}
