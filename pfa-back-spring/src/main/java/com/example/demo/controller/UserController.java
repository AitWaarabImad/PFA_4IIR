    package com.example.demo.controller;


    import com.example.demo.DTO.UserDto;
    import com.example.demo.entities.User;
    import com.example.demo.mapper.UserMapper;
    import com.example.demo.repository.IUserRepository;
    import com.example.demo.services.IUserService;
    import lombok.NoArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import com.example.demo.services.UserService;
    import lombok.AllArgsConstructor;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @CrossOrigin(origins = "http://localhost:4200")
    @RestController
    @AllArgsConstructor
    public class UserController {


        private UserService userService;
        private final IUserRepository userRepository;
        private final UserMapper userMapper;
        private final IUserService iUserService;

        @PostMapping("/login")
        public UserDto login(@RequestBody UserDto userDto)
        {
            UserDto userDto1;
            userDto1 = userService.loginUser(userDto);
            userDto1.setIsAuthenticated(true);
            return userDto1;

        }
        @PostMapping("/register")
        public User register(@RequestBody UserDto userDto)
        {
            return userService.registerUser(userDto);
        }

        @PutMapping()
        public void logout(@RequestBody UserDto userDto)
        {
            userService.updateUser(userDto);
        }

        @GetMapping("/all")
        public List<UserDto> getAllUsers() {

            return userService.getAllUserDtos();
        }

        @GetMapping("/getId/{id}")
        public UserDto getByiD(@PathVariable Long id){
            return iUserService.findById(id);
        }

        @GetMapping("/getnameId/{id}")
        public String getnameByiD(@PathVariable Long id){
            UserDto userDto = iUserService.findById(id);
            return userDto.getNom() + " " + userDto.getPrenom();
        }
        @PutMapping("/update")
        public UserDto updateUser(@RequestBody UserDto userDto) {
            try {
                return userService.UpdateInfoUser(userDto);
            } catch (RuntimeException ex) {
                return null;
            }
        }


    }
