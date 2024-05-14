package com.example.sallereunion.Repositories;

import com.example.sallereunion.ENTITY.SalleReunion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalleReunionRepo extends JpaRepository<SalleReunion,Long> {

    public Optional<SalleReunion> findSalleReunionByNomSalle(String nomC);
}
