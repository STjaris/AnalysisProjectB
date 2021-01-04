package com.vijfmusketiers.ProjectB.authentication.user.controller;

import com.vijfmusketiers.ProjectB.authentication.user.User;
import com.vijfmusketiers.ProjectB.authentication.user.UserDto;
import com.vijfmusketiers.ProjectB.authentication.user.repository.UserRepository;
import com.vijfmusketiers.ProjectB.authentication.user.service.UserService;
import com.vijfmusketiers.ProjectB.rollator.Rollators;
import com.vijfmusketiers.ProjectB.route.Route;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping(
            value = "/users",
            consumes = "application/json",
            produces = "application/json")
    public UserDto saveUsers(@RequestBody User user) {
        return modelMapper.map(userService.save(user), UserDto.class);
    }


    @PutMapping("/users/{userid}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUserById(@PathVariable Long userid, @RequestBody User user){
        return modelMapper.map(userService.updateRollator(user, userid), UserDto.class);
    }

    @GetMapping("/users")
    public List<UserDto> getUserByEmail() {
        List<UserDto> userDtoList = new ArrayList<>();

        userRepository.findAll().forEach(user -> {
            userDtoList.add(modelMapper.map(user, UserDto.class));
        });

        return userDtoList;

    }

    @GetMapping("/users/{id}")
    public UserDto getUserByEmail(@PathVariable Long id) {
        return modelMapper.map(userRepository.findById(id).get(), UserDto.class);
    }

    public UserDto getUserByEmail(String email) {
        return modelMapper.map(userRepository.findByEmail(email), UserDto.class);
    }

    // Principal haalt de ingelogde gebruiker op.
    @GetMapping("user")
    public UserDto getLoginUser(Principal principal) {
        return getUserByEmail(principal.getName());
    }
}
