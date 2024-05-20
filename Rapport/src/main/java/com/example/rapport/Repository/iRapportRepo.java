package com.example.rapport.Repository;

import com.example.rapport.Dto.RapportDto;
import com.example.rapport.entity.Rapport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface iRapportRepo extends JpaRepository<Rapport,Long> {
    List<RapportDto> findRapportByIdRapporteur(Long id);
}
