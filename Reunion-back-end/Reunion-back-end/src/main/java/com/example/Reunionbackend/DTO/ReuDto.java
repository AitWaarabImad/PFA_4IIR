package com.example.Reunionbackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReuDto {
    Long ID_Re;

    Date debutR;
    Date FinReu;
    String description;
    Long ID_user;
    String username;

}
