package com.example.sallereunion.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SalleReunionDto {
    Long iD_Salle;
    String nomSalle;
    Long iD_Re;
    int place;
    boolean projecteur;


}
