package com.example.demo.services;

import com.example.demo.DTO.UserDto;
import com.example.demo.entities.User;

import java.util.Optional;

public interface IUserService {
    public User registerUser(UserDto userDto) ;
    public UserDto loginUser(UserDto userDto) ;
    Optional<User> findByUsername(String username);
    public void updateUser(UserDto userDto);

}
