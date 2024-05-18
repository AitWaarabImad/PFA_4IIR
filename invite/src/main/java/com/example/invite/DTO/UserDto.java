package com.example.invite.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    Long ID_user;
    String nom;
    String prenom ;
    String username;
    String email;
    String password;
    String nomComplet;
    Boolean isAuthenticated;
    Role role = Role.Invite;

}
