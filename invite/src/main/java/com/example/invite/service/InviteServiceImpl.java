package com.example.invite.service;

import com.example.invite.DTO.InviteDTO;
import com.example.invite.DTO.UserDto;
import com.example.invite.entity.Invite;

import com.example.invite.repository.inviteJpa;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InviteServiceImpl implements iInviteService {

    @Autowired
    private inviteJpa inviteRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private WebClient webClient;

    @Override
    public void createInvite(List<Long> ids , long idReunion) {
        System.out.println("I am here in create invite");
        for(long id : ids){

            UserDto userDto = webClient.get()
                    .uri("http://localhost:8080/getId/"+ id)
                    .retrieve()
                    .bodyToMono(UserDto.class)
                    .block();

            Invite invite = Invite.builder().idInvite(id).idReu(idReunion).nomInvite(userDto.getNomComplet()).idUser(userDto.getID_user()).build();
            inviteRepository.save(invite);

        }
    }

    @Override
    public Optional<InviteDTO> getInviteById(long id) {
        Optional<Invite> invite = inviteRepository.findById(id);
        return invite.map(i -> modelMapper.map(i, InviteDTO.class));
    }

    @Override
    public List<InviteDTO> getAllInvites() {
        return null;
    }



    @Override
    public void deleteInvite(long id) {
        inviteRepository.deleteById(id);
    }
    @Override
    public List<Long> processReunionsByUserId(long idUser) {
        List<Invite> invites = inviteRepository.findByIdUser(idUser);
        List<Long> ids = new ArrayList<>() ;
        for (Invite invite : invites) {
            // Traitez chaque réunion ici
            // Par exemple, afficher les détails de chaque réunion
            ids.add(invite.getIdReu());
            System.out.println("Réunion ID: " + invite.getIdReu() + " pour l'utilisateur ID: " + idUser);

        }
        System.out.println(ids);
        return ids;
    }

    @Override
    public Optional<InviteDTO> updateInvite(InviteDTO inviteDTO, long id) {
        return inviteRepository.findById(id).map(invite -> {
            modelMapper.map(inviteDTO, invite);
            inviteRepository.save(invite);
            return modelMapper.map(invite, InviteDTO.class);
        });
    }
}