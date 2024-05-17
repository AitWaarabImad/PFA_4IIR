package com.example.rapport.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RapportDto {
    long id_rapport;
    String Description;
    String Description_corrigé;
    String Description_resumé;
}
