package com.example.invite.repository;

import com.example.invite.entity.Invité;
import org.springframework.data.jpa.repository.JpaRepository;

public interface inviteJpa extends JpaRepository<Invité,Long> {
}
