    package com.example.demo.controller;


    import com.example.demo.DTO.UserDto;
    import com.example.demo.entities.Role;
    import com.example.demo.entities.User;
    import com.example.demo.mapper.UserMapper;
    import com.example.demo.repository.IUserRepository;
    import com.example.demo.services.IUserService;
    import com.example.demo.services.UserService;
    import lombok.AllArgsConstructor;
    import org.springframework.web.bind.annotation.*;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;

    import static java.lang.System.in;

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

        @GetMapping("/username")
        public List<String> GetNamesOfUsers()
        {
            List<UserDto> allUsers = userService.getAllUserDtos();
            List<String> userNames = new ArrayList<>();
            for (UserDto user : allUsers) {
                if(user.getRole()== Role.Rapporteur)
                    userNames.add(user.getNom()+" "+user.getPrenom());
            }

            return userNames;
        }

        @GetMapping("/getId/{id}")
        public UserDto getByiD(@PathVariable Long id){
            return iUserService.findById(id);
        }

        @GetMapping("/getnameId/{id}")
        public UserDto getnameByiD(@PathVariable Long id){
            UserDto userDto = iUserService.findById(id);
            return userDto;
        }
        @PutMapping("/update")
        public UserDto updateUser(@RequestBody UserDto userDto) {
            try {
                return userService.UpdateInfoUser(userDto);
            } catch (RuntimeException ex) {
                return null;
            }
        }
        @GetMapping("/userid/{nom}/{prenom}")
        public Optional<Long> getUserIdByNomAndPrenom(@PathVariable String nom, @PathVariable String prenom) {
            return userService.getUserIdByNomAndPrenom(nom, prenom);
        }



    }
