package com.example.invite.repository;

import com.example.invite.entity.Invite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface inviteJpa extends JpaRepository<Invite,Long> {
}
