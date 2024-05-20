package com.example.rapport.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RapportDto {
    Long id_rapport;
    Long idReunion;
    Long idRapporteur;
    String Description;
    String Description_corrige;
    String Description_resume;
}
