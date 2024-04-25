package com.example.Reunionbackend.ENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reunion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID_Re;

    Date debutR;
    Date finReu;
  String description;
    Long ID_user;


}
