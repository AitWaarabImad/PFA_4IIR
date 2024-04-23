package com.example.demo.mapper;


import com.example.demo.DTO.UserDto;
import com.example.demo.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    ModelMapper modelMapper = new ModelMapper();
    public UserMapper(){
        configureMappings();
    }

    private void configureMappings() {

        modelMapper.typeMap(UserDto.class , User.class)
                .addMappings(mapper -> {
                    mapper.map(UserDto::getEmail ,User ::setEmail);
                    mapper.map(UserDto::getUsername , User::setUsername);
                    mapper.map(UserDto::getEmail , User::setEmail);
                    mapper.map(UserDto::getNom , User::setNom);
                    mapper.map(UserDto::getPrenom , User::setPrenom);
                    mapper.map(UserDto::getIsAuthenticated,User::setIsAuthenticated);
                });
        modelMapper.typeMap(User.class , UserDto.class)
                .addMappings(mapper -> {
                    mapper.map(User::getEmail ,UserDto::setEmail);
                    mapper.map(User::getUsername , UserDto::setUsername);
                    mapper.map(User::getEmail , UserDto::setEmail);
                    mapper.map(User::getNom , UserDto::setNom);
                    mapper.map(User::getPrenom , UserDto::setPrenom);
                    mapper.map(User::getIsAuthenticated,UserDto::setIsAuthenticated);
                });

    }

public User convertDtoToEntity(UserDto userDto){
        User user = new User();
        modelMapper.map(userDto,user);
        return user;
}

    public UserDto convertEntitytoDto(User user){
        UserDto userDto = new UserDto();
        modelMapper.map(user,userDto);
        return userDto;
    }

}
