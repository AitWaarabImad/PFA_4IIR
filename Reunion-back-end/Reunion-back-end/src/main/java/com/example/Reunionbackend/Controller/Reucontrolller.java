package com.example.Reunionbackend.Controller;

import com.example.Reunionbackend.DTO.ReuDto;
import com.example.Reunionbackend.ENTITY.Reunion;
import com.example.Reunionbackend.Services.iReunionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Reucontrolller {
    @Autowired
    private iReunionServices iReu;
@PostMapping("createR")
    public ReuDto creeReu(@RequestBody ReuDto reuDto){
        return iReu.creeReunion(reuDto);


    }
@GetMapping("reu/{id}")
    public ReuDto getReuId(@PathVariable Long id){


    return iReu.getReunionById(id);

    }
    @PutMapping("updtae/{id}")
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

}
