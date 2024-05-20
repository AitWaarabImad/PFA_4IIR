package com.example.rapport.Controller;

import com.example.rapport.Dto.RapportDto;
import com.example.rapport.Service.iRapportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RapportController {

   @Autowired

    iRapportService  iRapportService;


   @PostMapping("/createRapport")
    public RapportDto createRapport(@RequestBody Map<String, Long> requestBody) {
        Long id_rapp = requestBody.get("id_rapp");
        Long id_reunion = requestBody.get("id_reunion");
        return iRapportService.createRapport(id_rapp, id_reunion);
    }

    @GetMapping("/rapport/{id}")
    public RapportDto getRapportById(@PathVariable Long id) {
        return iRapportService.getRapportById(id);
    }
    @GetMapping("rapports/{id}")
    public List<RapportDto> getRapportByIdrapp(@PathVariable Long id) {
        return iRapportService.getRaportsbyIdrapp(id);
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