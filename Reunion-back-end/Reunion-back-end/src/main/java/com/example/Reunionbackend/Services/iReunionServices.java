package com.example.Reunionbackend.Services;

import com.example.Reunionbackend.DTO.ReuDto;
import com.example.Reunionbackend.ENTITY.Reunion;

import java.util.List;
import java.util.Optional;

public interface iReunionServices {

    public ReuDto creeReunion(ReuDto Rdto);

    public ReuDto getReunionById(Long id);

    public void deleteReunion(Long id);

    public Optional<Reunion> updateReunion(ReuDto reuDto, Long id);

   public List<ReuDto> findReunionsByUserId(Long ID_user);


}
