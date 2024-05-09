package com.example.sallereunion.Controller;

import com.example.sallereunion.DTO.SalleReunionDto;
import com.example.sallereunion.ENTITY.SalleReunion;
import com.example.sallereunion.Service.SalleReunionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class SalleReunionController {

    private final SalleReunionService salleReunionService;

    @GetMapping("get/{id}")
    public Optional<SalleReunionDto> getSalleById(@PathVariable Long id) {
        return salleReunionService.getSallebyId(id);
    }

    @PostMapping("/createSalle")
    public SalleReunionDto createSalle(@RequestBody SalleReunionDto salleReunionDto) {
        return salleReunionService.createSalle(salleReunionDto);
    }

    @DeleteMapping("delete/{id}")
    public void deleteSalle(@PathVariable Long id) {
        salleReunionService.DeleteSalle(id);
    }

    @PutMapping("update/{id}")
    public SalleReunion updateSalle(@PathVariable Long id, @RequestBody SalleReunionDto salleReunionDto) {
        return salleReunionService.updateSalle(id, salleReunionDto);
    }

    @GetMapping("allsalle")
    public List<SalleReunion> getAllSalles() {
        return salleReunionService.getAllSalle();
    }
}
