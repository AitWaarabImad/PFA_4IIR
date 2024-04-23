package com.example.demo.services;


import com.example.demo.DTO.UserDto;
import com.example.demo.entities.User;
import jakarta.annotation.Nullable;
import org.mindrot.jbcrypt.BCrypt;
import com.example.demo.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.demo.mapper.UserMapper;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements  IUserService{
    private IUserRepository iUserRepository;
    private UserMapper userMapper;

    @Override
    public User registerUser(UserDto userDto) {

        Optional<User> existingUser = iUserRepository.findByUsername(userDto.getUsername());
        Optional<User> existingEmail = iUserRepository.findByEmail(userDto.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Username already exists.");
        }
        if (existingEmail.isPresent()) {
            throw new RuntimeException("Email already registered.");
        }
        User userEntity = userMapper.convertDtoToEntity(userDto);
        return iUserRepository.save(userEntity);
    }

    @Override
    public UserDto loginUser(UserDto userDto) {
        Optional<User> userEntityOptional = iUserRepository.findByUsername(userDto.getUsername());
        if (userEntityOptional.isPresent()) {
            User userEntity = userEntityOptional.get();
            if(BCrypt.checkpw(userDto.getPassword(), userEntity.getPassword())){
                return userMapper.convertEntityToDto(userEntity);}
        }
        return null;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return iUserRepository.findByUsername(username);
    }

    @Override
    public void updateUser(UserDto userDto) {
        User user = iUserRepository.findByUsername(userDto.getUsername()).get();
        user.setIsAuthenticated(false);
        iUserRepository.save(user);
    }

    public UserDto UpdateInfoUser(UserDto userDto) {
        Optional<User> userOptional = iUserRepository.findByUsername(userDto.getUsername());
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found.");
        }

        User user = userOptional.get();


        user.setRole(userDto.getRole());
        user.setNom(userDto.getNom());
        user.setPrenom(userDto.getPrenom());


        User updatedUser = iUserRepository.save(user);


        return userMapper.convertEntityToDto(updatedUser);
    }

}
