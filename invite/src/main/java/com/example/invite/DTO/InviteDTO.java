package com.example.invite.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class InviteDTO {
    long id_invite;
    String nom_invite;
    long id_reu;
    long id_user;
}
