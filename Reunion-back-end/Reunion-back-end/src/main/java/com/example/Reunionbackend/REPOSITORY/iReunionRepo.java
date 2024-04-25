package com.example.Reunionbackend.REPOSITORY;

import com.example.Reunionbackend.ENTITY.Reunion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iReunionRepo extends JpaRepository<Reunion,Long> {
}
