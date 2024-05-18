package com.example.invite.repository;

import com.example.invite.DTO.InviteDTO;
import com.example.invite.entity.Invite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface inviteJpa extends JpaRepository<Invite,Long> {
     List<InviteDTO> findById_user(Long id_user);
}
