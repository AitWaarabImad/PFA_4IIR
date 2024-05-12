package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID_user;
    String nom;
    String username;
    String prenom;
    String nomComplet;
    String email;
    String password;
    @Enumerated(EnumType.STRING)
    Role role = Role.Invite;
    Boolean isAuthenticated;

    Long ID_calendrier;
    Long ID_reunion;

    public void setNomComplet() {

        this.nomComplet =getNom() + " "+ getPrenom();

    }



    public void setPassword(String password) {
        String salt = BCrypt.gensalt();

        String hashedPassword = BCrypt.hashpw(password, salt);

        this.password = hashedPassword;
    }


}
