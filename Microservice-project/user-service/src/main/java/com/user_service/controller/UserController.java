package com.user_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user_service.Dto.UserDto;
import com.user_service.entity.User;
import com.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Predicate;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public ObjectMapper objectMapper;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity getAllUser()
    {
        List<User> users = userService.getAllUser();
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUserById(@RequestParam  List<Long> userIds)
    {
        List<User> users = userService.getUserById(userIds);
        List<UserDto> userDtos =  users.stream().map(user -> objectMapper.convertValue(user,UserDto.class)).toList();
        return new ResponseEntity(userDtos, HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity saveUser(@Valid @RequestBody UserDto userDto)
    {
        User user = objectMapper.convertValue(userDto,User.class);
        Long userId = userService.saveUser(user);
        return new ResponseEntity("User is created successfully with user id - "+userId, HttpStatus.OK);
    }
}
