package com.example.sallereunion.ENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SalleReunion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID_Salle;
    String nomSalle;
    Long ID_Re;
    int place;
    @Getter
    boolean projecteur;

}
