package com.example.rapport.Controller;

import com.example.rapport.Dto.RapportDto;
import com.example.rapport.Service.iRapportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RapportController {

   @Autowired

    iRapportService  iRapportService;


    @PostMapping("/createRapport")
    public RapportDto createRapport(@RequestBody RapportDto rapportDto) {
        return iRapportService.createRapport(rapportDto);
    }

    @GetMapping("/rapport/{id}")
    public RapportDto getRapportById(@PathVariable Long id) {
        return iRapportService.getRapportById(id);
    }

    @PutMapping("/update/{id}")
    public RapportDto updateRapport(@PathVariable Long id, @RequestBody RapportDto rapportDto) {
        return iRapportService.updateRapport(id, rapportDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRapport(@PathVariable Long id) {
        iRapportService.deleteRapport(id);
    }

    @GetMapping("/allRapports")
    public List<RapportDto> getAllRapports() {
        return iRapportService.getAllRapports();
    }
}