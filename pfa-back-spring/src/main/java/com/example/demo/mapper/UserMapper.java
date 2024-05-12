package com.example.demo.mapper;


import com.example.demo.DTO.UserDto;
import com.example.demo.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public UserMapper() {
        configureMappings();
    }

    private void configureMappings() {
        modelMapper.typeMap(UserDto.class, User.class)
                .addMappings(mapper -> {

                    mapper.map(UserDto::getUsername, User::setUsername);
                    mapper.map(UserDto::getEmail, User::setEmail);
                    mapper.map(UserDto::getNom, User::setNom);
                    mapper.map(UserDto::getPrenom, User::setPrenom);

                    mapper.map(UserDto::getIsAuthenticated, User::setIsAuthenticated);
                });
        modelMapper.typeMap(User.class, UserDto.class)
                .addMappings(mapper -> {
                    mapper.map(User::getEmail, UserDto::setEmail);
                    mapper.map(User::getUsername, UserDto::setUsername);
                    mapper.map(User::getEmail, UserDto::setEmail);
                    mapper.map(User::getNom, UserDto::setNom);
                    mapper.map(User::getPrenom, UserDto::setPrenom);
                    mapper.map(User::getIsAuthenticated, UserDto::setIsAuthenticated);
                });
    }

    public User convertDtoToEntity(UserDto userDto) {
        User user = new User();
        modelMapper.map(userDto, user);
        return user;
    }

    public UserDto convertEntityToDto(User user) {
        UserDto userDto = new UserDto();
        modelMapper.map(user, userDto);
        return userDto;
    }

//    public List<UserDto> convertEntitiesToDtoList(List<User> users) {
//        if (users == null) {
//            return Collections.emptyList();
//        }
//        List<UserDto> collect = users.stream()
//                .map((User user) -> convertEntityToDto(Optional.ofNullable(user)))
//                .collect(Collectors.toList());
//        return collect;
//    }
}