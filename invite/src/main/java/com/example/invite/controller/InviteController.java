package com.example.invite.controller;

import com.example.invite.DTO.InviteDTO;
import com.example.invite.service.iInviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invites")
@CrossOrigin(origins = "http://localhost:4200")
public class InviteController {

    @Autowired
    private iInviteService inviteService;

    @PostMapping("/{idReunion}")
    void createInvite(@RequestBody List<Long> ids,@PathVariable long idReunion){
        System.out.println("I am here in create invite controller");

        inviteService.createInvite(ids,idReunion);
    }

    @GetMapping("/reunions/{idUser}")
    public List<Long> processReunionsByUserId(@PathVariable long idUser) {
        return inviteService.processReunionsByUserId(idUser);

    }
    @GetMapping("/{id}")
    public Optional<InviteDTO> getInvite(@PathVariable long id) {
        return inviteService.getInviteById(id);
    }

    @GetMapping
    public List<InviteDTO> getAllInvites() {
        return inviteService.getAllInvites();
    }

    @DeleteMapping("/{id}")
    public void deleteInvite(@PathVariable long id) {
        inviteService.deleteInvite(id);
    }

    @PutMapping("/{id}")
    public Optional<InviteDTO> updateInvite(@RequestBody InviteDTO inviteDTO, @PathVariable long id) {
        return inviteService.updateInvite(inviteDTO, id);
    }
}