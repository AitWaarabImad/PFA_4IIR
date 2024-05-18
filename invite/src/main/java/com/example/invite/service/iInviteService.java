package com.example.invite.service;

import com.example.invite.DTO.InviteDTO;

import java.util.List;
import java.util.Optional;

public interface iInviteService {
    void createInvite(List<Long> ids, long idReunion);
    Optional<InviteDTO> getInviteById(long id);
    List<InviteDTO> getAllInvites();
    void deleteInvite(long id);
    Optional<InviteDTO> updateInvite(InviteDTO inviteDTO, long id);

    public List<Long> getReuOfInv(Long idUser);

    }
