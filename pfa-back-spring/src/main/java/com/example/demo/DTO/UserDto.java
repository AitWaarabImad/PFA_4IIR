package com.example.demo.DTO;

import com.example.demo.entities.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    String nom;
    String prenom ;
    String username;
    String email;
    String password;
    Boolean isAuthenticated;
    Role role = Role.Invite;

}
