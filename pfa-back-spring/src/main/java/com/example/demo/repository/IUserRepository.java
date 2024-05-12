package com.example.demo.repository;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User,Long> {
    Optional<User>  findByUsername(String username);

    Optional<User> findByEmail(String email);
    Optional<User> findByNomAndPrenom(String nom, String prenom);
}
