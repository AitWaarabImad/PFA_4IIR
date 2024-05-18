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

            Invite invite = Invite.builder().id_invite(id).id_reu(idReunion).nom_invite(userDto.getNomComplet()).id_user(userDto.getID_user()).build();
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
    public List<Long> getReuOfInv(Long idUser){
        List<Long> idReuList = new ArrayList<>();
        List<InviteDTO> invites = inviteRepository.findById_user(idUser);
        for (InviteDTO invite : invites) {
            idReuList.add(invite.getId_reu());
        }
        return idReuList;
    }

    @Override
    public void deleteInvite(long id) {
        inviteRepository.deleteById(id);
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