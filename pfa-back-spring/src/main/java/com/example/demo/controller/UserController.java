    package com.example.demo.controller;


    import com.example.demo.DTO.UserDto;
    import com.example.demo.entities.User;
    import lombok.NoArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import com.example.demo.services.UserService;
    import lombok.AllArgsConstructor;
    import org.springframework.web.bind.annotation.*;

    @CrossOrigin(origins = "http://localhost:4200")
    @RestController
    @AllArgsConstructor
    public class UserController {


        private UserService userService;

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

    }
