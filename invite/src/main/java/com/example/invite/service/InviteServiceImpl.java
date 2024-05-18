package com.example.invite.service;

import com.example.invite.DTO.InviteDTO;
import com.example.invite.DTO.UserDto;
import com.example.invite.entity.Invite;

import com.example.invite.repository.inviteJpa;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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
        List<Invite> invites = inviteRepository.findAll();
        return invites.stream()
                .map(invite -> modelMapper.map(invite, InviteDTO.class))
                .collect(Collectors.toList());
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