package com.example.rapport.Repository;

import com.example.rapport.entity.Rapport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iRapportRepo extends JpaRepository<Rapport,Long> {
}
