package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    Long ID;
    String nom;
    String username;
    String prenom;
    String email;
    String password;
    Role role = Role.Invite;
    Boolean isAuthenticated;

    Long ID_calendrier;
    Long ID_reunion;



    public void setPassword(String password) {
        String salt = BCrypt.gensalt();

        String hashedPassword = BCrypt.hashpw(password, salt);

        this.password = hashedPassword;
    }


}
