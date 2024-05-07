package com.example.sallereunion.Service;

import com.example.sallereunion.DTO.SalleReunionDto;
import com.example.sallereunion.ENTITY.SalleReunion;
import com.example.sallereunion.Repositories.SalleReunionRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SalleReunionService implements iSalleReunionService{

    private SalleReunionRepo iRepoSalle;
    private ModelMapper modelMapper;

    private WebClient webClient;

    public Optional<SalleReunionDto> getSallebyId(Long id) {
        return iRepoSalle.findById(id)
                .map(salle -> modelMapper.map(salle, SalleReunionDto.class));
    }

    @Override
    public SalleReunionDto createSalle(SalleReunionDto salleReunionDto){
        SalleReunion salle = modelMapper.map(salleReunionDto,SalleReunion.class);

              iRepoSalle.save(salle);
              return salleReunionDto;



    }

    @Override
    public void DeleteSalle(Long Id) {
        iRepoSalle.deleteById(Id);

    }


    @Override
    public SalleReunion updateSalle(Long id, SalleReunionDto salleReunionDto) {

        SalleReunion salleReunion = modelMapper.map(salleReunionDto, SalleReunion.class);

        return iRepoSalle.findById(id).map(existingSalle -> {

            existingSalle.setNomSalle(salleReunion.getNomSalle());
            existingSalle.setPlace(salleReunion.getPlace());
            existingSalle.setID_Salle(salleReunion.getID_Salle());

            return iRepoSalle.save(existingSalle);
        }).orElseThrow(() -> new RuntimeException("Salle not found with id " + id));
    }


    @Override
    public List<SalleReunion> getAllSalle() {

        return iRepoSalle.findAll();
    }


}
