package com.example.Reunionbackend.Controller;

import com.example.Reunionbackend.DTO.ReuDto;
import com.example.Reunionbackend.ENTITY.Reunion;
import com.example.Reunionbackend.Services.ReunionServices;
import com.example.Reunionbackend.Services.iReunionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class Reucontrolller {
    @Autowired
    private iReunionServices iReu;
@PostMapping("createReunion")
    public ReuDto creeReu(@RequestBody ReuDto reuDto){
        return iReu.creeReunion(reuDto);
    }
@GetMapping("reunion/{id}")
    public ReuDto getReuId(@PathVariable Long id){


    return iReu.getReunionById(id);

    }
    @PutMapping("update/{id}")
    public Optional<Reunion> update(@PathVariable Long id, @RequestBody ReuDto  reuDto){

    return iReu.updateReunion(reuDto,id);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id ){
    iReu.deleteReunion(id);
    }


    @GetMapping("/userReunions/{ID_user}")
    public List<ReuDto> getReunionsByUserId(@PathVariable Long ID_user) {
        return iReu.findReunionsByUserId(ID_user);
    }

    @GetMapping("AllReunions")
    public List<ReuDto> getAllReunions() {
        return iReu.getAllReunions();
    }

}
